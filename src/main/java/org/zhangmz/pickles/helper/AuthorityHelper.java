/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhangmz.pickles.orm.model.Account;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Title:AuthorityHelper.java
 * Description:权限助手
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 上午9:04:24
 * 说明： pickles将自行实现一个简单的权限管理
 * 		用户按组管理权限  account.groudId
 * 		权限包括组权限、是否已登录、是否管理员
 * 		另外超级管理员有且只有一个，  account.id、account.groudId 必须为1
 * 		管理员account.groudId 为2
 * 
 * 		这个类应该是单例（Sping Bean默认为单例）
 */
@Component
public class AuthorityHelper {
	private static Logger logger = LoggerFactory.getLogger(AuthorityHelper.class);

	// 注入配置值  30分钟过期  30X60=1800
	@Value("${app.loginTimeoutSecs:1800}")
	private int loginTimeoutSecs;

    // guava cache
 	private Cache<String, Account> loginUsers;

 	@PostConstruct
 	public void init() {
 		logger.debug("登录信息缓存过期时间设置（秒）： " + loginTimeoutSecs);
 		loginUsers = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
 				.build();
 	}
 	
 	public void putAccount(String token, Account account) {
 		loginUsers.put(token, account);
 	}
 	
 	public Account getAccount(String token) {
 		return loginUsers.getIfPresent(token);
	}
 	
 	public void invalidateAccount(String token) {
 		loginUsers.invalidate(token);
	}
 	
 	/**
 	 * 
 	 * @Title: isAdministrator 
 	 * @Description: 是否超级管理员
 	 * @param token
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 上午9:22:47
 	 * 说明：超级管理员有且只有一个，  account.id、account.groudId 必须为1
 	 */
 	public boolean isAdministrator(String token) {
 		return hasLoginCache(token, 1);
	}

 	public boolean isAdministrator(Account account) {
 		return account!=null && 1==account.getId().intValue() && 1==account.getGroupId().intValue();
	}
 	
 	public Account getAdministratorAccount(String token) {
 		return getLoginAccount(token, 1);
	}
 	
 	/**
 	 * 
 	 * @Title: isAdmin 
 	 * @Description: 是否管理员
 	 * @param token
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 上午9:38:47
 	 * 说明：管理员account.groudId 为2
 	 */
 	public boolean isAdmin(String token) {
 		return hasLoginCache(token, 2);
	}
 	
 	public Account getAdminAccount(String token) {
 		return getLoginAccount(token, 2);
	}
 	
 	/**
 	 * 
 	 * @Title: isLogin 
 	 * @Description: 是否已登录
 	 * @param token
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 上午9:25:58
 	 * 说明：从登录缓存中获取登录信息，判断是否有登录
 	 */
 	public boolean isLogin(String token) { 		
 		return hasLoginCache(token, 3);
	}

 	public Account getLoginAccount(String token) { 		
 		return getLoginAccount(token, 3);
	}
 	
 	/**
 	 * 
 	 * @Title: hasLoginCacheAuthority 
 	 * @Description: 根据登录信息缓存获取权限信息 
 	 * @param token  
 	 * @param type   1是否超级管理员、2是否管理员、3是否已登录
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 上午9:35:11
 	 * 说明：重构是否超级管理员、管理员、已登录判断
 	 */
 	private boolean hasLoginCache (String token, int type) {
 		boolean bln = false;
 		if(StringUtils.isNotBlank(token)){
 			Account account = loginUsers.getIfPresent(token);
 			// 一般情况下，根据登录信息缓存判断是否超级管理员、管理员、已登录，只是判断条件不同 			
 			switch (type) {
			case 1:
				bln = (account != null) 
	 					&& (1 == account.getGroupId().intValue()) 
	 					&& (1 == account.getId().intValue());
				break;
			case 2:
				bln = (account != null) 
	 					&& (	(2 == account.getGroupId().intValue())
	 							|| (// 超级管理员
	 								(1 == account.getGroupId().intValue())
	 				 				&& (1 == account.getId().intValue())
	 				 				)
	 						);
				break;
			case 3:
				bln = account != null;
				break;
			default:
				break;
			} 			
 		}
 		
 		return bln;
 	}
 	
 	/**
 	 * 
 	 * @Title: getLoginCacheAuthority 
 	 * @Description: 1是否超级管理员、2是否管理员、3是否已登录
 	 * @param token
 	 * @param type
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 上午9:54:26
 	 * 说明：获取有相关权限的用户信息
 	 */
 	private Account getLoginAccount (String token, int type) {
 		Account account = null;
 		if(hasLoginCache(token, type)){
 			account = loginUsers.getIfPresent(token);
 		}
 		return account;
 	}
}
