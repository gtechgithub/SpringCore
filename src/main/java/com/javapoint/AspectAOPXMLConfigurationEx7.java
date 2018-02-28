package com.javapoint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


@Component
class AspectAOPXMLConfiguration{
	
	public Object employeeServiceAroundAdvice(ProceedingJoinPoint proceedingjoinpoint) {
		
		Object objectValue = null;
		
		try {
			System.out.println("Inside employee service around advice xml configuration");
			objectValue = proceedingjoinpoint.proceed();
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		return objectValue;
	}
}

public class AspectAOPXMLConfigurationEx7 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectAOPXMLConfiguration.xml");
		EmployeeService empservice = (EmployeeService) context.getBean("empservice");
		
		System.out.println(empservice.getEmployee());
		
	}
	
}


/*********************OUTPUT***************************

Inside employee service around advice xml configuration
executing get employee in class com.javapoint.EmployeeService
executing get name in class com.javapoint.Employee
no:1 name:name1

********************************************************/