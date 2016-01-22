package org.zhangmz.pickles.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhangmz.pickles.modules.utils.DiscuzHashPassword;
import org.zhangmz.pickles.modules.utils.Ids;
import org.zhangmz.pickles.orm.mapper.AccountMapper;
import org.zhangmz.pickles.orm.model.Account;
import org.zhangmz.pickles.service.exception.ErrorCode;
import org.zhangmz.pickles.service.exception.ServiceException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName:AccountService 
 * @Description:账户管理
 * @author:张孟志
 * @date:2016年1月7日 上午10:42:26 
 * @version V1.0
 * 说明：账户管理
 */
@Service
public class AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
    @Autowired
    private AccountMapper accountMapper;

    // 登录信息缓存时间设置  TOKEN有效期
 	@Value("${app.loginTimeoutSecs:600}")
 	private int loginTimeoutSecs;
 
 	// codehale metrics
 	@Autowired
 	private CounterService counterService;

	// guava cache
 	// <TOKEN, account>
	private Cache<String, Account> loginUsers;
	
	@PostConstruct
	public void init() {
		logger.debug("登录信息缓存时间设置 = " + loginTimeoutSecs);
		loginUsers = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
				.build();
	}

	/*************************************************************************
	 * 说明：处理API用户登录请求
	 *      检验用户登录凭证，目前使用email/password
	 *      登录成功产生一个唯一的标识TOKEN，
	 *      保存登录记录（如果不保存数据库则需要将TOKEN保存在缓存）返回TOKEN
	 * 作者：张孟志
	 * 日期：2016-01-10
	 ************************************************************************/
 	@Transactional(readOnly = true)
	public String login(String email, String password) {
 		
 		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
 			logger.error(email + "用户名或密码为空。 ");
			throw new ServiceException("用户名或密码为空。", ErrorCode.UNAUTHORIZED);
 		}
 		
		Account account = this.getByEmail(email);

		if (account == null) {
			logger.error(email + "登录失败，该Email为未注册用户。 ");
			throw new ServiceException("该Email为未注册用户", ErrorCode.UNAUTHORIZED);
		}

		// 设置为Discuz加密方式，为数据迁移做准备
		if (!account.getHashPassword().equals(
				DiscuzHashPassword.getHashPassword(password, account.getSalt()))) {
			logger.error(email + "登录失败，密码错误。 ");
			throw new ServiceException("密码错误", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		
		// 这里可以将登录信息保存到数据库		
		// 将登录信息放入缓存
		loginUsers.put(token, account);
		/*
		loginTimeInfos.put(email, new Date());
		*/
		
		// 系统监控  统计登录用户总数
		counterService.increment("loginUser");
		
		logger.info(email + " login, TOKEN = " + token + ", admin? " + this.isAdmin(token));
		return token;
	}
 	
 	/*************************************************************************
	 * 说明：处理API用户退出请求
	 *      从缓存中取出用户信息、登录用户计数减少
	 * 作者：张孟志
	 * 日期：2016-01-10
	 ************************************************************************/
 	public void logout(String token) {
 		if(StringUtils.isEmpty(token)){
 			return;
 		}
 		
		Account account = loginUsers.getIfPresent(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			loginUsers.invalidate(token);
			counterService.decrement("loginUser");
		}		

		logger.info("logout, TOKEN = " + token);
	}
 	
 	/*************************************************************************
	 * 说明：处理API用户注册请求
	 *      从缓存中取出用户信息、登录用户计数减少
	 * 作者：张孟志
	 * 日期：2016-01-10
	 ************************************************************************/
 	@Transactional
	public void register(String email, String name, String password) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		Account account = new Account();
		account.setEmail(email);
		account.setName(name);
		account.setPassword(password);
		this.save(account);

		logger.info(email + " register. ");
	}
 	
 	/*************************************************************************
	 * 说明：校验是否登录，做权限控制
	 * 作者：张孟志
	 * 日期：2016-01-13
	 ************************************************************************/
 	public boolean isLogin(String token) {
 		boolean bln = false;
 		if(StringUtils.isNotEmpty(token)){
 			Account account = loginUsers.getIfPresent(token);
 			if (account != null) {
 				bln = true;
 			}
 		}
 		
 		return bln;
	}
 	
 	/*************************************************************************
	 * 说明：校验是否为管理员
	 * 作者：张孟志
	 * 日期：2016-01-13
	 ************************************************************************/
 	public boolean isAdmin(String token) {
 		boolean bln = false;
 		if(StringUtils.isNotEmpty(token)){
 			Account account = loginUsers.getIfPresent(token);
 			// 判断是否为管理员，管理员的GroupId为1
 			if ((account != null) && (1 == account.getGroupId().intValue())) {
 				bln = true;
 			}
 		}
 		
 		return bln;
	}
 	
 	/*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/
 	public List<Account> search(Account account) {
        if (account.getPage() != null && account.getRows() != null) {
            PageHelper.startPage(account.getPage(), account.getRows());
        }
        return accountMapper.select(account);
    }
 	
    public List<Account> getAll(Account account) {
        if (account.getPage() != null && account.getRows() != null) {
            PageHelper.startPage(account.getPage(), account.getRows());
        }
        return accountMapper.selectAll();
    }

    public Account getById(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Long id) {
        accountMapper.deleteByPrimaryKey(id);
    }

    public void save(Account account) {
        if (account.getId() != null) {
            accountMapper.updateByPrimaryKey(account);
        } else {
            accountMapper.insert(account);
        }
    }

    /*************************************************************************
 	 * 说明：模糊查询，页面查询框服务
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/
    public List<Account> searchLike(Account account) {
        if (account.getPage() != null && account.getRows() != null) {
            PageHelper.startPage(account.getPage(), account.getRows());
        }
        return null;
    }
    
    /*************************************************************************
 	 * 说明：email条件查询，用于校验Email是否已注册，登录查询是否有该用户
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/
    public Account getByEmail(String email) {
    	Account account = new Account();
    	account.setEmail(email);
    	return accountMapper.selectOne(account);
    }
}
