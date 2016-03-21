package org.zhangmz.pickles.orm.mapper;

import org.zhangmz.pickles.orm.model.Account;
import org.apache.ibatis.annotations.Param;
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

//	/**
//	 * 重写这个方法可以自己定制SQL查询语句（结果）
//	 */
//    List<Account> select(Account record);    
	
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
	 * 这个方法名应该改为select*，统一命名
	 * 考虑到这是最初代码，不修改。
	 */
	Account getByPhoneEmail(String phoneEmail);
	
	Account selectByPhoneEmail(@Param("groupCode")String groupCode, @Param("phoneEmail")String phoneEmail);
}