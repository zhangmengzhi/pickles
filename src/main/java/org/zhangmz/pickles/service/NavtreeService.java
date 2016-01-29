/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.service;

import java.util.ArrayList;
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

	private static Logger logger = LoggerFactory.getLogger(NavtreeService.class);

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
		NavtreeNode rtnNavtreeNode = new NavtreeNode();
		List<Navtree> NavtreeList1 = navtreeMapper.selectNavTreeList(1);
		List<Navtree> NavtreeList2 = navtreeMapper.selectNavTreeSecondList();
		
		// 排序需要，使用LinkedHashMap
		Map<Integer, NavtreeNode> navtreeNodeMap = new LinkedHashMap<Integer, NavtreeNode>();
		
		// 一级导航菜单
		for (Navtree navtree1 : NavtreeList1) {
			NavtreeNode navtreeNode = new NavtreeNode();
			navtreeNode.setText(navtree1.getName());
			navtreeNode.setValue(String.valueOf(navtree1.getId()));
			navtreeNodeMap.put(navtree1.getId(), navtreeNode);
		}
		
		// 二级导航菜单
		for (Navtree navtree2 : NavtreeList2) {
			NavtreeNode navtreeNode = new NavtreeNode();
			navtreeNode.setText(navtree2.getName());
			navtreeNode.setValue(String.valueOf(navtree2.getId()));
			navtreeNodeMap.get(navtree2.getPid()).setNode(navtreeNode);
		}
		
		// 处理根菜单
		List<NavtreeNode> navtreeNodeList = new ArrayList<NavtreeNode>(navtreeNodeMap.values());
		rtnNavtreeNode.setNodes(navtreeNodeList);
		
		List<NavtreeNode> rtnList = new ArrayList<NavtreeNode>();
		rtnList.add(rtnNavtreeNode);
		rtnList.add(new NavtreeNode());
		String rtn = JsonMapper.nonEmptyMapper().toJson(rtnList);
		logger.debug(rtn);
		return rtn;
	}
}
