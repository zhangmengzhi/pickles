package org.zhangmz.pickles.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.modules.utils.DiscuzHashPassword;
import org.zhangmz.pickles.modules.utils.Ids;
import org.zhangmz.pickles.orm.mapper.AccountMapper;
import org.zhangmz.pickles.orm.model.Account;
import org.zhangmz.pickles.service.exception.ErrorCode;
import org.zhangmz.pickles.service.exception.ServiceException;
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

    @Autowired
    private AuthorityHelper authorityHelper;
    
    /**
     * 
     * @Title: login 
     * @Description: 处理用户登录请求
     * @param phoneEmail
     * @param password
     * @return
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年1月25日 下午8:14:29
     * 说明：处理用户登录请求
	 *      检验用户登录凭证，目前使用email/password
	 *      登录成功产生一个唯一的标识TOKEN，
	 *      保存登录记录（如果不保存数据库则需要将TOKEN保存在缓存）返回TOKEN
	 *      
 	 *	只查询一次数据库，忽略 @Transactional(readOnly = true)
     */
	public String login(String phoneEmail, String password) {
 		
 		if (StringUtils.isBlank(phoneEmail) || StringUtils.isBlank(password)) {
 			logger.warn(phoneEmail + "用户信息或密码为空。 ");
			throw new ServiceException("用户信息或密码为空。", ErrorCode.UNAUTHORIZED);
 		}
 		
		Account account = this.getByPhoneEmail(phoneEmail);

		if (account == null) {
			logger.error(phoneEmail + "登录失败，未注册用户。 ");
			throw new ServiceException("未注册用户", ErrorCode.UNAUTHORIZED);
		}

		// 设置为Discuz加密方式，为数据迁移做准备
		if (!account.getHashPassword().equals(
				DiscuzHashPassword.getHashPassword(password, account.getSalt()))) {
			logger.warn(phoneEmail + "登录失败，密码错误。 ");
			throw new ServiceException("密码错误", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		
		// 这里可以将登录信息保存到数据库		
		// 将登录信息放入缓存
		authorityHelper.putAccount(token, account);
		/*
		loginTimeInfos.put(email, new Date());
		*/
				
		logger.info(phoneEmail + " login, TOKEN = " + token + ", admin? " + authorityHelper.isAdministrator(token));
		return token;
	}
    
    /**
     * 
     * @Title: getByPhoneEmail 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param email
     * @return
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年1月25日 下午8:21:09
     * 说明：用户登录，用户信息可以是手机号，也可以是Email
     */
    public Account getByPhoneEmail(String phoneEmail) {    	
    	return accountMapper.getByPhoneEmail(phoneEmail);
    }    

	/**
	 * 
	 * @Title: logout 
	 * @Description: 处理用户退出请求
	 * @param token
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午8:16:52
	 * 说明：处理用户退出请求
	 *      从缓存中取出用户信息、登录用户计数减少
	 */
 	public void logout(String token) {
 		if(StringUtils.isBlank(token)){
 			return;
 		}
 		
		Account account = authorityHelper.getAccount(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			authorityHelper.invalidateAccount(token);
		}		

		logger.info("logout, TOKEN = " + token);
	}
 	
 	/**
 	 * 
 	 * @Title: register 
 	 * @Description:处理用户注册请求
 	 * @param email
 	 * @param name
 	 * @param password
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 下午8:17:13
 	 * 说明：处理用户注册请求
	 *      从缓存中取出用户信息、登录用户计数减少
 	 */
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
}
