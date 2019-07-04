package ApplicationContextAware;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

class StudentService {
	
	private Student student;

	@Autowired
	SpringApplicationContextAware applicationContext;
	
	public StudentService() {
		System.out.println("This default constructor is used for byName and byType");
	}
	

	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	public void init() {
		System.out.println("inside init method");
		student = (Student) applicationContext.getApplicationContext().getBean("student");
		//this.student.setNo (10000);
		//this.student.setName("this is for test");
		

	}
	
	public void destroy() {
		System.out.println("inside destory method");
		
	}
}
