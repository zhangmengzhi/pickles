/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.helper.constants.AdminUrl;
import org.zhangmz.pickles.helper.vo.NavTreeNode;
import org.zhangmz.pickles.modules.convert.JsonMapper;
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
        // result.addObject("TOKEN", token);
		return result;
    }
	*/

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam("TOKEN") String token) {
		ModelAndView result = new ModelAndView(AdminUrl.mainPage);
		result.addObject("mainInfo", adminMainHelper.getMainInfo(token));
        // result.addObject("TOKEN", token);
		return result;
    }
	
	//modify by 张孟志 2016年1月26日 下午4:14:04 使用AOP重构权限管理 end
	
	@RequestMapping("/blank")
	public ModelAndView blank(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/blank");		
		return result;
    }
	
	@RequestMapping("/buttons")
	public ModelAndView buttons(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/buttons");		
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
	
	@RequestMapping("/navtree")
	public ModelAndView navtree(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/navtree");
		// 获取导航栏的JSON数据
		String treeDataJson = tempMethod();
		result.addObject("treeDataJson", treeDataJson);
		return result;
    }
	
	public String tempMethod(){
		NavTreeNode ntn = new NavTreeNode(); 		ntn.setText("根节点"); 		ntn.setValue("0");		
		NavTreeNode ntnP1 = new NavTreeNode(); 		ntnP1.setText("父1"); 		ntnP1.setValue("p1");		
		NavTreeNode ntnP2 = new NavTreeNode();		ntnP2.setText("父2");		ntnP2.setValue("p2");		
		NavTreeNode ntnP3 = new NavTreeNode();		ntnP3.setText("父3");		ntnP3.setValue("p3");		
		NavTreeNode ntnP4 = new NavTreeNode();		ntnP4.setText("父4");		ntnP4.setValue("p4");		
		NavTreeNode ntnP5 = new NavTreeNode();		ntnP5.setText("父5");		ntnP5.setValue("p5");		
		NavTreeNode ntnC1 = new NavTreeNode();		ntnC1.setText("子1");		ntnC1.setValue("c1");		
		NavTreeNode ntnC2 = new NavTreeNode();		ntnC2.setText("子2");		ntnC2.setValue("c2");		
		NavTreeNode ntnC3 = new NavTreeNode();		ntnC3.setText("子3");		ntnC3.setValue("c3");		
		NavTreeNode ntnG1 = new NavTreeNode();		ntnG1.setText("孙1");		ntnG1.setValue("g1");		
		NavTreeNode ntnG2 = new NavTreeNode();		ntnG2.setText("孙2");		ntnG2.setValue("g2");
		
		List<NavTreeNode> cg1 = new ArrayList<>();		cg1.add(ntnC1);		cg1.add(ntnC2);
		ntnP2.setNodes(cg1);
		
		List<NavTreeNode> cg2 = new ArrayList<>();		cg2.add(ntnC3);
		// 给ntnC3带入nodes
		List<NavTreeNode> cg3 = new ArrayList<>();		cg3.add(ntnG1);		cg3.add(ntnG2);		ntnC3.setNodes(cg3);		
		ntnP3.setNodes(cg2);
		
		// 所有父节点
		List<NavTreeNode> cg4 = new ArrayList<>();
		cg4.add(ntnP1);		cg4.add(ntnP2);		cg4.add(ntnP3);		cg4.add(ntnP4);		cg4.add(ntnP5);
		ntn.setNodes(cg4);
		
		JsonMapper binder = JsonMapper.nonDefaultMapper();
		String beanString = binder.toJson(cg4);
		System.out.println(beanString);
		String beanStringRoot = binder.toJson(ntn);
		System.out.println(beanStringRoot);
		return beanString;
	}
}
