package org.zhangmz.pickles.modules.utils;

import org.zhangmz.pickles.modules.vo.HashPasswordResult;

/**
 * 
 * @ClassName:DiscuzHashPassword 
 * @Description:Discuz密码操作类
 * @author:张孟志
 * @date:2016年1月20日 上午9:35:08 
 * @version V1.0
 * 说明：实现Discuz密码操作，为数据迁移做准备
 * 		DiscuzHashPassword对原始密码加密
 * 		DiscuzHashMd5Password对已MD5加密的字符串加密
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
	
	/**
	 * 
	 * @Title: getHashPassword 
	 * @Description: 获取加密后的hashpassword
	 * @param input
	 * @param salt
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月23日 上午7:46:58
	 * 说明：根据用户输入的密码、数据库保存的salt，计算加密后的hashpassword
	 */
	public static String getHashPassword(String input, String salt) {
		HashPasswordResult result = getHashPasswordResult(input, salt, false);
		return result.getHashPassword();
	}	

	public static String getHashPassword(String input, String salt, boolean isMd5Input) {
		HashPasswordResult result = getHashPasswordResult(input, salt, isMd5Input);
		return result.getHashPassword();
	}
	
	/**
	 * 
	 * @Title: getHashPasswordResult 
	 * @Description: 获取加密后对象
	 * @param input
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月23日 上午7:49:44
	 * 说明：该方法获取原始加密后对象，设置salt为null，加密过程随机产生salt。
	 */
	public static HashPasswordResult getHashPasswordResult(String input) {
		return getHashPasswordResult(input, null, false);
	}

	public static HashPasswordResult getHashPasswordResult(String input, boolean isMd5Input) {
		return getHashPasswordResult(input, null, isMd5Input);
	}
	
	/**
	 * 
	 * @Title: getHashPasswordResult 
	 * @Description: 获取加密后对象
	 * @param input       字符串
	 * @param salt        SALT
	 * @param isMd5Input  是否Md5加密后的字符串
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月23日 上午7:50:32
	 * 说明：加密后产生了加密salt、hashPassword，封装在HashPasswordResult
	 */
	private static HashPasswordResult getHashPasswordResult(String input, String salt, boolean isMd5Input) {
		HashPasswordResult result = new HashPasswordResult();
		String passwordMd5 = null;
		
		// 兼顾密码生成（即使产生salt）与密码检验(salt已保存在数据库中)
		result.setSalt(salt);
		if(null == salt){ result.setSalt(Md5Util.getRandomString(6));}

		if(isMd5Input){
			passwordMd5 = input;
		}else{
			passwordMd5 = Md5Util.getMd5(input);
		}
		
		result.setHashPassword(Md5Util.getMd5(passwordMd5 + result.getSalt()));
		
		return result;
	}
}


