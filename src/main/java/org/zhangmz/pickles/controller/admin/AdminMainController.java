/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.helper.constants.AdminUrl;
import org.zhangmz.pickles.helper.AdminMainHelper;

/**
 * Title:AdminMainController.java
 * Description:控制台主页控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月25日 下午7:58:13
 * 说明：
 */
@Controller
@RequestMapping("/admin")
public class AdminMainController {

    @Autowired
    private AdminMainHelper adminMainHelper;
    
    //modify by 张孟志 2016年1月26日 下午4:14:04 使用AOP重构权限管理 begin
    /*
    @Autowired
    private AuthorityHelper authorityHelper;
    
	@RequestMapping(value = "/main", method = RequestMethod.GET)
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
	*/

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam("TOKEN") String token) {
		ModelAndView result = new ModelAndView(AdminUrl.mainPage);
		result.addObject("mainInfo", adminMainHelper.getMainInfo(token));
        result.addObject("TOKEN", token);
		return result;
    }
	
	//modify by 张孟志 2016年1月26日 下午4:14:04 使用AOP重构权限管理 end
	
	@RequestMapping("/blank")
	public ModelAndView blank(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/blank2");		
		return result;
    }
	
	@RequestMapping("/buttons")
	public ModelAndView buttons(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/buttons2");		
		return result;
    }
	
	@RequestMapping("/calendar")
	public ModelAndView calendar(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/calendar");		
		return result;
    }
	
	@RequestMapping("/dropzone")
	public ModelAndView dropzone(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/dropzone");		
		return result;
    }
	
	@RequestMapping("/elements")
	public ModelAndView elements(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/elements");		
		return result;
    }
	
	@RequestMapping("/faq")
	public ModelAndView faq(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/faq");		
		return result;
    }
	
	@RequestMapping("/form-elements")
	public ModelAndView formElements(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/form-elements");		
		return result;
    }
	
	@RequestMapping("/form-wizard")
	public ModelAndView formWizard(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/form-wizard");		
		return result;
    }
	
	@RequestMapping("/gallery")
	public ModelAndView gallery(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/gallery");		
		return result;
    }
	
	@RequestMapping("/grid")
	public ModelAndView grid(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/grid");		
		return result;
    }
	
	@RequestMapping("/inbox")
	public ModelAndView inbox(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/inbox");		
		return result;
    }
	
	@RequestMapping("/invoice")
	public ModelAndView invoice(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/invoice");		
		return result;
    }
	
	@RequestMapping("/jqgrid")
	public ModelAndView jqgrid(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/jqgrid");		
		return result;
    }
	
	@RequestMapping("/jquery-ui")
	public ModelAndView jqueryUi(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/jquery-ui");		
		return result;
    }
	
	@RequestMapping("/nestable-list")
	public ModelAndView nestableList(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/nestable-list");		
		return result;
    }
	
	@RequestMapping("/pricing")
	public ModelAndView pricing(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/pricing");		
		return result;
    }
	
	@RequestMapping("/profile")
	public ModelAndView profile(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/profile");		
		return result;
    }
	
	@RequestMapping("/tables")
	public ModelAndView tables(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/tables");		
		return result;
    }
	
	@RequestMapping("/timeline")
	public ModelAndView timeline(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/timeline");		
		return result;
    }
	
	@RequestMapping("/treeview")
	public ModelAndView treeview(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/treeview");		
		return result;
    }
	
	@RequestMapping("/typography")
	public ModelAndView typography(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/typography");		
		return result;
    }
	
	@RequestMapping("/widgets")
	public ModelAndView widgets(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/widgets");		
		return result;
    }
	
	@RequestMapping("/wysiwyg")
	public ModelAndView wysiwyg(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/wysiwyg");		
		return result;
    }
}
