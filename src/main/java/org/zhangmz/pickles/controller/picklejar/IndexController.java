/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.picklejar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.zhangmz.pickles.service.NavtreeService;

/**
 * Title:IndexController.java
 * Description:主页控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 上午11:08:36
 * 说明：
 */

@Controller
@EnableWebMvc
@RequestMapping("/")
public class IndexController {
    @Autowired
    private NavtreeService navtreeService;
	
	@RequestMapping
	String home() {
		return "redirect:/index";
    }
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("index");
		return result;
    }
	
	@RequestMapping("/contact")
	public ModelAndView contact(@RequestParam(value="TOKEN", required=false) String token) {		
		ModelAndView result = new ModelAndView("contact");		
		return result;
    }
	
	@RequestMapping("/category")
	public ModelAndView category(@RequestParam(value="TOKEN", required=false) String token) {		
		ModelAndView result = new ModelAndView("category");		
		return result;
    }
	
	@RequestMapping("/cart")
	public ModelAndView cart(@RequestParam(value="TOKEN", required=false) String token) {		
		ModelAndView result = new ModelAndView("cart");		
		return result;
    }
	
	@RequestMapping("/product")
	public ModelAndView product(@RequestParam(value="TOKEN", required=false) String token) {		
		ModelAndView result = new ModelAndView("product");		
		return result;
    }
}
