/**
 * 
 */
package org.zhangmz.pickles.service.channel;

import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.modules.vo.SimpleResponse;

/**
 * @ClassName:DefaultChannelService.java
 * @Description: 默认服务类
 * @author:张孟志
 * @date:2016年3月11日下午12:43:27
 * @version V1.0
 * 说明：默认服务类，没有什么用处
 */
public class DefaultChannelService implements IChannelService {

	/* (non-Javadoc)
	 * @see org.zhangmz.pickles.service.IChannelService#doService(org.zhangmz.pickles.modules.vo.SimpleRequest)
	 */
	@Override
	public SimpleResponse doService(SimpleRequest request) {
		return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.UNKNOW_CODE);
	}
}
