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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.orm.model.Group;
import org.zhangmz.pickles.service.GroupService;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName:GroupsController 
 * @Description:用户组处理控制器
 * @author:张孟志
 * @date:2016年3月3日 上午10:29:03 
 * @version V1.0
 * 说明：用户组处理控制器
 */
@Controller
@RequestMapping("/admin/groups")
public class GroupsController {
	private static Logger logger = LoggerFactory.getLogger(GroupsController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
    @Autowired
    private GroupService groupService;
    
    @RequestMapping
    public ModelAndView search(@RequestParam("TOKEN") String token, @RequestParam(required=false) Group group) {
 		if(null == group) group = new Group();
 		
        ModelAndView result = new ModelAndView("admin/account/groups");
//        // 获取所有的用户组数据
//        List<Group> groupList = groupService.search(group);
//        // result.addObject("groups", binder.toJson(groupList));
//        result.addObject("pageInfo", new PageInfo<Group>(groupList));
//        result.addObject("queryParam", group);
//        result.addObject("page", group.getPage());
//        result.addObject("rows", group.getRows());        
//        result.addObject("TOKEN", token);
//        
//        logger.debug(binder.toJson(result));
        return result;
    }
   
}
