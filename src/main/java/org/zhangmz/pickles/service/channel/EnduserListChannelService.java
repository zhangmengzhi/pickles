/**
 * 
 */
package org.zhangmz.pickles.service.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.helper.AuthorityHelper;
import org.zhangmz.pickles.helper.vo.EnduserElement;
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
@Service
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
		
//		// 取_data_值，可能有分页要求
//		String date = request.get_data_();
//		// TODO 对data解密，暂时使用明文
//		// JSON-->对象转换
//		Map<String, Object> map = binder.fromJson(date, HashMap.class);
//		int page = (int) map.get("page");
//		int rows = (int) map.get("rows");
		
		// 根据终端用户组获取终端用户列表
		List<Enduser> enduserList = enduserMapper.selectEnduserList(enduser.getGroupId());
//		List<Enduser> enduserList = enduserMapper.selectEnduserPage(enduser.getGroupId(), (page-1)*rows, rows);
		
		List<EnduserElement> eel = new ArrayList<>();
		for (Enduser eu : enduserList) {
			EnduserElement ee = new EnduserElement(eu);
			eel.add(ee);
		}
		
		// 组装返回对象
		SimpleResponse sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS, eel);

		logger.debug(binder.toJson(sr));
		return sr;
	}
}
