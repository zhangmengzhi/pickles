/**
 * 
 */
package org.zhangmz.pickles.service.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.modules.vo.SimpleResponse;

/**
 * 
 * @ClassName:DataChannelService.java
 * @Description:数据服务类
 * @author:张孟志
 * @date:2016年3月23日上午10:18:36
 * @version V1.0
 * 说明：数据服务类
 */
@Service
public class DataChannelService implements IChannelService {
	private static Logger logger = LoggerFactory.getLogger(DataChannelService.class);	
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();

	@Override
	public SimpleResponse doService(SimpleRequest request) {
		return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.UNKNOW_CODE);
	}
}
