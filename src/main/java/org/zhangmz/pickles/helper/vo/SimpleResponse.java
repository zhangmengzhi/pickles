package org.zhangmz.pickles.helper.vo;

/**
 * 
 * @ClassName:SimpleResponse 
 * @Description:一个简单的请求返回对象
 * @author:张孟志
 * @date:2016年3月3日 上午10:49:16 
 * @version V1.0
 * 说明：一个简单的请求返回对象 只包含返回编码/返回信息
 */
public class SimpleResponse extends org.zhangmz.pickles.modules.vo.SimpleResponse {

	private int groupId = -1;

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
	
}
