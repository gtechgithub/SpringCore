package controllerAdvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "controllerAdvice")
@SpringBootApplication
public class ControllerAdviceBootApplication {

	public static void main(String args[]) {
		SpringApplication.run(ControllerAdviceBootApplication.class, args);
	}
}
