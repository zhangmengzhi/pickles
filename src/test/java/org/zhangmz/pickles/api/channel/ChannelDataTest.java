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
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zhangmz.pickles.PicklesApplication;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.utils.Ids;
import org.zhangmz.pickles.modules.vo.SimpleResponse;

/**
 * @ClassName:ChannelServiceTest.java
 * @Description: 测试渠道数据入口
 * @author:张孟志
 * @date:2016年3月28日下午2:37:19
 * @version V1.0
 * 说明：
 * 返回SimpleResponse，包括code/message
 */
//SpringJUnit支持，由此引入Spring-Test框架支持！ 
@RunWith(SpringJUnit4ClassRunner.class) 
//指定我们SpringBoot工程的Application启动类
@SpringApplicationConfiguration(classes = PicklesApplication.class) 
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
//@WebAppConfiguration 
@WebIntegrationTest("server.port:8080")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class ChannelDataTest {
	public static String loginUrl = "http://localhost:8080/api/channel/authoriry/login";
	public static String url = "http://localhost:8080/api/channel/data";
	
	@Test
	public void accountTest() {	
		// 先登陆获得TOKEN
		SimpleResponse simpleResponse = this.getToken();

		// 为避免数据库中phone字段冲突，获取11位随机数测试
		String phone = Ids.randomBase62(11);
        // 发起终端用户列表查询请求
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("_channel_", "1"));  
        formparams.add(new BasicNameValuePair("_version_", "1.8"));
        formparams.add(new BasicNameValuePair("_token_", (String) simpleResponse.getResult("result")));    
        formparams.add(new BasicNameValuePair("_code_", "Account"));         // 注意这个 code
        String data = "{\n" +
						"    \"groupId\": 3,\n" + 
						"    \"phone\": \"" + phone + "\",\n" + 
						"    \"name\": \"" + phone + "\",\n" + 
						"    \"email\": \"" + phone + "@pickles.com\",\n" + 
						"    \"password\": \"password\",\n" + 
						"    \"status\": \"Yes\"\n" + 
						"}";
        System.out.println("Enduser数据请求内容：" + data);
        formparams.add(new BasicNameValuePair("_data_", data));
        
        simpleResponse = HttpClientHelper.doPost(url, formparams);
        System.out.println("插入数据标识phone：" + phone);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
	}
	
	@Test
	public void enduserTest() {	
		// 先登陆获得TOKEN
		SimpleResponse simpleResponse = this.getToken();

		// 为避免数据库中phone字段冲突，获取11位随机数测试
		String phone = Ids.randomBase62(11);
        // 发起终端用户列表查询请求
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("_channel_", "1"));  
        formparams.add(new BasicNameValuePair("_version_", "1.8"));
        formparams.add(new BasicNameValuePair("_token_", (String) simpleResponse.getResult("result")));    
        formparams.add(new BasicNameValuePair("_code_", "Enduser"));         // 注意这个 code
        String data = "{\n" +
						"    \"groupId\": 3,\n" + 
						"    \"phone\": \"" + phone + "\",\n" + 
						"    \"name\": \"" + phone + "\",\n" + 
						"    \"password\": \"password\",\n" + 
						"    \"status\": \"Yes\"\n" + 
						"}";
        System.out.println("Enduser数据请求内容：" + data);
        formparams.add(new BasicNameValuePair("_data_", data));
        
        simpleResponse = HttpClientHelper.doPost(url, formparams);
        System.out.println("插入数据标识phone：" + phone);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
	}
	
	/**
	 * 
	 * @Title: getToken 
	 * @Description: 登陆获取TOKEN
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年3月21日 下午4:37:18
	 * 说明：登陆获取TOKEN
	 * 该接口已废弃，仅用于测试
	 */
	public static SimpleResponse getToken(){
		// 创建参数队列    
        List<NameValuePair> fs = new ArrayList<NameValuePair>();  
        fs.add(new BasicNameValuePair("groupCode", "nogroup"));  
        fs.add(new BasicNameValuePair("phone", "13000000007"));
        fs.add(new BasicNameValuePair("password", "password"));        
		SimpleResponse simpleResponse = HttpClientHelper.doPost(loginUrl, fs);
        return simpleResponse;
	}
}
