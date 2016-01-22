package org.zhangmz.pickles.modules.utils;

import java.nio.charset.Charset;
import org.zhangmz.pickles.modules.constants.Charsets;
import org.zhangmz.pickles.modules.utils.Digests;
import org.zhangmz.pickles.modules.utils.Encodes;

/**
 * 
 * @ClassName:HashPassword 
 * @Description:密码加密工具类
 * @author:张孟志
 * @date:2016年1月7日 下午4:05:07 
 * @version V1.0
 * 说明：对密码字符进行加密
 * 对Encodes定义的方法的组合
 */
public class HashPassword {

	public static String sha1Hex(String forPassword) {
		return Encodes.encodeHex(Digests.sha1(forPassword));
	}	
	
	public static String sha1Base62(String forPassword) {
		return Encodes.encodeBase62(Digests.sha1(forPassword));
	}
	
	/**
	 * 
	 * @Title: sha1Base64 
	 * @Description: 加密算法
	 * @param forPassword
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月7日 下午4:32:12
	 * 说明：建议采用的加密算法
	 */
	public static String sha1Base64(String forPassword) {
		return Encodes.encodeBase64(Digests.sha1(forPassword));
	}
	
	public static String sha1Base64MostSecure(String forPassword, String forSalt) {
		return HashPassword.sha1Base64MostSecure(forPassword, Charsets.UTF8, forSalt, Charsets.UTF8, 1);
	}
	
	public static String sha1Base64MostSecure(String forPassword, String forSalt, int iterations) {
		return HashPassword.sha1Base64MostSecure(forPassword, Charsets.UTF8, forSalt, Charsets.UTF8, iterations);
	}
	
	public static String sha1Base64MostSecure(String forPassword, String forSalt, Charset charset) {
		return HashPassword.sha1Base64MostSecure(forPassword, charset, forSalt, charset, 1);
	}
	
	public static String sha1Base64MostSecure(String forPassword, String forSalt, Charset charset, int iterations) {
		return HashPassword.sha1Base64MostSecure(forPassword, charset, forSalt, charset, iterations);
	}
	
	/**
	 * 
	 * @Title: sha1Base64MostSecure 
	 * @Description: 最高安全级别的加密算法
	 * @param forPassword            原密码
	 * @param charsetforPassword     原密码字符集
	 * @param forSalt                salt
	 * @param charsetforSalt         salt字符集
	 * @param iterations             迭代次数
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月7日 下午4:35:31
	 * 说明：最高安全级别的加密算法。采用原密码/salt多次迭代获取SHA-1散列，最后做Base64加密。
	 */
	public static String sha1Base64MostSecure(String forPassword, Charset charsetforPassword, 
												String forSalt, Charset charsetforSalt, 
												int iterations) {
		return Encodes.encodeBase64(Digests.sha1(forPassword.getBytes(charsetforPassword),
													forSalt.getBytes(charsetforSalt), 
													iterations));
	}
}
