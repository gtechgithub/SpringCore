package com.javapoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class SpringAnnotationBasedSpringBean {


}

public class SpringAnnotationBasedSpringBeanEx9 {
	
	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("servlet-context.xml");
		SpringXMLBasedBeanConfiguration springXMLBasedBeanConfiguration 
		                = (SpringXMLBasedBeanConfiguration) context.getBean("springXMLBasedBeanConfiguration");
		
		System.out.println("name:" + springXMLBasedBeanConfiguration.getName());
		
	}
}

/*************** output **************

name:null

**************************************/