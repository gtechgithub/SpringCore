package ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
class StudentService {
	
	@Autowired
	@Qualifier("studentTwo")
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

}
