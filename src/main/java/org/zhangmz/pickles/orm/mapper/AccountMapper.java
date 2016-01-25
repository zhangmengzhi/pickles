package org.zhangmz.pickles.orm.mapper;

import org.zhangmz.pickles.orm.model.Account;
import org.zhangmz.pickles.orm.MyMapper;

/**
 * 
 * @ClassName:AccountMapper 
 * @Description:帐户（account）的操作类
 * @author:张孟志
 * @date:2016年1月22日 下午8:48:43 
 * @version V1.0
 * 说明：帐户（account）的操作类
 *     开发代码用自https://github.com/abel533/MyBatis-Spring-Boot
 */
public interface AccountMapper extends MyMapper<Account> {
	
	/**
	 * 
	 * @Title: getByPhoneEmail 
	 * @Description: 查询登录用户 
	 * @param phoneEmail
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午8:25:09
	 * 说明：用户登录，用户信息可以是手机号，也可以是Email
	 */
	Account getByPhoneEmail(String phoneEmail);
}