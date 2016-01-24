/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @ClassName:ReflectionsTest 
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @author:张孟志
 * @date:2016年1月24日 上午10:21:47 
 * @version V1.0
 * 说明：修改springside的单元测试
 * org.assertj.core.api.Assertions.* --> org.junit.*
 */
public class ReflectionsTest {

	@Test
	public void getAndSetFieldValue() {
		TestBean bean = new TestBean();
		// 无需getter函数, 直接读取privateField
		Assert.assertEquals(1, Reflections.getFieldValue(bean, "privateField"));
		// 绕过将publicField+1的getter函数,直接读取publicField的原始值
		Assert.assertEquals(1, Reflections.getFieldValue(bean, "publicField"));

		bean = new TestBean();
		// 无需setter函数, 直接设置privateField
		Reflections.setFieldValue(bean, "privateField", 2);
		Assert.assertEquals(2, bean.inspectPrivateField());

		// 绕过将publicField+1的setter函数,直接设置publicField的原始值
		Reflections.setFieldValue(bean, "publicField", 2);
		Assert.assertEquals(2, bean.inspectPublicField());

		// 在junit中判断指定异常的抛出
		// JUnit 4 has support for this:
		// @Test(expected=IndexOutOfBoundsException.class)
		boolean getFieldValue = false;
		try {
			Reflections.getFieldValue(bean, "notExist");
		} catch (IllegalArgumentException e) {
			getFieldValue = true;
		}
		Assert.assertTrue(getFieldValue);

		boolean setFieldValue = false;
		try {
			Reflections.setFieldValue(bean, "notExist", 2);
		} catch (IllegalArgumentException e) {
			setFieldValue = true;
		}
		Assert.assertTrue(setFieldValue);

	}

	@Test
	public void invokeGetterAndSetter() {
		TestBean bean = new TestBean();
		Assert.assertEquals(bean.inspectPublicField() + 1, Reflections.invokeGetter(bean, "publicField"));

		bean = new TestBean();
		// 通过setter的函数将+1
		Reflections.invokeSetter(bean, "publicField", 10);
		Assert.assertEquals(10 + 1, bean.inspectPublicField());
	}

	@Test
	public void invokeMethod() {
		TestBean bean = new TestBean();
		// 使用函数名+参数类型的匹配
		Assert.assertEquals("hello calvin", 
				Reflections
				.invokeMethod(bean, "privateMethod", new Class[] { String.class }, new Object[] { "calvin" }));

		// 仅匹配函数名
		Assert.assertEquals("hello calvin", Reflections.invokeMethodByName(bean, "privateMethod", new Object[] { "calvin" }));

		// 函数名错
		boolean notExistMethod = false;
		try {
			Reflections.invokeMethod(bean, "notExistMethod", new Class[] { String.class }, new Object[] { "calvin" });
		} catch (IllegalArgumentException e) {
			notExistMethod = true;
		}
		Assert.assertTrue(notExistMethod);

		// 参数类型错
		boolean privateMethod = false;
		try {
			Reflections.invokeMethod(bean, "privateMethod", new Class[] { Integer.class }, new Object[] { "calvin" });
		} catch (RuntimeException e) {
			privateMethod = true;
		}
		Assert.assertTrue(privateMethod);

		// 函数名错
		boolean notExistMethod2 = false;
		try {
			Reflections.invokeMethodByName(bean, "notExistMethod", new Object[] { "calvin" });
		} catch (IllegalArgumentException e) {
			notExistMethod2 = true;
		}
		Assert.assertTrue(notExistMethod2);

	}

	@Test
	public void getSuperClassGenricType() {
		// 获取第1，2个泛型类型
		Assert.assertEquals(String.class, Reflections.getClassGenricType(TestBean.class));
		Assert.assertEquals(Long.class, Reflections.getClassGenricType(TestBean.class, 1));

		// 定义父类时无泛型定义
		Assert.assertEquals(Object.class, Reflections.getClassGenricType(TestBean2.class));

		// 无父类定义
		Assert.assertEquals(Object.class, Reflections.getClassGenricType(TestBean3.class));
	}

	@Test
	public void convertReflectionExceptionToUnchecked() {
		IllegalArgumentException iae = new IllegalArgumentException();
		// ReflectionException,normal
		RuntimeException e = Reflections.convertReflectionExceptionToUnchecked(iae);
		Assert.assertEquals(iae, e.getCause());

		// InvocationTargetException,extract it's target exception.
		Exception ex = new Exception();
		e = Reflections.convertReflectionExceptionToUnchecked(new InvocationTargetException(ex));
		Assert.assertEquals(ex, e.getCause());

		// UncheckedException, ignore it.
		RuntimeException re = new RuntimeException("abc");
		e = Reflections.convertReflectionExceptionToUnchecked(re);
		// assertThat(e, containsString("abc"));

		// Unexcepted Checked exception.
		e = Reflections.convertReflectionExceptionToUnchecked(ex);
		// assertThat(e, containsString("Unexpected Checked Exception."));
	}

	public static class ParentBean<T, ID> {
	}

	public static class TestBean extends ParentBean<String, Long> {
		/** 没有getter/setter的field */
		private int privateField = 1;
		/** 有getter/setter的field */
		private int publicField = 1;

		// 通過getter函數會比屬性值+1
		public int getPublicField() {
			return publicField + 1;
		}

		// 通過setter函數會被比輸入值加1
		public void setPublicField(int publicField) {
			this.publicField = publicField + 1;
		}

		public int inspectPrivateField() {
			return privateField;
		}

		public int inspectPublicField() {
			return publicField;
		}

		private String privateMethod(String text) {
			return "hello " + text;
		}
	}

	public static class TestBean2 extends ParentBean {
	}

	public static class TestBean3 {

		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
