/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Title:UserController.java
 * Description:用户帐户管理
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月28日 上午9:08:49
 * 说明：用户帐户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/account")
	public ModelAndView account(@RequestParam(value="TOKEN", required=false) String token) {		
		ModelAndView result = new ModelAndView("user/account");		
		return result;
    }
}
