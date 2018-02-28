package com.javapoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;



@Aspect
class EmployeeAspectAround{
    
	@Around("execution(* com.javapoint.EmployeeService.get*())")
	public Object EmployeeServiceAdviceAround(ProceedingJoinPoint proceedjoinpoint) {
		
		Object returnObject = null;
		
		try {
			System.out.println("inside Around advice EmployeeServiceAdviceAround");
			returnObject = proceedjoinpoint.proceed();
			
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		
		return returnObject;
		
	}
	
}
@Component
public class AspectAOPAroundEx5 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectAOPAround.xml");

		EmployeeService empservice =  (EmployeeService) context.getBean("empservice");
		System.out.println("Employee details:" + empservice.getEmployee());

	}
	
}


/************************ OUTPUT ******************************

inside Around advice EmployeeServiceAdviceAround
executing get employee in class com.javapoint.EmployeeService
executing get name in class com.javapoint.Employee
Employee details:no:1 name:name1

************************ OUTPUT ******************************/

