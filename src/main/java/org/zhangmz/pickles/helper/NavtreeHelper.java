/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhangmz.pickles.helper.vo.NavtreeNode;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.orm.mapper.NavtreeMapper;
import org.zhangmz.pickles.orm.model.Navtree;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Title:NavtreeHelper.java
 * Description:导航栏帮助类
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月29日 下午6:06:11
 * 说明：提供导航栏的树形数据结构对象
 * 		生成导航栏HTML代码
 */
@Component
public class NavtreeHelper {
	
	private static Logger logger = LoggerFactory.getLogger(NavtreeHelper.class);
	
	public static final String ROOTKEY = "NAV_TREE_ROOT";
	
	// 注入配置值  30分钟过期  30X60=1800
	@Value("${app.loginTimeoutSecs:1800}")
	private int loginTimeoutSecs;

	// guava cache
 	private Cache<String, NavtreeNode> navTree;

 	@PostConstruct
 	public void init() {
 		logger.debug("导航树缓存过期时间设置（秒）： " + loginTimeoutSecs);
 		navTree = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
 				.build();
 	}
 	
	/*************************************************************************
 	 * 说明：树形数据结构查询
 	 * 作者：张孟志
 	 * 日期：2016-01-28
 	 * 先查找一级导航，pid=1; 获取以及导航的id查找二级导航
 	 ************************************************************************/	
	public String getNavTreeString(NavtreeMapper navtreeMapper) {
		NavtreeNode rtnNavtreeNode = getNavTreeRoot(navtreeMapper);
		
		// jquery tree控件有一个bug，不能独立显示根节点
		// 也有可能是我不会用这个控件。
		List<NavtreeNode> rtnList = new ArrayList<NavtreeNode>();
		rtnList.add(rtnNavtreeNode);
		rtnList.add(new NavtreeNode());
		String rtn = JsonMapper.nonEmptyMapper().toJson(rtnList);
		
		logger.debug(rtn);
		return rtn;
	}
	
	private NavtreeNode getNavTreeRoot(NavtreeMapper navtreeMapper){
		NavtreeNode rtnNavtreeNode = navTree.getIfPresent(ROOTKEY);
		if(null == rtnNavtreeNode){
			rtnNavtreeNode = new NavtreeNode();
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

			navTree.put(ROOTKEY, rtnNavtreeNode);
		}
		
		return rtnNavtreeNode;
	}
}
