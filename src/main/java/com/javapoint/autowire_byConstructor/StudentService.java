package ex;

import org.springframework.stereotype.Service;

@Service
class StudentService {
	
	private Student student;

	
	public StudentService() {
		System.out.println("This default constructor is used for byName and byType");
	}
	
	
	public StudentService(Student student) {
		System.out.println("This is autowiring by construtor");
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}

}
