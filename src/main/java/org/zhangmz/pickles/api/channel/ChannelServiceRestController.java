/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.helper.SpringContextHelper;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.modules.vo.SimpleResponse;
import org.zhangmz.pickles.service.channel.IChannelService;

/**
 * @ClassName:ChannelServiceRestController.java
 * @Description:渠道服务接入口
 * @author:张孟志
 * @date:2016年3月10日下午6:14:31
 * @version V1.0
 * 说明：渠道服务接入口
 * 手机/平板（手持设备）、PC客户端/机器人（APP）、等多种渠道数据入口
 * 
	接口请求地址: http://xxx.xxx.xxx/api/channel/service
	接口请求方式: HTTP POST
	接口报文编码: UTF-8
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
	
	返回报文格式：
	返回报文是json格式，包含3个字段，code、message、resultMap，
	code表示状态（1表示成功，0表示失败），message表示提示信息，resultMap是返回的业务数据（或错误提示信息）。
	成功报文示例：
	{
		"code":"1",
		"message":"成功",
		"resultMap": {
			"customservice": "(010)62693990",
			"dataversion": 131
		}
	}
	
	失败报文示例：	
	{
		"code":"0",
		"message":"失败",
		"resultMap": {
			"result":"e3b6d2afc7d34af5a5ec3ff3253e1ee2"
		}
	}
	
/api/channel/service?_channel_=1&_version_=1.1&_token_=3d190529f12546288dd4141e8e3ce113&_code_=ENDUSER_LIST&_data_=
 */
@RestController
@RequestMapping("/api/channel/service")
public class ChannelServiceRestController {
	private static Logger logger = LoggerFactory.getLogger(ChannelServiceRestController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
    @Autowired
    private AuthorityHelper authorityHelper;

	@RequestMapping
	public SimpleResponse index(HttpServletRequest httpRequest) {
		IChannelService channelService = null;
		SimpleResponse sr = null;
		
		// 封装参数/检查参数是否符合通信协议
		 SimpleRequest request = new SimpleRequest();
		 request.set_channel_(httpRequest.getParameter("_channel_"));
		 request.set_version_(httpRequest.getParameter("_version_"));
		 request.set_token_(httpRequest.getParameter("_token_"));
		 request.set_code_(httpRequest.getParameter("_code_"));
		 request.set_data_(httpRequest.getParameter("_data_"));
		 
		 if(null == request.get_token_()){
			 return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.MUST_BE_LOGGED);
		 }
		 
		
		// 判断终端用户是否有权限访问（判断是否登陆）
		// authorityHelper.isLogin(request.get_token_(), 2);
		try {
			logger.debug(request.get_token_());
			if(!authorityHelper.isLogin(request.get_token_(), 2)){
				return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.MUST_BE_LOGGED);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}
		
		// 根据_code_来获取服务类
		switch (request.get_code_()) {
		case "ENDUSER_LIST":
			// 不能new,这样Spring不能注入
			// channelService = new EnduserListChannelService();
			channelService = (IChannelService) SpringContextHelper.getBean("enduserListChannelService");
			break;
			
		default:
			// channelService = new DefaultChannelService();
			channelService = (IChannelService) SpringContextHelper.getBean("defaultChannelService");
			break;
		}
		
		// 服务处理
		try {
			sr = channelService.doService(request);
		} catch (Exception e) {
			e.printStackTrace();
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}

		logger.debug(binder.toJson(sr));
		return sr;
	}
}
