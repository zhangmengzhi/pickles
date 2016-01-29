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
import org.zhangmz.pickles.service.NavtreeService;

/**
 * Title:NavtreeController.java
 * Description:导航栏控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月28日 下午5:22:14
 * 说明：
 */
@Controller
@RequestMapping("/admin/navtrees")
public class NavtreeController {
	
	@Autowired
	private NavtreeService navtreeService;
	
	@RequestMapping("")
	public ModelAndView navtree(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/navtree");
		// 获取导航栏的JSON数据
		String treeDataJson = navtreeService.getNavTreeString();
		result.addObject("treeDataJson", treeDataJson);
		return result;
    }
	
	
}
