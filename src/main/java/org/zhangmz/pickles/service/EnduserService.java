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
import org.zhangmz.pickles.orm.mapper.EnduserMapper;
import org.zhangmz.pickles.orm.mapper.GroupMapper;
import org.zhangmz.pickles.orm.model.Enduser;
import org.zhangmz.pickles.orm.model.Group;
import org.zhangmz.pickles.service.exception.ErrorCode;
import org.zhangmz.pickles.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName:EnduserService
 * @Description:终端用户管理
 * @author:张孟志
 * @date:2016年1月7日 上午10:42:26 
 * @version V1.0
 * 说明：终端用户管理
 */
@Service
public class EnduserService {

	private static Logger logger = LoggerFactory.getLogger(EnduserService.class);
	
    @Autowired
    private AuthorityHelper authorityHelper;    
	
    @Autowired
    private EnduserMapper enduserMapper;  
    
    @Autowired
    private GroupMapper groupMapper;      

    /**
     * 
     * @Title: register 
     * @Description: 处理用户注册请求
     * @param groupCode
     * @param phone
     * @param password
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年3月21日 上午11:59:38
     * 说明：处理用户注册请求
     */
    @Transactional
	public void register(String groupCode, String phone, String password) {
    	if (checkBlank(groupCode, phone, password)) {
 			logger.warn(groupCode + "_" + phone + "用户信息或密码为空。 ");
			throw new ServiceException("用户信息或密码为空。", ErrorCode.UNAUTHORIZED);
 		}
    	
    	// 根据 groupCode 找 groupId，有且只有一个
		Group group = new Group();
		group.setCode(groupCode);
		List<Group> groups = groupMapper.select(group);
		if(null != groups && 1 == groups.size()){
			group = groups.get(0);
		} else {
			throw new ServiceException("用户组信息有误。", ErrorCode.UNAUTHORIZED);
		}
		
		Enduser eu = new Enduser();
		eu.setGroupId(group.getId());
		eu.setPhone(phone);
		eu.setName(phone);
		eu.setPassword(password);
		eu.setStatus("Yes");

		this.save(eu);

		logger.info(phone + " register. ");
    }
    
	/**
	 * 
	 * @Title: login 
	 * @Description: 处理登录请求 
	 * @param groupCode       用户组编码
	 * @param phone           手机号
	 * @param password        密码
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年3月8日 下午4:34:58
	 * 说明：处理登录请求
	 *      检验用户登录凭证，目前使用用户组编码、phone、密码
	 *      登录成功产生一个唯一的标识TOKEN，
	 *      保存登录记录（如果不保存数据库则需要将TOKEN保存在缓存）返回TOKEN
	 */
	public String login(String groupCode, String phone, String password) {		
		
		if (checkBlank(groupCode, phone, password)) {
 			logger.warn(groupCode + "_" + phone + "用户信息或密码为空。 ");
			throw new ServiceException("用户信息或密码为空。", ErrorCode.UNAUTHORIZED);
 		}
		
		Enduser enduser = enduserMapper.selectByPhone(groupCode, phone);		

		if (enduser == null) {
			logger.error(groupCode + "_" + phone + "登录失败，未注册用户。 ");
			throw new ServiceException("未注册用户", ErrorCode.UNAUTHORIZED);
		}

		// 设置为Discuz加密方式，为数据迁移做准备
		if (!enduser.getHashPassword().equals(
			DiscuzHashPassword.getHashPassword(password, enduser.getSalt()))) {
			logger.warn(groupCode + "_" + phone + "登录失败，密码错误。 ");
			throw new ServiceException("密码错误", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		
		// 这里可以将登录信息保存到数据库		
		// 将登录信息放入缓存
		authorityHelper.putEnduser(token, enduser);
		/*
		loginTimeInfos.put(email, new Date());
		*/
				
		logger.info(groupCode + "_" + phone + " login, TOKEN = " + token);
		return token;
	}
	
	/**
	 * 
	 * @Title: checkBlank 
	 * @Description: 检查参数是否为空
	 * @param groupCode       用户组编码
	 * @param phone           手机号或Email
	 * @param password        密码
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年3月8日 下午4:38:18
	 * 说明：登陆管理员控制台不需要用户组编码这个参数
	 */
	private boolean checkBlank(String groupCode, String phone, String password) {
		boolean bln = StringUtils.isBlank(groupCode) 
				|| StringUtils.isBlank(phone) 
				|| StringUtils.isBlank(password);
		
		// 参数的其他检查
		
		return bln;
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
 		
		Enduser enduser = authorityHelper.getEnduser(token);
		if (enduser == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			authorityHelper.invalidateEnduser(token);
		}		

		logger.info("logout, TOKEN = " + token);
	}
 	 	
 	/*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/
 	public List<Enduser> search(Integer groupId, Integer page, Integer rows) {
        return enduserMapper.selectEnduserPage(groupId, (page-1)*rows, rows);
    }
 	
 	public List<Enduser> search(Enduser enduser) {
        if (enduser.getPage() != null && enduser.getRows() != null) {
            PageHelper.startPage(enduser.getPage(), enduser.getRows());
        }
        return enduserMapper.select(enduser);
    }
 	
    public List<Enduser> getAll(Enduser enduser) {
        if (enduser.getPage() != null && enduser.getRows() != null) {
            PageHelper.startPage(enduser.getPage(), enduser.getRows());
        }
        return enduserMapper.selectAll();
    }

    public Enduser getById(Long id) {
        return enduserMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Long id) {
    	enduserMapper.deleteByPrimaryKey(id);
    }

    public void save(Enduser enduser) {
        if (enduser.getId() != null) {
        	enduserMapper.updateByPrimaryKey(enduser);
        } else {
        	enduserMapper.insert(enduser);
        }
    }
    
    /*************************************************************************
 	 * 说明：权限判断服务
 	 * 作者：张孟志
 	 * 日期：2016-01-26
 	 ************************************************************************/    
    public boolean isLogin(String token){
    	return authorityHelper.isLogin(token, 2);
    }   

    public void invalidateEnduser(String token){
    	authorityHelper.invalidateEnduser(token);
    }
}
