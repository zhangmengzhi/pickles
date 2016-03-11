/**
 * 
 */
package org.zhangmz.pickles.service.channel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.modules.constants.Codes;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.convert.JsonMapper;
import org.zhangmz.pickles.modules.vo.SimpleRequest;
import org.zhangmz.pickles.modules.vo.SimpleResponse;
import org.zhangmz.pickles.orm.mapper.EnduserMapper;
import org.zhangmz.pickles.orm.model.Enduser;

/**
 * @ClassName:EnduserListChannelService
 * @Description: 终端用户列表查询服务
 * @author:张孟志
 * @date:2016年3月11日下午12:43:27
 * @version V1.0
 * 说明：终端用户列表查询服务
 */
public class EnduserListChannelService implements IChannelService {

	private static Logger logger = LoggerFactory.getLogger(EnduserListChannelService.class);
	
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
    @Autowired
    private AuthorityHelper authorityHelper;    
	
    @Autowired
    private EnduserMapper enduserMapper;    

	/* (non-Javadoc)
	 * @see org.zhangmz.pickles.service.IChannelService#doService(org.zhangmz.pickles.modules.vo.SimpleRequest)
	 */
	@Override
	public SimpleResponse doService(SimpleRequest request) {
		
		// 从requset中获取TOKEN，进一步获取终端用户信息
		Enduser enduser = authorityHelper.getEnduser(request.get_token_());
		
		// 根据终端用户组获取终端用户列表
		List<Enduser> enduserList = enduserMapper.selectEnduserList(enduser.getGroupId());
		
		// 组装返回对象
		SimpleResponse sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS, enduserList);

		logger.debug(binder.toJson(sr));
		return sr;
	}
}
