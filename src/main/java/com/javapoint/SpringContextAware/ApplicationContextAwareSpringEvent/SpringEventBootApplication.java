package ApplicationContextAwareSpringEvent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.swing.text.MaskFormatter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"ApplicationContextAwareSpringEvent"})
@SpringBootApplication
public class SpringEventBootApplication {
		
	public static void main(String args[]) throws JAXBException, IOException {

		ApplicationContext context = SpringApplication.run(SpringEventBootApplication.class, args);
//		   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
//				   CustomEventConfiguration.class);
		   
		MyEventPublisher bean = (MyEventPublisher) context.getBean("myEventPublisher");
        bean.sendMessage("hello how ru ");
	}
}

