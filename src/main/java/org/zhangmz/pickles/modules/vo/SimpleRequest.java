/**
 * 
 */
package org.zhangmz.pickles.modules.vo;

/**
 * @ClassName:SimpleRequest.java
 * @Description: 请求类
 * @author:张孟志
 * @date:2016年3月11日下午12:15:38
 * @version V1.0
 * 说明：
	请求报文格式:
		请求报文有且仅有如下的字段，各字段分别解释如下：
		_channel_		表示请求来源区分，手机/平板（手持设备）、PC客户端/机器人（APP）等
		_version_ 		接口版本
		_token_			认证码，登陆成功后系统会返回一个认证码
		废弃_enduserid_		终端用户Id
		废弃_groupid_		用户组Id
		_code_			接口服务代码，唯一标示一个接口
		_data_			base64编码后的业务数据
	为方便说明，我们将_data_外的所有字段称为"基础字段"。 
	base64编码前的_data_格式是如下的json(所有的json的键都要加双引号，值不一定)
	{
		"datatime" : "2014-09-23 09:11:11",
		"address" : "xxx"
	}
 */
public class SimpleRequest {
	private String _channel_;
	private String _version_;
	private String _token_;
	private String _code_;
	private String _data_;
	/**
	 * @return the _channel_
	 */
	public String get_channel_() {
		return _channel_;
	}
	/**
	 * @param _channel_ the _channel_ to set
	 */
	public void set_channel_(String _channel_) {
		this._channel_ = _channel_;
	}
	/**
	 * @return the _version_
	 */
	public String get_version_() {
		return _version_;
	}
	/**
	 * @param _version_ the _version_ to set
	 */
	public void set_version_(String _version_) {
		this._version_ = _version_;
	}
	/**
	 * @return the _token_
	 */
	public String get_token_() {
		return _token_;
	}
	/**
	 * @param _token_ the _token_ to set
	 */
	public void set_token_(String _token_) {
		this._token_ = _token_;
	}
	/**
	 * @return the _code_
	 */
	public String get_code_() {
		return _code_;
	}
	/**
	 * @param _code_ the _code_ to set
	 */
	public void set_code_(String _code_) {
		this._code_ = _code_;
	}
	/**
	 * @return the _data_
	 */
	public String get_data_() {
		return _data_;
	}
	/**
	 * @param _data_ the _data_ to set
	 */
	public void set_data_(String _data_) {
		this._data_ = _data_;
	}
}
