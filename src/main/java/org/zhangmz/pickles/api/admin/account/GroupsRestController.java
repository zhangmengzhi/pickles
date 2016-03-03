/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.api.admin.account;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.helper.vo.SimpleResponse;
import org.zhangmz.pickles.orm.model.Group;
import org.zhangmz.pickles.orm.model.Group2;
import org.zhangmz.pickles.service.GroupService;

/**
 * 
 * @ClassName:GroupsRestController 
 * @Description:用户组处理控制器
 * @author:张孟志
 * @date:2016年3月3日 上午10:28:08 
 * @version V1.0
 * 说明：用户组处理控制器
 */
@RestController
@RequestMapping("/api/admin/groups")
public class GroupsRestController {
	private static Logger logger = LoggerFactory.getLogger(GroupsRestController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
    @Autowired
    private GroupService groupService;
    
    @RequestMapping
    public List<Group> search(@RequestParam("TOKEN") String token) {
        // 获取所有的用户组数据
        List<Group> groupList = groupService.search(new Group());
        logger.debug(binder.toJson(groupList));
        return groupList;
    }
    
	/**
     * 
     * @param token
     * @param oper    	"edit", // 当在edit模式中提交数据时，操作的名称
     * 					"add", // 当在add模式中提交数据时，操作的名称
     * 					"del", // 当在delete模式中提交数据时，操作的名称
     * @param group
     * @return
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SimpleResponse save(@RequestParam("TOKEN") String token, 
    		@RequestParam("oper") String oper,
    		Group2 group2) {
    	SimpleResponse sr = new SimpleResponse();
        
//        // 注意jqGrid对于新增记录，id默认为""_empty""
//        // jqGrid 使用inlineNav 会有不同表现，例如"jqg3"
//        if("_empty".equals(group2.getId())){
//        	group2.setId(null);
//        }
        
        // 对象复制，属性相同，id的类型不同
        // Group group = group2.copyProperties();
        Group group;
        
        if("add".equals(oper))group2.setId(null);
        try {
        	group = group2.copyProperties();
		} catch (Exception e) {
			// 新增记录没有刷新立即编辑，ID转换异常
			logger.debug(e.getMessage());
			e.printStackTrace();
	        sr.setCode(Codes.FAILURE_FALSE_NUMBER);
			sr.setMessage(Messages.SYSTEM_BUSY);
			return sr;
		}
        
        // 数据库操作会有异常抛出，例如：插入数据违反唯一索引
        try {
            switch (oper) {
    		case "edit":
    	        groupService.save(group);
    	        sr.setMessage(Messages.UPDATE_SUCCESS);
    			break;
    			
    		case "add":
    	        groupService.save(group);;
    	        sr.setMessage(Messages.INSERT_SUCCESS);
    			break;
    			
    		case "del":
    			groupService.deleteById(group.getId());
    			sr.setMessage(Messages.DELETE_SUCCESS);
    			break;

    		default:
    			break;
    		}

            sr.setCode(Codes.SUCCESS_TRUE_NUMBER);
			sr.setGroupId(group.getId());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
	        sr.setCode(Codes.FAILURE_FALSE_NUMBER);
			sr.setMessage(Messages.SYSTEM_BUSY);
		}
        
        logger.debug(binder.toJson(sr));
        return sr;
    }
}
