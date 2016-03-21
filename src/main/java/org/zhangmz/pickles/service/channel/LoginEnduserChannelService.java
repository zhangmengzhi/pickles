/**
 * 
 */
package org.zhangmz.pickles.service.channel;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.modules.vo.SimpleResponse;
import org.zhangmz.pickles.service.EnduserService;

/**
 * 
 * @ClassName:LoginEnduserChannelService 
 * @Description:终端用户列表登陆服务
 * @author:张孟志
 * @date:2016年3月21日 上午11:55:53 
 * @version V1.0
 * 说明：终端用户列表登陆服务
 */
@Service
public class LoginEnduserChannelService implements IChannelService {

	private static Logger logger = LoggerFactory.getLogger(LoginEnduserChannelService.class);
	
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();	

    @Autowired
    private EnduserService enduserService;
    
	@Override
	public SimpleResponse doService(SimpleRequest request) {
		SimpleResponse sr = null;
		
		// TODO 取_data_值，对data解密，暂时使用明文  groupCode, phone, password
		String date = request.get_data_();
		logger.debug(date);
		// JSON-->对象转换
		Map<String, Object> map = binder.fromJson(date, HashMap.class);
		String groupCode = (String) map.get("groupCode");
		String phone = (String) map.get("phone");
		String password = (String) map.get("password");
		
		try {
			String token = enduserService.login(groupCode, phone, password);
			sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS, token);
		} catch (Exception e) {
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}

		logger.debug(binder.toJson(sr));
		return sr;
	}
}
