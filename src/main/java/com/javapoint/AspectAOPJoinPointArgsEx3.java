package com.javapoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Aspect
class EmployeeAspectJointPointArgs{
                           
	@Before("execution(* com.javapoint.*.set*(..))")

	public void loggingAdvice(JoinPoint jointpoint) {
		System.out.println("inside loggingadvice method");
		
		//System.out.println("joinpoint target:"+ joinpoint.getTarget());
		//System.out.println("join point args" + joinpoint.getArgs());
	}
	
	@Before("args(name)")
	public void logStringArguments(String name){
		System.out.println("String argument passed="+name);
	}
}


public class AspectAOPJoinPointArgsEx3 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectAOPJoinPointArgsEx3.xml");

		EmployeeService empservice =  (EmployeeService) context.getBean("empservice");
		System.out.println("Employee details:" + empservice.getEmployee());

		Employee emp = new Employee();
		emp.setName("name2");
		emp.setNo(2);
		EmployeeService empservice1 = new EmployeeService();
		empservice1.setEmployee(emp);
		
		
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

