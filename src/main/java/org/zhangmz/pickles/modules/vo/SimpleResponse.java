package org.zhangmz.pickles.modules.vo;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	public Object getResult(String key) {
		return this.resultMap.get(key);
	}
	
	public void setResult(String key, Object value) {
		this.resultMap.put(key, value);
	}

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
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}	
}
