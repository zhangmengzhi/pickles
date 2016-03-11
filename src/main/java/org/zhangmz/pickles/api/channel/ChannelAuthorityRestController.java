/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.modules.vo.SimpleResponse;
import org.zhangmz.pickles.service.EnduserService;

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
	
	@Autowired
    private EnduserService enduserService;
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录请求
	 * @param groupCode   用户组编码
	 * @param phoneEmail  手机号码或Email
	 * @param password    密码
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午7:14:05
	 * 说明：根据用户组编码、手机号码或Email、密码登录
	 * 用户组编码可以改为用户组ID，但是会对用户泄露编码方式
	 */
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@RequestMapping(value = "/login")
	public SimpleResponse login(@RequestParam("groupCode") String groupCode, 
						@RequestParam("phone") String phone, 
						@RequestParam("password") String password) {
		SimpleResponse sr = new SimpleResponse();
		
		try {
			logger.debug("groupCode:"+ groupCode 
					+ ", phone:"+ phone 
					+ ", password:" + password);
			String token = enduserService.login(groupCode, phone, password);
			sr.setCode(Codes.SUCCESS_TRUE_NUMBER);
			sr.setMessage(Messages.SUCCESS);
			sr.setResult("TOKEN", token);
		} catch (Exception e) {
			e.printStackTrace();
			sr.setCode(Codes.FAILURE_FALSE_NUMBER);
			sr.setMessage(e.getMessage());
		}
		
		logger.debug(binder.toJson(sr));
		return sr;
    }
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: 退出
	 * @param token
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午8:05:22
	 * 说明：清理用户登录信息
	 */
	@RequestMapping(value = "/logout")
	public SimpleResponse logout(@RequestParam("TOKEN") String token) {
		enduserService.logout(token);
		
		SimpleResponse sr = new SimpleResponse();
		sr.setCode(Codes.SUCCESS_TRUE_NUMBER);
		sr.setMessage(Messages.SUCCESS);
		sr.setResult("TOKEN", token);

		logger.debug(binder.toJson(sr));
		return sr;
    }
}
