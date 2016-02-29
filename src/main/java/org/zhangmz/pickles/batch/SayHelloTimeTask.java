package org.zhangmz.pickles.batch;

import java.text.SimpleDateFormat;  
import java.util.Date;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName:SayHelloTimeTask 
 * @Description: 定时任务案例
 * @author:张孟志
 * @date:2016年2月29日 上午10:50:08 
 * @version V1.0
 * 说明： 在控制台打印一句话，每隔一秒打印一次
 */
@Component
public class SayHelloTimeTask {
	private static Logger logger = LoggerFactory.getLogger(SayHelloTimeTask.class);
	  
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * @Title: reportCurrentTime 
	 * @Description: 打印当前时间点
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年2月29日 下午2:25:36
	 * 说明：@Scheduled 注解用于标注这个方法是一个定时任务的方法，方法的执行周期是fixedRate
	 * 也可以使用更灵活的设置方法 @Scheduled(cron="...") ，用一个表达式来设置定时任务。
	 */
    // @Scheduled(fixedRate = 5000)  //每隔5秒钟运行一次
    @Scheduled(cron="${spring.batch.sayHelloTimeTask.cronExpression}")
    public void reportCurrentTime() {
    	logger.debug("Hello, The time is now " + dateFormat.format(new Date()));
    }
}
