/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.api.admin.account;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.BeanMapper;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.helper.vo.Group2;
import org.zhangmz.pickles.modules.vo.IdName;
import org.zhangmz.pickles.modules.vo.PageRequest;
import org.zhangmz.pickles.helper.vo.SimpleResponse4Group;
import org.zhangmz.pickles.orm.model.Group;
import org.zhangmz.pickles.service.AccountService;
import org.zhangmz.pickles.service.GroupService;

import com.github.pagehelper.PageInfo;

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
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping
    public Map<String, Object> search(@RequestParam("TOKEN") String token, PageRequest request) {    	
    	Map<String, Object> rtnMap = new HashMap<String, Object>();
    	
    	Group group = new Group();
    	group.setPage(request.getPage());
    	group.setRows(request.getRows());
    	
        // 获取所有的用户组数据
        List<Group> groupList = groupService.search(group);
        PageInfo<Group> pageInfo = new PageInfo<Group>(groupList);
        
        // 总页数
	    rtnMap.put("total", pageInfo.getPages());
	    // 当前页码
	    rtnMap.put("page", pageInfo.getPageNum());
	    // 每页数
	    rtnMap.put("rowNum", pageInfo.getPageSize());
	    // 总记录数
	    rtnMap.put("records", pageInfo.getTotal());
	    // 当前页实际记录
	    rtnMap.put("rows", groupList);     
	    rtnMap.put("TOKEN", token);
        
        
        logger.debug(binder.toJson(rtnMap));
        return rtnMap;
    }
    
    @RequestMapping(value = "/idNames", method = RequestMethod.GET)
    public List<IdName> searchIdName(@RequestParam("TOKEN") String token) {
        // 获取所有的用户组数据 只查询id/name两个字段，用于下拉列表显示
        List<IdName> groupList = groupService.searchIdName(new Group());
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
    public SimpleResponse4Group save(@RequestParam("TOKEN") String token, 
    		@RequestParam("oper") String oper,
    		Group2 group2) {
    	SimpleResponse4Group sr = new SimpleResponse4Group();
        
//        // 注意jqGrid对于新增记录，id默认为""_empty""
//        // jqGrid 使用inlineNav 会有不同表现，例如"jqg3"
//        if("_empty".equals(group2.getId())){
//        	group2.setId(null);
//        }
        
        // 对象复制，属性相同，id的类型不同
        Group group;
        
        if("add".equals(oper))group2.setId(null);
        try {
        	// group = group2.copyProperties();
        	group = BeanMapper.map(group2, Group.class);
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
   	    	
            	// 新增用户组  生成一个默认的管理员账户
    	        accountService.save(groupService.getNewAccount(group));
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
			sr.setGroup(group);
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
