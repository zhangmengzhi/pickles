/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:MainInfo.java
 * Description:控制台主页信息
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月26日 上午10:08:53
 * 说明：控制台主页信息
 */
public class MainInfo {
	private int tasks = 4;
	private int notices = 8;
	private int messages = 5;
	private String username = "管理员";
	private String userImageUrl = "/static/assets/avatars/user.jpg"; 		
	
	public int getTasks() {
	return tasks;
	}
	public void setTasks(int tasks) {
		this.tasks = tasks;
	}
	public int getNotices() {
		return notices;
	}
	public void setNotices(int notices) {
		this.notices = notices;
	}
	public int getMessages() {
		return messages;
	}
	public void setMessages(int messages) {
		this.messages = messages;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public String toString() {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);  
			return ReflectionToStringBuilder.toString(this);
		}
	}