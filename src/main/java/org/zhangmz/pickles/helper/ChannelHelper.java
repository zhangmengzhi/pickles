/**
 * 
 */
package org.zhangmz.pickles.helper;

import javax.servlet.http.HttpServletRequest;

import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.service.channel.IChannelService;

/**
 * @ClassName:ChannelHelper.java
 * @Description: 渠道接入服务帮助类
 * @author:张孟志
 * @date:2016年3月23日上午9:23:14
 * @version V1.0
 * 说明：渠道接入服务帮助类
 * 用于参数封装（校验）/服务资源定位等
 */
public class ChannelHelper {

	/**
	 * 参数封装（校验）
	 * @param httpRequest  请求参数
	 * @return
	 */
	public static SimpleRequest packageParameters(HttpServletRequest httpRequest) {
		// 封装参数/检查参数是否符合通信协议
		 SimpleRequest request = new SimpleRequest();
		 request.set_channel_(httpRequest.getParameter("_channel_"));
		 request.set_version_(httpRequest.getParameter("_version_"));
		 request.set_token_(httpRequest.getParameter("_token_"));
		 request.set_code_(httpRequest.getParameter("_code_"));
		 request.set_data_(httpRequest.getParameter("_data_"));
		 return request;
	}
	
	/**
	 * TODO 需要重构
	 * 定位服务资源
	 * @param code
	 * @return
	 */
	public static IChannelService localizingResources(String code) {
		IChannelService channelService = null;
		
		switch (code) {
		case "REGIST_ENDUSER":
			// 不能new,这样Spring不能注入
			// channelService = new RegistEnduserChannelService();
			channelService = (IChannelService) SpringContextHelper.getBean("registEnduserChannelService");
			break;
			
		case "LOGIN_ENDUSER":
			channelService = (IChannelService) SpringContextHelper.getBean("loginEnduserChannelService");
			break;
			
		case "LOGOUT_ENDUSER":
			channelService = (IChannelService) SpringContextHelper.getBean("logoutEnduserChannelService");
			break;

		case "ENDUSER_LIST":
			channelService = (IChannelService) SpringContextHelper.getBean("enduserListChannelService");
			break;
			
		default:
			channelService = (IChannelService) SpringContextHelper.getBean("defaultChannelService");
			break;
		}
		
		return channelService;
	}
	
	/**
	 * 数据服务资源定位
	 * @param code
	 * @return
	 */
	public static IChannelService localizingDataService() {
		return (IChannelService) SpringContextHelper.getBean("dataChannelService");
	}
}
