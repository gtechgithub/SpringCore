package ApplicationContextAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class package ApplicationContextAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringContextAwareApplication {
		
	public static void main(String args[]) {
		ApplicationContext applicationContext = SpringApplication.run(SpringContextAwareApplication.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
			
			
		}
		
		//print the student servcie bean names
		StudentService stuservice = (StudentService) applicationContext.getBean("studentService");
		System.out.println("student:" + stuservice.getStudent());


	}
}
 {
		
	public static void main(String args[]) {
		ApplicationContext applicationContext = SpringApplication.run(SpringContextAwareApplication.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
			
			
		}
		
		//print the student servcie bean names
		StudentService stuservice = (StudentService) applicationContext.getBean("studentService");
		System.out.println("student:" + stuservice.getStudent());


	}
}
