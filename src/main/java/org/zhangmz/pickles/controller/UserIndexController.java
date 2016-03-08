/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zhangmz.pickles.modules.constants.AdminUrl;
import org.zhangmz.pickles.modules.constants.UserUrl;
import org.zhangmz.pickles.service.AccountService;

/**
 * Title:AdminIndexController.java
 * Description:普通用户首页（登录页）控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 下午3:15:00
 * 说明：普通用户首页（登录页）控制器
 */
@Controller
@RequestMapping("/user")
public class UserIndexController {

	@Autowired
    private AccountService accountService;
    
	@RequestMapping
	String home() {
		return UserUrl.redirectIndexController;
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView result = new ModelAndView(UserUrl.indexPage);
		return result;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView result = new ModelAndView(UserUrl.loginPage);
		return result;
    }
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录请求
	 * @param groupCode   用户组编码
	 * @param phoneEmail  手机号码或Email
	 * @param password    密码
	 * @param redirectAttributes
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午7:14:05
	 * 说明：根据用户组编码、手机号码或Email、密码登录
	 * 用户组编码可以改为用户组ID，但是会对用户泄露编码方式
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value="groupCode", required=false) String groupCode, 
						@RequestParam("phoneEmail") String phoneEmail, 
						@RequestParam("password") String password, 
						RedirectAttributes redirectAttributes) {
		String token;
		String url;
		
		try {
			// TODO 登陆方法需要重构，以适应管理员/用户两种身份的登陆
			token = accountService.login(phoneEmail, password);
			redirectAttributes.addFlashAttribute("TOKEN", token);
			
			// 判断是否管理员（包括超级管理员）
			if(accountService.isAdmin(token)){
				// accountService.invalidateAccount(token);
				// 跳转到管理员页面
				url = AdminUrl.redirectMainController + "?TOKEN=" + token;
			} else {
				// redirectAttributes.addFlashAttribute("message", Messages.USER_NOT_ADMIN);
				url = UserUrl.redirectMainController + "?TOKEN=" + token;
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			url = UserUrl.redirectLoginController;
		}
		
		return url;
    }
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: 退出
	 * @param token
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午8:05:22
	 * 说明：清理用户登录信息
	 */
	@RequestMapping(value = "/logout")
	public String logout(@RequestParam("TOKEN") String token) {
		accountService.logout(token);
		return UserUrl.redirectLoginController;
    }
}
