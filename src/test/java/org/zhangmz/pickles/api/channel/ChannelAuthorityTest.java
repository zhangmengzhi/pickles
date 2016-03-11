/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
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
 * 登陆入口： /api/channel/authoriry/login?groupCode=nogroup&phone=13000000007&password=password
 * 退出入口：/api/channel/authoriry/logout/e3b6d2afc7d34af5a5ec3ff3253e1ee2
 * 判断是否已登陆（出于安全考虑，关闭这个入口）：/api/channel/authoriry/islogin/e3b6d2afc7d34af5a5ec3ff3253e1ee2
 */
public class ChannelAuthorityTest {
	public static String url = "http://localhost:8080/api/channel/authoriry/login";
	
	@Test
	public void loginTest() {
		// 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("groupCode", "nogroup"));  
        formparams.add(new BasicNameValuePair("phone", "13000000007"));
        formparams.add(new BasicNameValuePair("password", "password"));    
        
		SimpleResponse simpleResponse = HttpClientHelper.doPost(url, formparams);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
        System.out.println("--------------------------------------");
        System.out.println("result (TOKEN): " + simpleResponse.getResult("result"));
        System.out.println("--------------------------------------");
	}

}
