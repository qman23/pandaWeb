package com.utils.business;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Service("springUtil")
public class SpringUtil implements ApplicationContextAware{

	
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringUtil.applicationContext = arg0;
	}
	
	public static Object getBean(String name) throws BeansException {
	    return applicationContext.getBean(name);
	}
}
