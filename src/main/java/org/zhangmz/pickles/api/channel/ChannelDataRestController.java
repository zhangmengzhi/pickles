/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.helper.ChannelHelper;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.modules.vo.SimpleResponse;
import org.zhangmz.pickles.service.channel.IChannelService;

/**
 * @ClassName:ChannelDataRestController.java
 * @Description: 渠道数据接入口
 * @author:张孟志
 * @date:2016年3月10日下午6:11:02
 * @version V1.0
 * 说明：渠道接入口，用于数据上传
 * 手机/平板（手持设备）、PC客户端/机器人（APP）、等多种渠道数据入口
 */
@RestController
@RequestMapping("/api/channel/data")
public class ChannelDataRestController {
	private static Logger logger = LoggerFactory.getLogger(ChannelDataRestController.class);

    @Autowired
    private AuthorityHelper authorityHelper;

	@RequestMapping
	public SimpleResponse index(HttpServletRequest httpRequest) {
		IChannelService channelService = null;
		SimpleResponse sr = null;
		
		// 封装参数/检查参数是否符合通信协议
		SimpleRequest request = ChannelHelper.packageParameters(httpRequest);
		
		if(StringUtils.isBlank(request.get_token_()) 
			|| !authorityHelper.isLogin(request.get_token_(), 2)){
			return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, 
										Messages.MUST_BE_LOGGED);
		}		
		
		// 根据_code_来获取服务类
		logger.debug(request.get_code_());
		channelService = ChannelHelper.localizingResources(request.get_code_());
		
		// 服务处理
		try {
			sr = channelService.doService(request);
		} catch (Exception e) {
			e.printStackTrace();
			// sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.SYSTEM_BUSY);
		}

		return sr;
	}
}
