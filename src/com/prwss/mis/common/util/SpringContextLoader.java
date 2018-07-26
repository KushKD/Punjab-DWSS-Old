package com.prwss.mis.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLoader {
	
	private SpringContextLoader() {
	}

	private static ApplicationContext ctx = null;

	static{
		
		if(ctx == null){
			ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		}
	}
	
	public static Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
	

}
