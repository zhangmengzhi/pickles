/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.modules.constants.UserUrl;

/**
 * Title:AdminMainController.java
 * Description:控制台主页控制器
 * @author:张孟志
 * @date:2016年1月25日 下午7:58:13
 * 说明：
 */
@Controller
@RequestMapping("/user")
public class UserMainController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam("TOKEN") String token) {
		ModelAndView result = new ModelAndView(UserUrl.mainPage);
		
        result.addObject("TOKEN", token);
		return result;
    }
	
	@RequestMapping("/blank")
	public ModelAndView blank(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("user/blank2");		
		return result;
    }

}
