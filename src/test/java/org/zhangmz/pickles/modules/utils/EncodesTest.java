/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @ClassName:EncodesTest 
 * @Description:
 * @author:张孟志
 * @date:2016年1月24日 上午9:58:30 
 * @version V1.0
 * 说明：修改springside的单元测试
 * org.assertj.core.api.Assertions.* --> org.junit.*
 */
public class EncodesTest {

	@Test
	public void hexEncode() {
		String input = "haha,i am a very long message";
		String result = Encodes.encodeHex(input.getBytes());
		Assert.assertEquals(input, new String(Encodes.decodeHex(result)));
	}

	@Test
	public void base64Encode() {
		String input = "haha,i am a very long message";
		String result = Encodes.encodeBase64(input.getBytes());
		Assert.assertEquals(input, new String(Encodes.decodeBase64(result)));
	}

	@Test
	public void base64UrlSafeEncode() {
		String input = "haha,i am a very long message";
		String result = Encodes.encodeUrlSafeBase64(input.getBytes());
		Assert.assertEquals(input, new String(Encodes.decodeBase64(result)));
	}

	@Test
	public void urlEncode() {
		String input = "http://locahost/?q=中文&t=1";
		String result = Encodes.urlEncode(input);
		System.out.println(result);

		Assert.assertEquals(input, Encodes.urlDecode(result));
	}

	@Test
	public void xmlEncode() {
		String input = "1>2";
		String result = Encodes.escapeXml(input);
		Assert.assertEquals("1&gt;2", result);
		Assert.assertEquals(input, Encodes.unescapeXml(result));
	}

	@Test
	public void html() {
		String input = "1>2";
		String result = Encodes.escapeHtml(input);
		Assert.assertEquals("1&gt;2", result);
		Assert.assertEquals(input, Encodes.unescapeHtml(result));
	}
}
