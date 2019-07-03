package controllerAdvice;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

	@RequestMapping(value = "/getStudentException")
	public ResponseEntity<?> getStudentException() throws CustomizedException{
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("SpringControllerAdviceConfig.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");
		System.out.println("student:" + studentService.getStudent());
		context.registerShutdownHook();
		
		if (StringUtils.isEmpty(studentService.getStudent().getName())) {
			throw new CustomizedException("Stundent is not found, throwing exception");
		}
	    
		return new ResponseEntity<>(studentService.getStudent(), HttpStatus.OK);

	}
}
