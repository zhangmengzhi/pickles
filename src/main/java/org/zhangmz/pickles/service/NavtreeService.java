/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.helper.vo.NavtreeNode;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.orm.mapper.NavtreeMapper;
import org.zhangmz.pickles.orm.model.Navtree;

/**
 * Title:NavtreeService.java
 * Description:导航栏服务类
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月28日 下午3:51:30
 * 说明：导航栏服务类
 */
@Service
public class NavtreeService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private NavtreeMapper navtreeMapper;
    
    /*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-01-28
 	 ************************************************************************/
// 	public List<Navtree> search(Navtree navtree) {
//        return navtreeMapper.search(navtree);
//    }

    public Navtree getById(int id) {
        return navtreeMapper.selectByPrimaryKey(id);
    }

    public void deleteById(int id) {
    	navtreeMapper.deleteByPrimaryKey(id);
    }

    public void save(Navtree navtree) {
        if (navtree.getId() != null) {
        	navtreeMapper.updateByPrimaryKey(navtree);
        } else {
        	navtreeMapper.insert(navtree);
        }
    }
    
    /*************************************************************************
 	 * 说明：树形数据结构查询
 	 * 作者：张孟志
 	 * 日期：2016-01-28
 	 * 先查找一级导航，pid=1; 获取以及导航的id查找二级导航
 	 ************************************************************************/	
	public String getNavTreeString() {
		List<Navtree> NavtreeList1 = navtreeMapper.selectNavTreeList(1);
		List<Navtree> NavtreeList2 = navtreeMapper.selectNavTreeSecondList();
		
		// 排序需要，使用LinkedHashMap
		Map<Integer, NavtreeNode> navtreeNodeMap = new LinkedHashMap<Integer, NavtreeNode>();
		
		// 一级导航菜单
		for (Navtree navtree : NavtreeList1) {
			NavtreeNode navtreeNode = new NavtreeNode();
			navtreeNode.setText(navtree.getName());
			navtreeNode.setValue(String.valueOf(navtree.getId()));
			navtreeNodeMap.put(navtree.getId(), navtreeNode);
		}
		
		// 二级导航菜单
		for (Navtree navtree : NavtreeList2) {
			NavtreeNode navtreeNode = new NavtreeNode();
			navtreeNode.setText(navtree.getName());
			navtreeNode.setValue(String.valueOf(navtree.getId()));
			navtreeNodeMap.get(navtree.getPid()).setNode(navtreeNode);
		}
		
		// List navtreeNodeList = new ArrayList<NavtreeNode>(navtreeNodeMap.values());
		// String rtn = JsonMapper.nonDefaultMapper().toJson(navtreeNodeList);
		String rtn = JsonMapper.nonDefaultMapper().toJson(navtreeNodeMap.values());
		logger.debug(rtn);
		return rtn;
	}
}
