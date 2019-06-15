package ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAutowiringConcepts {

	public static void main(String args[]) {

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("SpringAutowiringConceptsByName.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");
		System.out.println("student:" + studentService.getStudent());
		context.registerShutdownHook();
	}
}
