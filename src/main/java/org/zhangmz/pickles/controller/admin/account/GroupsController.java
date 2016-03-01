/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.admin.account;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.orm.model.Account;
import org.zhangmz.pickles.orm.model.Group;
import org.zhangmz.pickles.service.GroupService;

/**
 * Title:AccountController.java
 * Description:账户处理控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月27日 上午11:07:34
 * 说明：账户处理控制器
 */
@Controller
@RequestMapping("/admin/groups")
public class GroupsController {
	private static Logger logger = LoggerFactory.getLogger(GroupsController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
    @Autowired
    private GroupService groupService;
    
    @RequestMapping
    public ModelAndView search(@RequestParam("TOKEN") String token) {
        ModelAndView result = new ModelAndView("admin/account/groups");
        // 获取所有的用户组数据
        // List<Group> groupList = groupService.searchAll(new Group());
        List<Group> groupList = groupService.search(new Group());
        
        // String json = binder.toJson(groupList);
        // logger.debug(json);
        // result.addObject("groups", json);
        result.addObject("groups", binder.toJson(groupList));
        
        result.addObject("TOKEN", token);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("TOKEN") String token, 
    		@RequestParam("oper") String oper) {
        ModelAndView result = new ModelAndView("admin/account/groups");
        // String message = groups.getId() == null ? Messages.INSERT_SUCCESS : Messages.UPDATE_SUCCESS;
        
        System.out.println("ModelAndView org.zhangmz.pickles.controller.admin.account.GroupsController.save");
        
        
        result.addObject("TOKEN", token);
        return result;
    }
   
}
