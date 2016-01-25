/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Title:AdminIndexController.java
 * Description:管理员首页（登录页）控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 下午3:15:00
 * 说明：管理员首页（登录页）控制器
 */

@Controller
@EnableWebMvc
@RequestMapping("/admin")
public class AdminIndexController {
	
	@RequestMapping
	String home() {
		return "redirect:/admin/index";
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("admin/index");
		return result;
    }
}
