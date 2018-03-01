package com.javapoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSingletonExampleEx11
{
	public static void main (String args[]) {
		
		ApplicationContext context= new ClassPathXmlApplicationContext("SpringSingletonExampleEx11.xml");
		
		SpringPrototypeExample singleton1 = (SpringPrototypeExample) context.getBean("springsingleton");
		SpringPrototypeExample singleton2 = (SpringPrototypeExample) context.getBean("springsingleton");
		
		System.out.println("Hashcode of singleton1:" +  singleton1.hashCode());
		System.out.println("Hashcode of singleton2:" +  singleton2.hashCode());
	}
}


/*************OUTPUT **********************



Hashcode of singleton1:633070006
Hashcode of singleton2:633070006


*********************************************/