package com.javapoint;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
class Student {

	private int no;
	private String name;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "no:" + no  + " name:" + name;
	}
}

@Service
class StudentService {
	
	private Student student;

	public StudentService() {
		System.out.println("This default constructor is used for byName and byType");
		student= null;
	}
	
	public StudentService(Student student) {
		System.out.println("This parameterized constructor is used for autowire by constructor");
		this.student= student;
	}
	
	public Student getStudent() {
		return student;
	}

	//public void setStudent(Student student) {
    //	this.student = student;
	//}
	
}

class StudentServiceAutowiredAnnotationByType{
	
	@Autowired
	private Student student;

	public Student getStudent() {
		return student;
	}
	
}

class StudentServiceAutowiredAnnotationByConstructor{
	
	@Autowired
	private Student student;

	
	public StudentServiceAutowiredAnnotationByConstructor(@Qualifier("student") Student student) {
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}
}

public class SpringAutowiringConceptsEx15 {
	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringAutowiringConceptsEx15.xml");
		
		StudentService  studentAutowireByName =     (StudentService) context.getBean("studentAutowireByName");
		System.out.println("student details Autowiring byName:" + studentAutowireByName.getStudent());

		StudentService  studentAutowireByType =     (StudentService) context.getBean("studentAutowireByType");
		System.out.println("student details Autowiring byType:" + studentAutowireByType.getStudent());

		
		StudentService  studentAutowireByConstructor =     (StudentService) context.getBean("studentAutowireByConstructor");
		System.out.println("student details Autowiring by Constructor:" + studentAutowireByConstructor.getStudent());

		
		StudentServiceAutowiredAnnotationByType  studentServiceAutowiredAnnotationByType =     
				(StudentServiceAutowiredAnnotationByType) context.getBean("studentServiceAutowiredAnnotationByType");
		System.out.println("student details Autowired Annotation by Type:" + studentServiceAutowiredAnnotationByType.getStudent());

		
		StudentServiceAutowiredAnnotationByConstructor  studentServiceAutowiredAnnotationByConstructor =     
				(StudentServiceAutowiredAnnotationByConstructor) context.getBean("studentServiceAutowiredAnnotationByConstructor");

		System.out.println("student details Autowired Annotation by Constructor:" + 
		                          studentServiceAutowiredAnnotationByConstructor.getStudent());

		
	}
}

/*************OUTPUT **********************




*********************************************/