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
		NavTreeNode ntn = new NavTreeNode();
		ntn.setText("根节点");
		ntn.setValue("0");
		
		NavTreeNode ntnP1 = new NavTreeNode();
		ntnP1.setText("父1");
		ntnP1.setValue("p1");
		
		NavTreeNode ntnP2 = new NavTreeNode();
		ntnP2.setText("父2");
		ntnP2.setValue("p2");
		
		NavTreeNode ntnP3 = new NavTreeNode();
		ntnP3.setText("父3");
		ntnP3.setValue("p3");
		
		NavTreeNode ntnP4 = new NavTreeNode();
		ntnP4.setText("父4");
		ntnP4.setValue("p4");
		
		NavTreeNode ntnP5 = new NavTreeNode();
		ntnP5.setText("父5");
		ntnP5.setValue("p5");
		
		NavTreeNode ntnC1 = new NavTreeNode();
		ntnC1.setText("子1");
		ntnC1.setValue("c1");
		
		NavTreeNode ntnC2 = new NavTreeNode();
		ntnC2.setText("子2");
		ntnC2.setValue("c2");
		
		NavTreeNode ntnC3 = new NavTreeNode();
		ntnC3.setText("子3");
		ntnC3.setValue("c3");
		
		NavTreeNode ntnG1 = new NavTreeNode();
		ntnG1.setText("孙1");
		ntnG1.setValue("g1");
		
		NavTreeNode ntnG2 = new NavTreeNode();
		ntnG2.setText("孙2");
		ntnG2.setValue("g2");
		
		List<NavTreeNode> cg1 = new ArrayList<>();
		cg1.add(ntnC1);
		cg1.add(ntnC2);
		ntnP2.setNodes(cg1);
		
		List<NavTreeNode> cg2 = new ArrayList<>();
		cg2.add(ntnC3);		
		// 给ntnC3带入nodes
		List<NavTreeNode> cg3 = new ArrayList<>();
		cg3.add(ntnG1);
		cg3.add(ntnG2);
		ntnC3.setNodes(cg3);		
		ntnP3.setNodes(cg2);
		
		List<NavTreeNode> cg4 = new ArrayList<>();
		cg4.add(ntnP1);
		cg4.add(ntnP2);
		cg4.add(ntnP3);
		cg4.add(ntnP4);
		cg4.add(ntnP5);
		
		
		JsonMapper binder = JsonMapper.nonDefaultMapper();
		String beanString = binder.toJson(cg4);
		System.out.println(beanString);
	}
}
