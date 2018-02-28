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

/******
 * 
 * Spring Advice with Custom Annotation Pointcut
  If you look at all the above advices pointcut expressions, 
  there are chances that they gets applied to some other beans where it’s not intended. 
  For example, someone can define a new spring bean with getName() method 
  and the advices will start getting applied to that even though it was not intended. 
  That’s why we should keep the scope of pointcut expression as narrow as possible.

  An alternative approach is to create a custom annotation and annotate the methods 
  where we want the advice to be applied. 
  This is the purpose of having Employee setName() method annotated with @Loggable annotation.

 * @author gocothakotai
 *
 */

class EmployeeSample{
	
	private int no;
	private String name;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	@CustomAnotationForGetName
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}


@Aspect
class EmployeeAspectCustomAnnotationPointCut{
    
	@Before("@annotation(com.javapoint.CustomAnotationForGetName)")
	public void EmployeeServiceAdviceAround() {
		
			System.out.println("inside custom advice EmployeeServiceAdviceAround");
		
	}
	
}
@Component
public class AspectAOPCustomAnotationEx6 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectAOPCustomAnnotation.xml");

		EmployeeSample empservice =  (EmployeeSample) context.getBean("empsample");
		System.out.println("Employee no:" + empservice.getNo());
		System.out.println("Employee name:" + empservice.getName());

	}
	
}


/************************ OUTPUT ******************************

Employee no:1
10:19:38.307 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'employeeAspect'
inside custom advice EmployeeServiceAdviceAround
Employee name:name1

************************ OUTPUT ******************************/

