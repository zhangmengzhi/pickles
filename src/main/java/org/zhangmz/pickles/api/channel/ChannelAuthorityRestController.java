/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.pickles.modules.convert.JsonMapper;

/**
 * @ClassName:ChannelAuthorityRestController.java
 * @Description:渠道认证接入口
 * @author:张孟志
 * @date:2016年3月10日下午6:16:27
 * @version V1.0
 * 说明：渠道认证接入口
 * 手机/平板（手持设备）、PC客户端/机器人（APP）、等多种渠道认证入口
 */
@RestController
@RequestMapping("/api/channel/authoriry")
public class ChannelAuthorityRestController {
	private static Logger logger = LoggerFactory.getLogger(ChannelAuthorityRestController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();

}
