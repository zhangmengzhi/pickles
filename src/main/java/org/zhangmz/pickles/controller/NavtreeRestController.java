/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.orm.model.Navtree;
import org.zhangmz.pickles.service.NavtreeService;

/**
 * Title:NavtreeRestController.java
 * Description:导航栏控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月29日 上午11:03:46
 * 说明：用于异步请求
 */
@RestController()
@RequestMapping("/api/admin/navtrees")
public class NavtreeRestController {
	
	@Autowired
	private NavtreeService navtreeService;

	@RequestMapping("/{id}")
	public Navtree home(@RequestParam("TOKEN") String token, @PathVariable int id) {		
		Navtree navtree = navtreeService.getById(id);		
		return navtree;
    }
}
