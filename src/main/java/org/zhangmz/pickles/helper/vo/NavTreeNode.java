/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper.vo;

import java.util.List;

/**
 * Title:NavTree.java
 * Description:导航栏树结构
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月28日 下午2:56:46
 * 说明：导航栏树结构
 */
public class NavTreeNode {
	// 节点文本
	private String text;
	// 节点值，对应navtree表中的id字段
	private String value;
	// 直接点
	private List<NavTreeNode> nodes;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<NavTreeNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<NavTreeNode> nodes) {
		this.nodes = nodes;
	}	
}
