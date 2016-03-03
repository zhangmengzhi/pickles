/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.controller.admin.account;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.orm.model.Account;
import org.zhangmz.pickles.service.AccountService;
import com.github.pagehelper.PageInfo;

/**
 * Title:AccountController.java
 * Description:账户处理控制器
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月27日 上午11:07:34
 * 说明：账户处理控制器
 */
@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @RequestMapping
    public ModelAndView search(@RequestParam("TOKEN") String token, Account account) {
        ModelAndView result = new ModelAndView("admin/account/index");
        List<Account> accountList = accountService.search(account);
        result.addObject("pageInfo", new PageInfo<Account>(accountList));
        result.addObject("queryParam", account);
        result.addObject("page", account.getPage());
        result.addObject("rows", account.getRows());
        result.addObject("TOKEN", token);
        return result;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam("TOKEN") String token) {
        ModelAndView result = new ModelAndView("admin/account/view");
        result.addObject("account", new Account());
        // add by zhangmz 2016-01-09 添加操作表示
        result.addObject("action", "add");
        result.addObject("TOKEN", token);
        return result;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@RequestParam("TOKEN") String token, @PathVariable Long id) {
        ModelAndView result = new ModelAndView("admin/account/view");
        Account account = accountService.getById(id);
        result.addObject("account", account);
        // add by zhangmz 2016-01-09 添加操作表示
        // result.addObject("action", "view");
        result.addObject("action", "edit");
        result.addObject("TOKEN", token);
        return result;
    }

    /**
     * 
     * @Title: delete 
     * @Description: 删除用户
     * @param token
     * @param id
     * @param ra
     * @return
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年1月27日 下午5:03:56
     * 说明：因为使用了AOP，必须返回ModelAndView对象。
     * 		这个方法应该改为异步调用方法。
     */
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@RequestParam("TOKEN") String token, @PathVariable Long id) {
    	ModelAndView result = new ModelAndView("admin/account/index");
    	String message;
    	// 删除操作人必须为管理员，被删除的不能是超级管理员
    	if(accountService.isAdmin(token) && 1!=id.intValue()){
    		accountService.deleteById(id);
    		message = Messages.DELETE_SUCCESS;
    	} else {
    		message = Messages.MUST_BE_ADMIN + Messages.ADMINISTRATOR_IS_STATIC;
    	}
    	
    	// 因为使用了AOP，必须返回ModelAndView对象。调回用户管理页面
    	Account account = new Account();
        List<Account> accountList = accountService.search(account);
        result.addObject("pageInfo", new PageInfo<Account>(accountList));
        result.addObject("queryParam", account);
        result.addObject("page", account.getPage());
        result.addObject("rows", account.getRows());
        result.addObject("message", message);
        result.addObject("TOKEN", token);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("TOKEN") String token, Account account) {
        ModelAndView result = new ModelAndView("admin/account/view");
        String message = account.getId() == null ? Messages.INSERT_SUCCESS : Messages.UPDATE_SUCCESS;
        
        // 浏览器保存用户/密码下，可以只输入浏览器缓存（用户名/密码两个字段）提交表单
        // MySQLIntegrityConstraintViolationException: Column 'email' cannot be null
        // 为了避免这个bug，拦截一下异常。
        try {
        	// modify by zhangmz 只有超级管理员可以修改自己的信息
        	if(!accountService.isAdministrator(token)         		
        		&& accountService.isAdministrator(account)){
    			message = Messages.MUST_BE_ADMINISTRATOR;
        	}else{
            	accountService.save(account);
        	}
		} catch (Exception e) {
			// msg = e.getMessage();
			message = Messages.SYSTEM_BUSY;
		}
        
        result.addObject("account", account);
        result.addObject("message", message);
        // add by zhangmz 2016-01-09 添加操作表示
        // result.addObject("action", "view");
        result.addObject("action", "edit");
        result.addObject("TOKEN", token);
        return result;
    }
}
