package interceptorEX;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

	@RequestMapping(value = "/getStudent")
	public ResponseEntity<?> getStudent(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("SpringInterceptorConfig.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");
		System.out.println("student:" + studentService.getStudent());
		context.registerShutdownHook();
		
	      return new ResponseEntity<>(studentService.getStudent(), HttpStatus.OK);

	}
}

/**************** OUTPUT ***********************

http://localhost:9090/getStudent


Response Body:
{
    "no": 10000,
    "name": "this is for test"
}


**************** OUTPUT ***********************/

