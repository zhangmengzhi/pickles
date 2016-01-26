/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhangmz.pickles.helper.vo.MainInfo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Title:AdminMainHelper.java
 * Description:pickles主控制台帮助类
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月26日 上午8:53:10
 * 说明：pickles主控制台帮助类
 * 		用于获取admin/main页面上的统计信息，目前都写为固定值。
 */
@Component
public class AdminMainHelper {
	private static Logger logger = LoggerFactory.getLogger(AdminMainHelper.class);
	
	// 注入配置值  30分钟过期  5X60=300
	@Value("${app.loginTimeoutSecs:300}")
	private int loginTimeoutSecs;

	// guava cache
 	private Cache<String, MainInfo> mainInfos;

 	@PostConstruct
 	public void init() {
 		logger.debug("控制台信息缓存过期时间设置（秒）： " + loginTimeoutSecs);
 		mainInfos = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
 				.build();
 	}
 	
 	/**
 	 * 
 	 * @Title: getMainInfo 
 	 * @Description: 从缓存获取控制台主页信息
 	 * @param token
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月26日 上午9:07:27
 	 * 说明：从缓存获取控制台主页信息
 	 */
 	public MainInfo getMainInfo(String token) {
 		MainInfo mf = mainInfos.getIfPresent(token);

 		if(null == mf) {
 			mf = getMainInfoDB(token);
 			mainInfos.put(token, mf);
 		}
 		
 		return mf;
 	}
 	
 	/**
 	 * 
 	 * @Title: getMainInfoDB 
 	 * @Description: 从数据库获取控制台主页信息
 	 * @param token
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月26日 上午9:07:59
 	 * 说明：从数据库获取控制台主页信息
 	 */
 	public MainInfo getMainInfoDB(String token) {
 		// TODO 这里应该利用@Service查询数据库，获取控制台主页信息
 		// 目前就使用MainInfo的测试默认值
 		MainInfo mf = new MainInfo();

 		logger.debug(token + "重新获取控制台主页信息： " + mf.toString());
 		return mf;
 	} 		
}
