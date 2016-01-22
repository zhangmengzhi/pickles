package org.zhangmz.pickles.modules.utils;

import java.security.MessageDigest;
import java.util.Random;
import org.apache.commons.lang3.Validate;

/**
 * 
 * @ClassName:Md5Util 
 * @Description:MD5 工具类
 * @author:张孟志
 * @date:2016年1月21日 上午10:00:29 
 * @version V1.0
 * 说明：Digests用于字节，Md5Util用于字符
 */
public class Md5Util {
	
	private static MessageDigest md5 = null;
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 用于获取一个String的md5值
     * @param string
     * @return
     */
    public static String getMd5(String str) {
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
    
    /**
     * 
     * @Title: getRandomString 
     * @Description: 生成随机字符串
     * @param length
     * @return
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年1月21日 上午10:16:29
     * 说明：生成随机字符串
     * String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
    	Validate.isTrue(length > 0, "length argument must be a positive integer (1 or larger)", length);
        
    	String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
     }   

}
