package ex;

import org.springframework.stereotype.Service;

@Service
class StudentService {
	
	private Student student;

	
	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentService() {
		System.out.println("This default constructor is used for byName and byType");
	}
	
	public Student getStudent() {
		return student;
	}

}
