/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import org.junit.Test;

/**
 * 
 * @ClassName:IdentitiesTest 
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @author:张孟志
 * @date:2016年1月24日 上午10:21:17 
 * @version V1.0
 * 说明：修改springside的单元测试
 * org.assertj.core.api.Assertions.* --> org.junit.*
 */
public class IdentitiesTest {

	@Test
	public void demo() {
		System.out.println("uuid: " + Ids.uuid());
		System.out.println("uuid2:" + Ids.uuid2());
		System.out.println("randomLong:  " + Ids.randomLong());
		System.out.println("randomBase62:" + Ids.randomBase62(7));
	}
}
