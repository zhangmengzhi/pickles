/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.controller.admin.constants.AdminUrl;
import org.zhangmz.pickles.helper.AdminMainHelper;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.helper.constants.Messages;

/**
 * Title:AdminMainController.java
 * Description:控制台主页控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 下午7:58:13
 * 说明：
 */
@Controller
@RequestMapping("/admin/main")
public class AdminMainController {

    @Autowired
    private AdminMainHelper adminMainHelper;
    
    @Autowired
    private AuthorityHelper authorityHelper;
    
	@RequestMapping
	public ModelAndView home(@RequestParam("TOKEN") String token) {
		ModelAndView result;
		// 需要判断是否为管理员，一个方案是使用AuthorityHelper做硬编码，另一个方案是使用AOP
		if(authorityHelper.isAdmin(token)){
			result = new ModelAndView(AdminUrl.mainPage);
		} else {
			result = new ModelAndView(AdminUrl.loginPage);
	        result.addObject("message", Messages.USER_NOT_ADMIN);
		}
        result.addObject("mainInfo", adminMainHelper.getMainInfo(token));
        result.addObject("TOKEN", token);
		return result;
    }
}
