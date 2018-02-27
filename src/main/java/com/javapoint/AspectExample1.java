package com.javapoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Employee{
	
	private int no;
	private String name;

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		System.out.println("executing get name in" + this.getClass().toString());
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "no:" + getNo() + " name:" + getName();
	}
}

class EmployeeService{
	
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}

@Aspect
class EmployeeAspect{
	
	@Before("execution(public String getName())")
	public void getNameAdvice() {
		System.out.println("executing advice on get name");
	}
	
	@Before("execution(* com.javapoint.EmployeeService.get*())")
	public void getAllAdvice() {
		System.out.println("executing advice for all Service method getter called");
	}
}

public class AspectExample1 {

	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AspectExample1.xml");
		EmployeeService empservice =  (EmployeeService) context.getBean("empservice");
		
		System.out.println("Employee details:" + empservice.getEmployee());

		System.out.println("Employee name:" + empservice.getEmployee().getName());

	}
	
}


/*********** OUTOUT *********************


executing advice for all Service method getter called
executing get name inclass com.javapoint.Employee
Employee details:no:1 name:name1
executing advice for all Service method getter called
executing advice on get name
executing get name inclass com.javapoint.Employee
Employee name:name1

***********************************************/