package com.utils.business;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


/**
 * Spring Util. Use to provide the bean which handle by the spring framework. 
 * @author Allen
 *
 */
@Service("springUtil")
public class SpringUtil implements ApplicationContextAware{

	/**
	 * spring context 
	 */
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringUtil.applicationContext = arg0;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws BeansException
	 * 
	 * provide the bean instance by the parameter name.
	 */
	public static Object getBean(String name) throws BeansException {
	    return applicationContext.getBean(name);
	}
}
