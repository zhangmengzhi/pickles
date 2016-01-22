package org.zhangmz.pickles.modules.vo;

/**
 * 
 * @ClassName:DiscuzHashPasswordResult 
 * @Description:Discuz HashPassword 
 * @author:张孟志
 * @date:2016年1月20日 上午10:36:44 
 * @version V1.0
 * 说明：Discuz HashPassword 包括SALT、HASHPASSWORD
 */
public class HashPasswordResult {
	private String hashPassword;
	private String salt;
	
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}