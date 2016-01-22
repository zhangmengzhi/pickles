package org.zhangmz.pickles.modules.utils;

import org.zhangmz.pickles.modules.vo.DiscuzHashPasswordResult;

/**
 * 
 * @ClassName:DiscuzHashPassword 
 * @Description:Discuz密码操作类
 * @author:张孟志
 * @date:2016年1月20日 上午9:35:08 
 * @version V1.0
 * 说明：实现Discuz密码操作，为数据迁移做准备
 * 
 *      uc_client/model/user.php  function add_user
 *      $password = md5(md5($password).$salt);
 *      存储到数据库中的用户密码的加密方式：用户输入的密码经过md5加密，后面跟上一段字符串$salt之后，再md5加密一次。
 *      $salt = substr(uniqid(rand()), -6);
 *      可以分析为从后面往前截取6个字符。
 *      那么discuz存储密码的方式就得到了：用户输入密码md5加密后，在连接上6个随机字符然后md5加密一次。
 *      其中，md5加密为  32位（小）
 *      
 *      查看数据库表 pre_ucenter_members  有password.salt
 *      里面有一个 salt字段。这个字段即是后面跟的那个随机数。
 *      即当插入用户的时候，生成的那个随机数。而且用户登陆的时候，验证用的随机数，也是这个。
 */
public class DiscuzHashPassword {

	public static DiscuzHashPasswordResult getDiscuzHashPasswordResult(String input) {
		return getDiscuzHashPasswordResult(input, null);
	}
	
	private static DiscuzHashPasswordResult getDiscuzHashPasswordResult(String input, String salt) {
		DiscuzHashPasswordResult result = new DiscuzHashPasswordResult();
		
		result.setSalt(salt);
		if(null == salt){ result.setSalt(Md5Util.getRandomString(6));}

		String passwordMd5 = Md5Util.getMd5(input);		
		result.setHashPassword(Md5Util.getMd5(passwordMd5 + result.getSalt()));
		
		return result;
	}
	
	public static String getDiscuzHashPassword(String input, String salt) {
		DiscuzHashPasswordResult result = getDiscuzHashPasswordResult(input, salt);
		return result.getHashPassword();
	}
}


