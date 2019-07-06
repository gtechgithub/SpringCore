package MessageSourceResourceBundle;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplicationExample {

	public static void main(String args[]) {
		ApplicationContext context = SpringApplication.run(SpringBootApplicationExample.class, args);
		
		//bean name method is defined in the configuration class
		MessageSource  messageSource = (MessageSource) context.getBean("getMessageSource");
		
		System.out.println("printing for english............. new Locale(en)......");
	   	String admin = messageSource.getMessage("user.admin", null, "Default", new Locale("en"));
	   	System.out.println("admin:"+ admin);		

	   	String superadmin = messageSource.getMessage("user.superadmin", null, "Default", new Locale("en"));
	   	System.out.println("superadmin:" + superadmin);	

	   	
	   	String errorName = messageSource.getMessage("errormsg.name", null, "Default", new Locale("en"));
	   	System.out.println("errorName: "+ errorName);		

	   	String errorPwd = messageSource.getMessage("errormsg.pwd", null, "Default", new Locale("en"));
	   	System.out.println("errorPwd:"+ errorPwd);		

		System.out.println("\n \nprinting for german............. new Locale(de)......");

	   	admin = messageSource.getMessage("user.admin", null, "Default", new Locale("de"));
	   	System.out.println("admin:"+ admin);		

	   	superadmin = messageSource.getMessage("user.superadmin", null, "Default", new Locale("de"));
	   	System.out.println("superadmin:" + superadmin);	

	   	
	   	errorName = messageSource.getMessage("errormsg.name", null, "Default", new Locale("de"));
	   	System.out.println("errorName: "+ errorName);		

	   	errorPwd = messageSource.getMessage("errormsg.pwd", null, "Default", new Locale("de"));
	   	System.out.println("errorPwd:"+ errorPwd);	
	   	

	}
}
