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
	_code_			接口服务代码，唯一标示一个接口
	_data_			base64编码后的业务数据
为方便说明，我们将_data_外的所有字段称为"基础字段"。 
base64编码前的_data_格式是如下的json(所有的json的键都要加双引号，值不一定)
{
  "value" : {
    "datatime" : "2014-09-23 09:11:11",
    "address" : "xxx"
  }
}

返回报文格式：
返回报文是json格式，包含2个字段，code、message、resultMap，
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
 */
@RestController
@RequestMapping("/api/channel/service")
public class ChannelServiceRestController {
	private static Logger logger = LoggerFactory.getLogger(ChannelServiceRestController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();

}
