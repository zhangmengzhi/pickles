/**
 * 
 */
package org.zhangmz.pickles.service.channel;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zhangmz.pickles.helper.SpringContextHelper;
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
		SimpleResponse sr = null;
		
		String objectName = request.get_code_();
		// String data = request.get_data_();
		
		String objectClassName = "org.zhangmz.pickles.orm.model." + objectName; 
		// String objectServiceName = "org.zhangmz.pickles.service." + objectName + "Service"; 
		String objectBeanName = objectName.substring(0, 1).toLowerCase() + objectName.substring(1) + "Service";
		String methodName = "save";
		logger.debug("objectClassName:" + objectClassName 
				// + ";objectMapperName:" + objectServiceName
				+ ";objectBeanName:" + objectBeanName
				+ ";methodName:" + methodName);
		try {			  
			Class clazz = Class.forName(objectClassName);			
			Object service = SpringContextHelper.getBean(objectBeanName);
			Method m = service.getClass().getDeclaredMethod(methodName, clazz);
			m.invoke(service, binder.fromJson(request.get_data_(), clazz));
			sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}
		
		return sr;
	}
}
