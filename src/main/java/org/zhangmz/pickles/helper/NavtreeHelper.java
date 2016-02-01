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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhangmz.pickles.helper.vo.NavtreeNode;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.modules.utils.Collections3;
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
	
    @Autowired
    private NavtreeMapper navtreeMapper;
	
	public static final String ROOTTREEKEY = "NavtreeHelper_NAV_TREE_ROOT";
	public static final String ROOTBARKEY = "NavtreeHelper_NAV_BAR_ROOT";
	
	// 注入配置值  30分钟过期  30X60=1800
	@Value("${app.NavtreeHelperTimeoutSecs:1800}")
	private int NavtreeHelperTimeoutSecs;

	// guava cache
 	private Cache<String, NavtreeNode> navTree;

 	@PostConstruct
 	public void init() {
 		logger.debug("导航树缓存过期时间设置（秒）： " + NavtreeHelperTimeoutSecs);
 		navTree = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(NavtreeHelperTimeoutSecs, TimeUnit.SECONDS)
 				.build();
 	}
 	/**
 	 * 
 	 * @Title: getNavTreeString 
 	 * @Description: 树形数据结构查询
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月29日 下午6:56:21
 	 * 说明：先查找一级导航，pid=1; 获取以及导航的id查找二级导航
 	 */
	public String getNavTreeString() {
		NavtreeNode rtnNavtreeNode = getNavTreeRoot(1);
		
		// jquery tree控件有一个bug，不能独立显示根节点
		// 也有可能是我不会用这个控件。
		List<NavtreeNode> rtnList = new ArrayList<NavtreeNode>();
		rtnList.add(rtnNavtreeNode);
		rtnList.add(new NavtreeNode());
		String rtn = JsonMapper.nonEmptyMapper().toJson(rtnList);
		
		logger.debug(rtn);
		return rtn;
	}	
	
	public String getNavTreeHtml() {
		String rtn = null;
		NavtreeNode rtnNavtreeNode = getNavTreeRoot(2);
		
		// 拼装HTML 不要根节点
		// TODO 这个字符串应该放在缓存中，访问量较大是非常消耗资源
		rtn = getHtml(rtnNavtreeNode.getNodes());
		
		logger.debug(rtn);
		return rtn;
	}
	
	private NavtreeNode getNavTreeRoot(int type){
		NavtreeNode rtnNavtreeNode = null;
		
		if(1 == type){
			rtnNavtreeNode = navTree.getIfPresent(ROOTTREEKEY);
		}else if(2 == type){
			rtnNavtreeNode = navTree.getIfPresent(ROOTBARKEY);
		}
		
		if(null == rtnNavtreeNode){
			rtnNavtreeNode = new NavtreeNode();			
			rtnNavtreeNode.setNodes(getNavTreeList(type));
			
			if(1 == type){
				navTree.put(ROOTTREEKEY, rtnNavtreeNode);
			}else if(2 == type){
				navTree.put(ROOTBARKEY, rtnNavtreeNode);
			}
		}
		
		return rtnNavtreeNode;
	}

	/**
	 * 
	 * @Title: getNavTreeList 
	 * @Description: 获取导航栏数据
	 * @param type           类型 1取name/id;2取name/href
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月29日 下午6:46:44
	 * 说明：先查找一级导航，pid=1; 获取以及导航的id查找二级导航
	 * 		类型 1用于树形取name-text, id-value;2用于导航栏取name-text, href-value, status='Y'
	 */
	private List<NavtreeNode> getNavTreeList(int type) {
		List<Navtree> NavtreeList1 = navtreeMapper.selectNavTreeList(1);
		List<Navtree> NavtreeList2 = navtreeMapper.selectNavTreeSecondList();
		
		// 排序需要，使用LinkedHashMap
		Map<Integer, NavtreeNode> navtreeNodeMap = new LinkedHashMap<Integer, NavtreeNode>();
		
		// 一级导航菜单
		for (Navtree navtree1 : NavtreeList1) {
			NavtreeNode navtreeNode = new NavtreeNode();
			navtreeNode.setText(navtree1.getName());
			navtreeNode.setValue(String.valueOf(navtree1.getId()));
			if("Y".equals(navtree1.getStatus()) && 2==type){
				navtreeNode.setValue(navtree1.getHref());
			}
			navtreeNodeMap.put(navtree1.getId(), navtreeNode);
			if(2==type && !"Y".equals(navtree1.getStatus())){
				navtreeNodeMap.remove(navtree1.getId());
			}
		}
		
		// 二级导航菜单
		for (Navtree navtree2 : NavtreeList2) {
			NavtreeNode navtreeNode = new NavtreeNode();
			navtreeNode.setText(navtree2.getName());
						
			switch (type) {
			case 1:
				navtreeNode.setValue(String.valueOf(navtree2.getId()));
				break;
			case 2:
				navtreeNode.setValue(navtree2.getHref());
				break;
			default:
				// 默认type=1，取id为value
				navtreeNode.setValue(String.valueOf(navtree2.getId()));
				break;
			}
			
			if(navtreeNodeMap.get(navtree2.getPid()) != null){
				if( !(2==type && !"Y".equals(navtree2.getStatus())) ){
					navtreeNodeMap.get(navtree2.getPid()).setNode(navtreeNode);
				}
			}			
		}
		
		// 处理根菜单
		return new ArrayList<NavtreeNode>(navtreeNodeMap.values());			
	}
	

	
	/**
	 * 
	 * @Title: getHtml 
	 * @Description: 拼装HTML
	 * @param navtreeNodes
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月29日 下午7:04:59
	 * 说明：
		<ul class="nav navbar-nav">
			<li><a href="${base}/index">咸菜罐子</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">个人电脑</a>
				<div class="dropdown-menu">
					<div class="dropdown-inner">
						<ul class="list-unstyled">
							<li><a href="${base}/category">Window</a></li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
	 */
	private String getHtml(List<NavtreeNode> navtreeNodes){
		StringBuffer sb = new StringBuffer();
		
		sb.append("<ul class=\"nav navbar-nav\">");		
		sb.append(createLi(navtreeNodes));
		sb.append("</ul>");
		
		return sb.toString();
	}
	
	private StringBuffer createLi(List<NavtreeNode> navtreeNodes) {
		StringBuffer sb = new StringBuffer();
		
		// 开始拼装li
		for (NavtreeNode navtreeNode : navtreeNodes) {
			List<NavtreeNode> nns = navtreeNode.getNodes();
			if(Collections3.isEmpty(nns)){
				// <li><a href="${base}/index">咸菜罐子</a></li>
				sb.append("<li><a href=\"")
					.append(navtreeNode.getValue())
					.append("\">")
					.append(navtreeNode.getText())
					.append("</a></li>");
			}else{
				sb.append("<li class=\"dropdown\"><a href=\"")
					.append(navtreeNode.getValue())
					.append("\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">")
					.append(navtreeNode.getText())
					.append("</a>\n" +
								"  <div class=\"dropdown-menu\">\n" + 
								"    <div class=\"dropdown-inner\">\n" + 
								"      <ul class=\"list-unstyled\">\n" 
					).append(createLi(nns)).append(
								"      </ul>\n" + 
								"    </div>\n" + 
								"  </div>\n" + 
								"</li>"
						);
			}
		}
		
		return sb;
	}
	
	public void clearCache(){
		navTree.invalidateAll();
	}
}
