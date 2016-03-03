package org.zhangmz.pickles.modules.vo;

/**
 * 
 * @ClassName:SimpleResponse 
 * @Description:一个简单的请求返回对象
 * @author:张孟志
 * @date:2016年3月3日 上午10:49:16 
 * @version V1.0
 * 说明：一个简单的请求返回对象 只包含返回编码/返回信息
 */
public class SimpleResponse {

	private int code = 0;
	private String message;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
