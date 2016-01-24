/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import org.junit.Assert;
import java.io.IOException;
import org.junit.Test;

/**
 * 
 * @ClassName:ExceptionsTest 
 * @Description:
 * @author:张孟志
 * @date:2016年1月24日 上午10:02:49 
 * @version V1.0
 * 说明：修改springside的单元测试
 * org.assertj.core.api.Assertions.* --> org.junit.*
 */
public class ExceptionsTest {

	@Test
	public void unchecked() {
		// convert Exception to RuntimeException with cause
		Exception exception = new Exception("my exception");
		RuntimeException runtimeException = Exceptions.unchecked(exception);
		Assert.assertEquals(exception, runtimeException.getCause());

		// do nothing of RuntimeException
		RuntimeException runtimeException2 = Exceptions.unchecked(runtimeException);
		Assert.assertTrue(runtimeException.equals(runtimeException2));
	}

	@Test
	public void getStackTraceAsString() {
		Exception exception = new Exception("my exception");
		RuntimeException runtimeException = new RuntimeException(exception);

		String stack = Exceptions.getStackTraceAsString(runtimeException);
		System.out.println(stack);
	}

	@Test
	public void isCausedBy() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		Assert.assertTrue(Exceptions.isCausedBy(runtimeException, IOException.class));
		Assert.assertTrue(Exceptions.isCausedBy(runtimeException, IllegalStateException.class, IOException.class));
		Assert.assertTrue(Exceptions.isCausedBy(runtimeException, Exception.class));
		Assert.assertFalse(Exceptions.isCausedBy(runtimeException, IllegalAccessException.class));		
	}

}
