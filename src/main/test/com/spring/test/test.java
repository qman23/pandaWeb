package com.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
	public static void main(String[] args) {  
        String[] locations = {"spring-context.xml"};  
        ClassPathXmlApplicationContext ctx =   
            new ClassPathXmlApplicationContext(locations);  
        System.out.println(  ctx.getBean("sqlSessionFactory"));  
        ctx.destroy();
    }  
}
