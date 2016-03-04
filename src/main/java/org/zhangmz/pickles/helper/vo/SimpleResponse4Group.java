package org.zhangmz.pickles.helper.vo;

import org.zhangmz.pickles.modules.vo.SimpleResponse;
import org.zhangmz.pickles.orm.model.Group;

/**
 * 
 * @ClassName:SimpleResponse 
 * @Description:一个简单的请求返回对象
 * @author:张孟志
 * @date:2016年3月3日 上午10:49:16 
 * @version V1.0
 * 说明：一个简单的请求返回对象 只包含返回编码/返回信息
 */
public class SimpleResponse4Group extends SimpleResponse {

	private int groupId = -1;	
	private Group group;

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
	
}
