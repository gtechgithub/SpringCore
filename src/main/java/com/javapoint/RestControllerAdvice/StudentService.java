package controllerAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
class StudentService {
	
	@Autowired
	private Student student;

	
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
//		this.student.setNo (10000);
//		this.student.setName("this is for test");
	}
	
	public void destroy() {
		System.out.println("inside destory method");
		
	}
}
