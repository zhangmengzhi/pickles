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
import org.zhangmz.pickles.helper.constants.AdminUrl;
import org.zhangmz.pickles.helper.constants.Messages;
import org.zhangmz.pickles.service.AccountService;

/**
 * Title:AdminIndexController.java
 * Description:管理员首页（登录页）控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 下午3:15:00
 * 说明：管理员首页（登录页）控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

	@Autowired
    private AccountService accountService;
    
	@RequestMapping
	String home() {
		return AdminUrl.redirectIndexController;
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView result = new ModelAndView(AdminUrl.indexPage);
		return result;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView result = new ModelAndView(AdminUrl.loginPage);
		return result;
    }
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录请求
	 * @param phoneEmail  手机号码或Email
	 * @param password    密码
	 * @param redirectAttributes
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午7:14:05
	 * 说明：根据手机号码或Email、密码登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("phoneEmail") String phoneEmail, 
						@RequestParam("password") String password, 
						RedirectAttributes redirectAttributes) {
		String token;
		String url;
		
		try {
			token = accountService.login(phoneEmail, password);
			redirectAttributes.addFlashAttribute("TOKEN", token);
			
			// 判断是否管理员（包括超级管理员）
			if(accountService.isAdmin(token)){
				url = AdminUrl.redirectMainController + "?TOKEN=" + token;
			} else {
				accountService.invalidateAccount(token);
				redirectAttributes.addFlashAttribute("message", Messages.USER_NOT_ADMIN);
				url = AdminUrl.redirectLoginController;
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			url = AdminUrl.redirectLoginController;
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
		return AdminUrl.redirectLoginController;
    }
}
