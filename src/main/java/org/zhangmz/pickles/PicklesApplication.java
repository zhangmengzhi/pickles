package org.zhangmz.pickles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * 
 * @ClassName:PicklesApplication 
 * @Description:SpringBoot 应用
 * @author:张孟志
 * @date:2016年1月22日 下午7:42:52 
 * @version V1.0
 * 说明：启动SpringBoot 应用入口
 */
@SpringBootApplication
public class PicklesApplication {
	
	/**
	 * 启动嵌入式的Tomcat并初始化Spring环境.
	 * 
	 * 无 applicationContext.xml 和 web.xml, 靠下述方式进行配置：
	 * 
	 * 1. 扫描当前package下的class设置自动注入的Bean<br/>
	 * 2. 也支持用@Bean标注的类配置Bean <br/>
	 * 3. 根据classpath中的三方包Class及集中的application.properties条件配置三方包，如线程池 <br/>
	 * 4. 也支持用@Configuration标注的类配置三方包.
	 */
	public static void main(String[] args) {
        SpringApplication.run(PicklesApplication.class, args);
    }

	/**
	 * 
	 * @Title: containerCustomizer 
	 * @Description: 自定义常见错误页面
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月13日 上午9:57:19
	 * 说明：自定义常见错误页面 
	 *     error401Page, error403Page, error404Page, error500Page
	 *     注意这个方法使用Java 8的lambda表达式来简化实现的方式。
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

	   return (container -> {
    		ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/static/400.html");
	        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/static/401.html");
	        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/static/403.html");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/404.html");
	        ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/static/405.html");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/500.html");

	        container.addErrorPages(error400Page, error401Page, error403Page, error404Page, error405Page, error500Page);
	   });
	}
}
