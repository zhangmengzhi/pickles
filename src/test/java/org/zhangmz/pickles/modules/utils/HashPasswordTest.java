package org.zhangmz.pickles.modules.utils;

import org.junit.Assert;
import org.junit.Test;
import org.zhangmz.pickles.modules.vo.HashPasswordResult;

public class HashPasswordTest {

	@Test
	public void decode() {
		String password = "password";
		String hashPassword = HashPassword.sha1Base64(password);
		Assert.assertEquals("W6ph5Mm5Pz8GgiULbPgzG37mj9g=", hashPassword);
	}
	
	/*
		password = "123cS66zC1Q2w3E";
		mysql> select username, password, salt from szts_ucenter_members where uid = 1;
		+------------+----------------------------------+--------+
		| username   | password                         | salt   |
		+------------+----------------------------------+--------+
		| admin4szts | 497d70928ec3d5dde7cafe97d16e6c4e | 30db8c |
		+------------+----------------------------------+--------+
	*/
	@Test
	public void decodeDiscuz() {
		String password = "password";
		
		// 随机产生salt，加密后结果
		HashPasswordResult result = DiscuzHashPassword.getHashPasswordResult(password);
		System.out.println(result.getSalt());
		System.out.println(result.getHashPassword());
		
		// 使用已有的salt验证
		String salt = "4sG3vd";
		String hashPassword = DiscuzHashPassword.getHashPassword(password, salt);
		Assert.assertEquals("48577990717ce63726608c92c6ab257f", hashPassword);
	}
	
}
