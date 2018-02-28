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
import org.springframework.stereotype.Component;



@Aspect
class EmployeeAspectAfter{
    
	
	@After("args(name)")
	public void loggingAdvice(String name) {
		System.out.println("Running After Advice. String argument passed="+name);	
	}

	@AfterThrowing("within(com.javapoint.Employee)")
	public void logExceptions(JoinPoint joinpoint) {
		System.out.println("Exception thrown in Employee Method="+joinpoint.toString());
	}
	
	@AfterReturning(pointcut="execution(* com.javapoint.Employee.getName())",returning="returnString")
	public void getNameReturningAdvice(String returnString) {
		System.out.println("getNameReturningAdvice executed. Returned String="+returnString);
	}
}
@Component
public class AspectAOPAfterEx4 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectAOPAfter.xml");

		EmployeeService empservice =  (EmployeeService) context.getBean("empservice");
		System.out.println("Employee details:" + empservice.getEmployee());

		
		System.out.println("Employee name:" + empservice.getEmployee().getName());

		try {
			empservice.getEmployee().throwException();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}


/************************ OUTPUT ******************************

executing get employee in class com.javapoint.EmployeeService
executing get name in class com.javapoint.Employee
Employee details:no:1 name:name1
executing get employee in class com.javapoint.EmployeeService
executing get name in class com.javapoint.Employee
21:08:59.346 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'employeeAspect'
getNameReturningAdvice executed. Returned String=name1
Employee name:name1
executing get employee in class com.javapoint.EmployeeService
Exception thrown in Employee Method=execution(void com.javapoint.Employee.throwException())
java.lang.Exception
	at com.javapoint.Employee.throwException(AspectExample1.java:34)
	at com.javapoint.Employee$$FastClassBySpringCGLIB$$93de59b.invoke(<generated>)
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)
	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)
	at com.javapoint.Employee$$EnhancerBySpringCGLIB$$f2483655.throwException(<generated>)
	at com.javapoint.AspectAOPAfterEx4.main(AspectAOPAfterEx4.java:50)


************************ OUTPUT ******************************/

