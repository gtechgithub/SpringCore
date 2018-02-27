package com.javapoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Aspect
class EmployeeAspectPointcutReuse{

	@Before("getNamePointCut()")
	public void getAdvice1() {
		System.out.println("inside execution of getAdvice1 advice aspect");
			
	}
	
	
	@Before("getNamePointCut()")
	public void getAdvice2() {
		System.out.println("inside execution of getAdvice2 advice aspect");
		
	}
	
	
	@Before ("getAllMethodsPointCut()")
	public void getAdviceAllMethod(){
		System.out.println("inside execution of getAdviceAllMethod advice aspect");
	}
	
	@Pointcut("execution(public String getName())")
	public void getNamePointCut() {
		System.out.println("inside execution of getNamePointCut advice aspect");
	}
	
	@Pointcut("execution(* com.javapoint.EmployeeService.get*())")

	public void getAllMethodsPointCut() {
		System.out.println("inside execution of getAllMethodsPointCut advice aspect");
	}
	
	
}

public class AspectAOPPointCutResuseEx2 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectAOPPointCutResuseEx2.xml");
		EmployeeService empservice =  (EmployeeService) context.getBean("empservice");
		
		System.out.println("Employee details:" + empservice.getEmployee());

	
		System.out.println("Employee name:" + empservice.getEmployee().getName());
	}
	
}


/************************ OUTPUT ******************************

inside execution of getAdviceAllMethod advice aspect
executing get name inclass com.javapoint.Employee
Employee details:no:1 name:name1
inside execution of getAdviceAllMethod advice aspect
inside execution of getAdvice1 advice aspect
inside execution of getAdvice2 advice aspect
executing get name inclass com.javapoint.Employee
Employee name:name1


************************ OUTPUT ******************************/

