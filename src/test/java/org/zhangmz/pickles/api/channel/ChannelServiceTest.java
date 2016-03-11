/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.vo.SimpleResponse;

/**
 * @ClassName:ChannelServiceTest.java
 * @Description: 测试渠道服务入口
 * @author:张孟志
 * @date:2016年3月11日下午3:48:19
 * @version V1.0
 * 说明：
 */
public class ChannelServiceTest {
	static String url = "http://localhost:8080/api/channel/service";
	
	@Test
	public void enduserListTest() {	
		// 先登陆获得TOKEN
		// 创建参数队列    
        List<NameValuePair> fs = new ArrayList<NameValuePair>();  
        fs.add(new BasicNameValuePair("groupCode", "nogroup"));  
        fs.add(new BasicNameValuePair("phone", "13000000007"));
        fs.add(new BasicNameValuePair("password", "password"));        
		SimpleResponse simpleResponse = HttpClientHelper.doPost(ChannelAuthorityTest.url, fs);
        
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("_channel_", "1"));  
        formparams.add(new BasicNameValuePair("_version_", "1.8"));
        formparams.add(new BasicNameValuePair("_token_", (String) simpleResponse.getResult("result")));    
        formparams.add(new BasicNameValuePair("_code_", "ENDUSER_LIST"));  
        formparams.add(new BasicNameValuePair("_data_", ""));
        
        simpleResponse = HttpClientHelper.doPost(url, formparams);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
        System.out.println("--------------------------------------");
        System.out.println("result (enduser list): " + simpleResponse.getResult("result"));
        System.out.println("--------------------------------------");
	}
	
}
