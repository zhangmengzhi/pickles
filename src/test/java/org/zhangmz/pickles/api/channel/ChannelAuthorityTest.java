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
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zhangmz.pickles.PicklesApplication;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.vo.SimpleResponse;

/**
 * 已废弃，请使用渠道服务接入口ChannelServiceRestController
 * REGIST_ENDUSER  终端用户注册          groupCode, phone, password
 * LOGIN_ENDUSER   终端用户登陆          groupCode, phone, password
 * LOGOUT_ENDUSER  终端用户退出
 * 
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
//SpringJUnit支持，由此引入Spring-Test框架支持！ 
@RunWith(SpringJUnit4ClassRunner.class) 
//指定我们SpringBoot工程的Application启动类
@SpringApplicationConfiguration(classes = PicklesApplication.class) 
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
//@WebAppConfiguration 
@WebIntegrationTest("server.port:8080")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class ChannelAuthorityTest {
	public static String loginUrl = "http://localhost:8080/api/channel/authoriry/login";
	public static String logoutUrl = "http://localhost:8080/api/channel/authoriry/logout";
	
	@Test
	public void loginTest() {
		// 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("groupCode", "nogroup"));  
        formparams.add(new BasicNameValuePair("phone", "13000000007"));
        formparams.add(new BasicNameValuePair("password", "password"));    
        
		SimpleResponse simpleResponse = HttpClientHelper.doPost(loginUrl, formparams);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
        System.out.println("--------------------------------------");
        System.out.println("result (TOKEN): " + simpleResponse.getResult("result"));
        System.out.println("--------------------------------------");
	}

	@Test
	public void logoutTest() throws Exception {
		// 先登陆获取TOKEN
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("groupCode", "nogroup"));  
        formparams.add(new BasicNameValuePair("phone", "13000000007"));
        formparams.add(new BasicNameValuePair("password", "password"));        
		SimpleResponse simpleResponse = HttpClientHelper.doPost(loginUrl, formparams);
		
		String logoutUri = logoutUrl + "/" + simpleResponse.getResult("result");
		simpleResponse = HttpClientHelper.doGet(logoutUri);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
	}
}
