package org.zhangmz.pickles.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.orm.mapper.GroupMapper;
import org.zhangmz.pickles.orm.model.Account;
import org.zhangmz.pickles.orm.model.Group;

/**
 * 
 * @ClassName:GroupService.java
 * @Description:用户组管理服务类
 * @author:张孟志
 * @date:2016年3月1日下午6:10:46
 * @version V1.0
 * 说明：用户组管理服务类
 */
@Service
public class GroupService {
	private static Logger logger = LoggerFactory.getLogger(GroupService.class);
	
    @Autowired
    private GroupMapper groupMapper;

    /*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/
 	public List<Group> search(Group group) {
        return groupMapper.select(group);
    }

    public Group getById(Integer id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    public int save(Group group) {
    	int rtn = -1;
        if (group.getId() != null) {
        	rtn = groupMapper.updateByPrimaryKey(group);
        } else {
        	rtn = groupMapper.insert(group);
        }
        logger.debug("查看返回的变更记录数:" + rtn);
        return rtn;
    }

    /*************************************************************************
 	 * 说明：以下是业务方法
 	 * 作者：张孟志
 	 * 日期：2016-03-04
 	 ************************************************************************/
    public Account getNewAccount(Group group) {
    	Account account = new Account();
        account.setPhone(group.getPhone());
        account.setName(group.getAdminName());
        account.setPassword(group.getPhone());
        account.setGroupId(group.getId());
        account.setStatus(group.getStatus());
        return account;
    }
}
