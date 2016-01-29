/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.helper.NavtreeHelper;
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

    @Autowired
    private NavtreeHelper navtreeHelper;
    
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
    	navtreeHelper.clearCache();
    }

    public void save(Navtree navtree) {
        if (navtree.getId() != null) {
        	navtreeMapper.updateByPrimaryKey(navtree);
        } else {
        	navtreeMapper.insert(navtree);
        }
    	navtreeHelper.clearCache();
    }
    
	public String getNavTreeString() {		
		return navtreeHelper.getNavTreeString(navtreeMapper);
	}
	
	public String getNavTreeHtml() {		
		return navtreeHelper.getNavTreeHtml(navtreeMapper);
	}
}
