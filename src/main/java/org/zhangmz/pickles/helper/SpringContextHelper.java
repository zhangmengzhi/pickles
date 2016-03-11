/**
 * 
 */
package org.zhangmz.pickles.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName:SpringContextHelper.java
 * @Description:
 * @author:张孟志
 * @date:2016年3月11日下午6:04:19
 * @version V1.0
 * 说明：
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext; //Spring应用上下文环境
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHelper.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Object getBean(String name) throws BeansException {
	return applicationContext.getBean(name);
	}


	public static Object getBean(String name, Class requiredType) throws BeansException {
	return applicationContext.getBean(name, requiredType);
	}


	public static boolean containsBean(String name) {
	return applicationContext.containsBean(name);
	}


	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
	return applicationContext.isSingleton(name);
	}


	public static Class getType(String name) throws NoSuchBeanDefinitionException {
	return applicationContext.getType(name);
	}


	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
	return applicationContext.getAliases(name);
	}
}
