/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.helper.constants.AdminUrl;
import org.zhangmz.pickles.helper.constants.Messages;

/**
 * Title:AuthorityHelperAOP.java
 * Description:权限管理切面
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月26日 下午3:49:39
 * 说明：
 */
@Aspect
@Component
public class AuthorityHelperAOP {
	
    @Autowired
    private AuthorityHelper authorityHelper;
	
    @Pointcut("execution(org.springframework.web.servlet.ModelAndView org.zhangmz.pickles.controller.admin.AdminMainController.*(..))")
    private void actionMethod() {}
    
//	/**
//	 * 
//	 * @Title: authorityBeforeService 
//	 * @Description: 前置增强对管理员控制台进行用户权限判断
//	 * @param token
//	 * @throws 
//	 * 增加人:张孟志
//	 * 增加日期:2016年1月26日 下午3:55:56
//	 * 说明：通过连接点切入，对管理员控制台进行用户权限判断
//	 * 		用户必须为管理员（包括超级管理员）
//	 */
//	@Before("execution(* org.zhangmz.pickles.controller.admin.AdminMainController.*(..)) && args(token,..)")    
//	public void authorityBeforeService(String token){
//		System.out.println ("前置增强对权限进行校验，TOKEN==" + token);
//		authorityHelper.isAdmin(token);
//	}
//	
//	@After("execution(* org.zhangmz.pickles.controller.admin.AdminMainController.*(..)) && args(token,..)") 
//	public void authorityAfterService(String token){
//		System.out.println ("后置增强对权限进行校验，TOKEN==" + token);
//		authorityHelper.isAdmin(token);
//	}
	
    @Around("actionMethod()")
	public Object authorityAroundService(ProceedingJoinPoint joinpoint) throws Throwable {
    	ModelAndView result = null;
		 try{
			 	// 计算服务时间 begin
	            // long start = System.currentTimeMillis();
	            
			 	// 获取方法参数值，第一个参数需要是TOKEN
	            Object[] args = joinpoint.getArgs();
	            
	            if(args.length > 0 
	            	&& authorityHelper.isAdmin((String) args[0])){
	            	// return joinpoint.proceed();
	            	result = ((ModelAndView)joinpoint.proceed()).addObject("TOKEN", args[0]);
	            }else{
	    			result = new ModelAndView(AdminUrl.loginPage);
	    	        result.addObject("message", Messages.USER_NOT_ADMIN);
	            }
	            
	            // long end = System.currentTimeMillis();
	            // System.out.println("end! performance took " + (end-start) + " milliseconds");
	            // 计算服务时间 end
	        }catch(Throwable e){
	            System.out.println(Messages.AOP_HAS_ERROR);
	        }
		 
		 return result;
	}
    
}
