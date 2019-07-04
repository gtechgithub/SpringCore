package ApplicationContextAwareResources;

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
import org.springframework.core.io.Resource;

@SpringBootApplication
public class SpringContextResourcesSpringBootApplication {
		
	public static void main(String args[]) throws JAXBException, IOException {
		ApplicationContext applicationContext = SpringApplication.run(SpringContextResourcesSpringBootApplication.class, args);

		//get the resource from the application context
		Resource  res  = applicationContext.getResource("classpath:staff.xml");
		
		File file = res.getFile();
		
		//Jaxb context for xml files parser
		JAXBContext jaxbContext  =  JAXBContext.newInstance(Company.class);
		
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		//jaxbUnMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		  
		Company company = (Company) jaxbUnMarshaller.unmarshal(file);
		
		System.out.println("company:" + company);

		
	}
}

