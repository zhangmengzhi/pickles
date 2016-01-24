/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import org.junit.Assert;
import org.junit.Test;
import org.zhangmz.pickles.modules.utils.Cryptos;
import org.zhangmz.pickles.modules.utils.Encodes;

/**
 * 
 * @ClassName:CryptosTest 
 * @Description:
 * @author:张孟志
 * @date:2016年1月24日 上午9:56:56 
 * @version V1.0
 * 说明：修改springside的单元测试
 * org.assertj.core.api.Assertions.* --> org.junit.*
 */
public class CryptosTest {
	@Test
	public void mac() {
		String input = "foo message";

		// key可为任意字符串
		// byte[] key = "a foo key".getBytes();
		byte[] key = Cryptos.generateHmacSha1Key();
		Assert.assertTrue(key.length == 20);

		byte[] macResult = Cryptos.hmacSha1(input.getBytes(), key);
		System.out.println("hmac-sha1 key in hex      :" + Encodes.encodeHex(key));
		System.out.println("hmac-sha1 in hex result   :" + Encodes.encodeHex(macResult));

		Assert.assertTrue(Cryptos.isMacValid(macResult, input.getBytes(), key));
	}

	@Test
	public void aes() {
		byte[] key = Cryptos.generateAesKey();
		Assert.assertTrue(key.length == 16);
		String input = "foo message";

		byte[] encryptResult = Cryptos.aesEncrypt(input.getBytes(), key);
		String descryptResult = Cryptos.aesDecrypt(encryptResult, key);

		System.out.println("aes key in hex            :" + Encodes.encodeHex(key));
		System.out.println("aes encrypt in hex result :" + Encodes.encodeHex(encryptResult));

		Assert.assertEquals(input, descryptResult);
	}

	@Test
	public void aesWithIV() {
		byte[] key = Cryptos.generateAesKey();
		byte[] iv = Cryptos.generateIV();
		Assert.assertTrue(key.length == 16);
		Assert.assertTrue(iv.length == 16);

		String input = "foo message";

		byte[] encryptResult = Cryptos.aesEncrypt(input.getBytes(), key, iv);
		String descryptResult = Cryptos.aesDecrypt(encryptResult, key, iv);

		System.out.println("aes key in hex            :" + Encodes.encodeHex(key));
		System.out.println("iv in hex                 :" + Encodes.encodeHex(iv));
		System.out.println("aes encrypt in hex result :" + Encodes.encodeHex(encryptResult));
		
		Assert.assertEquals(input, descryptResult);
	}
}
