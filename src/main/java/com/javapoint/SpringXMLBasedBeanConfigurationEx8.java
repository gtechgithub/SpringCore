package com.javapoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class SpringXMLBasedBeanConfiguration {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

public class SpringXMLBasedBeanConfigurationEx8 {
	
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