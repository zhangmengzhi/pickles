/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.google.common.collect.Lists;

/**
 * 
 * @ClassName:Collecitons3Test 
 * @Description: 
 * @author:张孟志
 * @date:2016年1月24日 上午9:29:38 
 * @version V1.0
 * 说明：修改springside的单元测试
 * org.assertj.core.api.Assertions.* --> org.junit.*
 */
public class Collecitons3Test {

	@Test
	public void convertElementPropertyToString() {
		TestBean3 bean1 = new TestBean3();
		bean1.setId(1);
		TestBean3 bean2 = new TestBean3();
		bean2.setId(2);

		List list = Lists.newArrayList(bean1, bean2);

		Assert.assertEquals("1,2", Collections3.extractToString(list, "id", ","));
	}

	@Test
	public void convertElementPropertyToList() {
		TestBean3 bean1 = new TestBean3();
		bean1.setId(1);
		TestBean3 bean2 = new TestBean3();
		bean2.setId(2);

		List list = Lists.newArrayList(bean1, bean2);
		List result = Collections3.extractToList(list, "id");
		
		// 手动生成一个结果对象
		List expecteds = Lists.newArrayList(1, 2);
		
		Assert.assertTrue(expecteds.equals(result));
	}

	@Test
	public void convertCollectionToString() {
		List<String> list = Lists.newArrayList("aa", "bb");
		String result = Collections3.convertToString(list, ",");
		Assert.assertEquals("aa,bb", result);

		result = Collections3.convertToString(list, "<li>", "</li>");
		Assert.assertEquals("<li>aa</li><li>bb</li>", result);
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
