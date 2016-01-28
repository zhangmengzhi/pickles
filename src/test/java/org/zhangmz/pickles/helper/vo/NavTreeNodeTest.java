/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper.vo;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.zhangmz.pickles.modules.convert.JsonMapper;

/**
 * Title:NavTreeNodeTest.java
 * Description:TODO(用一句话描述该文件做什么)
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月28日 下午3:01:37
 * 说明：
 */
public class NavTreeNodeTest {
	
	@Test
	public void pcg() {
		NavtreeNode ntn = new NavtreeNode(); 		ntn.setText("根节点"); 		ntn.setValue("0");		
		NavtreeNode ntnP1 = new NavtreeNode(); 		ntnP1.setText("父1"); 		ntnP1.setValue("p1");		
		NavtreeNode ntnP2 = new NavtreeNode();		ntnP2.setText("父2");		ntnP2.setValue("p2");		
		NavtreeNode ntnP3 = new NavtreeNode();		ntnP3.setText("父3");		ntnP3.setValue("p3");		
		NavtreeNode ntnP4 = new NavtreeNode();		ntnP4.setText("父4");		ntnP4.setValue("p4");		
		NavtreeNode ntnP5 = new NavtreeNode();		ntnP5.setText("父5");		ntnP5.setValue("p5");		
		NavtreeNode ntnC1 = new NavtreeNode();		ntnC1.setText("子1");		ntnC1.setValue("c1");		
		NavtreeNode ntnC2 = new NavtreeNode();		ntnC2.setText("子2");		ntnC2.setValue("c2");		
		NavtreeNode ntnC3 = new NavtreeNode();		ntnC3.setText("子3");		ntnC3.setValue("c3");		
		NavtreeNode ntnG1 = new NavtreeNode();		ntnG1.setText("孙1");		ntnG1.setValue("g1");		
		NavtreeNode ntnG2 = new NavtreeNode();		ntnG2.setText("孙2");		ntnG2.setValue("g2");
		
		List<NavtreeNode> cg1 = new ArrayList<>();		cg1.add(ntnC1);		cg1.add(ntnC2);
		ntnP2.setNodes(cg1);
		
		List<NavtreeNode> cg2 = new ArrayList<>();		cg2.add(ntnC3);
		// 给ntnC3带入nodes
		List<NavtreeNode> cg3 = new ArrayList<>();		cg3.add(ntnG1);		cg3.add(ntnG2);		ntnC3.setNodes(cg3);		
		ntnP3.setNodes(cg2);
		
		// 所有父节点
		List<NavtreeNode> cg4 = new ArrayList<>();
		cg4.add(ntnP1);		cg4.add(ntnP2);		cg4.add(ntnP3);		cg4.add(ntnP4);		cg4.add(ntnP5);
		ntn.setNodes(cg4);
		
		JsonMapper binder = JsonMapper.nonDefaultMapper();
		String beanString = binder.toJson(cg4);
		System.out.println(beanString);
		String beanStringRoot = binder.toJson(ntn);
		System.out.println(beanStringRoot);
	}
}
