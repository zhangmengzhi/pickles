/**
 * 
 */
package org.zhangmz.pickles.api.channel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;
import org.zhangmz.pickles.modules.constants.Messages;
import org.zhangmz.pickles.modules.utils.Ids;
import org.zhangmz.pickles.modules.vo.SimpleResponse;

/**
 * @ClassName:ChannelServiceTest.java
 * @Description: 测试渠道服务入口
 * @author:张孟志
 * @date:2016年3月11日下午3:48:19
 * @version V1.0
 * 说明：
 * 返回SimpleResponse，包括code/message/resultMap
 * resultMap 是HashMap
 * 记录集 result 是List，每一条记录为LinkedHashMap
 * List<LinkedHashMap<String, Object>>
 */
public class ChannelServiceTest {
	static String url = "http://localhost:8080/api/channel/service";
	
	@Test
	public void registLoginLogoutEnduserTest() {
		// 为避免数据库中phone字段冲突，获取11位随机数测试
		String phone = Ids.randomBase62(11);
		// 发起终端用户注册请求
        List<NameValuePair> regist = new ArrayList<NameValuePair>();  
        regist.add(new BasicNameValuePair("_channel_", "1"));  
        regist.add(new BasicNameValuePair("_version_", "1.8"));
        regist.add(new BasicNameValuePair("_token_", ""));               // 注册不需要token
        regist.add(new BasicNameValuePair("_code_", "REGIST_ENDUSER"));  // 注意这个 code
        regist.add(new BasicNameValuePair("_data_", "{\n" +
							        				"    \"groupCode\": \"nogroup\",\n" + 
							        				"    \"phone\": \"" + phone + "\",\n" + 
							        				"    \"password\": \"password\"\n" + 
							        				"}"));
        
        SimpleResponse simpleResponse = HttpClientHelper.doPost(url, regist);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
	
		// 发起终端用户登陆请求
        List<NameValuePair> login = new ArrayList<NameValuePair>();  
        login.add(new BasicNameValuePair("_channel_", "1"));  
        login.add(new BasicNameValuePair("_version_", "1.8"));
        login.add(new BasicNameValuePair("_token_", ""));              // 登陆不需要token    
        login.add(new BasicNameValuePair("_code_", "LOGIN_ENDUSER"));  // 注意这个 code
        login.add(new BasicNameValuePair("_data_", "{\n" +
							        				"    \"groupCode\": \"nogroup\",\n" + 
							        				"    \"phone\": \"" + phone + "\",\n" + 
							        				"    \"password\": \"password\"\n" + 
							        				"}"));
        
        simpleResponse = HttpClientHelper.doPost(url, login);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
        System.out.println("--------------------------------------");
        System.out.println("result (TOKEN): " + simpleResponse.getResult("result"));
        System.out.println("--------------------------------------");

		// 发起终端用户退出请求
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("_channel_", "1"));  
        formparams.add(new BasicNameValuePair("_version_", "1.8"));
        formparams.add(new BasicNameValuePair("_token_", (String) simpleResponse.getResult("result")));    
        formparams.add(new BasicNameValuePair("_code_", "LOGOUT_ENDUSER"));  // 注意这个 code
        formparams.add(new BasicNameValuePair("_data_", ""));
        
        simpleResponse = HttpClientHelper.doPost(url, formparams);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
	}
	
	@Test
	public void enduserListTest() {	
		// 先登陆获得TOKEN
		SimpleResponse simpleResponse = this.getToken();
		
        // 发起终端用户列表查询请求
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("_channel_", "1"));  
        formparams.add(new BasicNameValuePair("_version_", "1.8"));
        formparams.add(new BasicNameValuePair("_token_", (String) simpleResponse.getResult("result")));    
        formparams.add(new BasicNameValuePair("_code_", "ENDUSER_LIST"));  // 注意这个 code
        formparams.add(new BasicNameValuePair("_data_", 
								        				"{\n" +
								        				"    \"page\": 2,\n" + 
								        				"    \"rows\": 2\n" + 
								        				"}"));
        
        simpleResponse = HttpClientHelper.doPost(url, formparams);
		Assert.assertTrue(simpleResponse.getCode() == 1);
        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
        System.out.println("--------------------------------------");
        System.out.println("result (enduser list): " + simpleResponse.getResult("result"));
        System.out.println("--------------------------------------");
        
        // 打印返回的列表，注意对象转换后的数据类型 List<LinkedHashMap<String, Object>>
        List list = (List) simpleResponse.getResult("result");
        // 由于开发中数据库会发生变化，不用Assert
        // Assert.assertTrue(2 == list.size());
        for (Object object : list) {
        	System.out.println("--------------------------------------");  
        	Map ee = (LinkedHashMap) object;
        	printMap2(ee);
        	System.out.println("--------------------------------------");  
		}
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
		SimpleResponse simpleResponse = HttpClientHelper.doPost(ChannelAuthorityTest.loginUrl, fs);
        return simpleResponse;
	}
	
	public static void printMap2(Map<String, Object> parameters){  
		for (Iterator iterator = parameters.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			System.out.println("Key:"+ entry.getKey().toString());  
            System.out.println("Value:"+ entry.getValue().toString());  
		}
    }
}
