/**
 * 
 */
package org.zhangmz.pickles.service.channel;

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
 * @Description:终端用户列表退出服务
 * @author:张孟志
 * @date:2016年3月21日 上午11:55:53 
 * @version V1.0
 * 说明：终端用户列表退出服务
 */
@Service
public class LogoutEnduserChannelService implements IChannelService {
	private static Logger logger = LoggerFactory.getLogger(LogoutEnduserChannelService.class);	
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();	

    @Autowired
    private EnduserService enduserService;
    
	@Override
	public SimpleResponse doService(SimpleRequest request) {
		SimpleResponse sr = null;
		
		String token = request.get_token_();
		
		try {
			enduserService.logout(token);
			sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS);
		} catch (Exception e) {
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}

		logger.debug(binder.toJson(sr));
		return sr;
	}
}
