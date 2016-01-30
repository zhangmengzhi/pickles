/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper.aop;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.helper.NavtreeHelper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Title:NavBarHelperAOP.java
 * Description:导航栏切面，为页面添加导航栏数据
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月30日 上午8:48:39
 * 说明：导航栏切面，为页面添加导航栏数据
 */
@Aspect
@Component
public class NavTreeHelperAOP {
	
	private static Logger logger = LoggerFactory.getLogger(NavTreeHelperAOP.class);

    @Autowired
    private NavtreeHelper navtreeHelper;

	public static final String HTMLTREEKEY = "NavTreeHelperAOP_NAV_TREE_HTML";
	
	// 注入配置值  30分钟过期  30X60=1800
	@Value("${app.NavTreeHelperAOPTimeoutSecs:1800}")
	private int NavTreeHelperAOPTimeoutSecs;

	// guava cache
 	private Cache<String, String> navTree;

 	@PostConstruct
 	public void init() {
 		logger.debug("导航栏缓存过期时间设置（秒）： " + NavTreeHelperAOPTimeoutSecs);
 		// expireAfterAccess(long, TimeUnit)  这个方法是根据某个键值对最后一次访问之后多少时间后移除
 		// expireAfterWrite(long, TimeUnit)   这个方法是根据某个键值对被创建或值被替换后多少时间移除
 		// navTree = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(NavTreeHelperAOPTimeoutSecs, TimeUnit.SECONDS).build();
 		navTree = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(NavTreeHelperAOPTimeoutSecs, TimeUnit.SECONDS).build();
 	}
 	
	
    @Pointcut("execution(org.springframework.web.servlet.ModelAndView org.zhangmz.pickles.controller.picklejar..*(..)) ")
    private void actionMethod() {}
    
    @Around("actionMethod()")
	public Object navTreeAroundService(ProceedingJoinPoint joinpoint) throws Throwable {
    	ModelAndView result = (ModelAndView)joinpoint.proceed();
    	
    	String html = navTree.getIfPresent(HTMLTREEKEY);
    	if(StringUtils.isBlank(html)){
    		logger.debug("重新获取导航栏HTML缓存。");
    		html = navtreeHelper.getNavTreeHtml();
    		navTree.put(HTMLTREEKEY, html);
    	}
    	
		result.addObject("navBar", html);		 
		 return result;
	}
}
