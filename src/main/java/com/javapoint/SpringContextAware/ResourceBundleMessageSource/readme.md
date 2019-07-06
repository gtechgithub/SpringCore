# ResourceBundleMessageSource Example

For an application to support internationalization, it requires the capability of resolving text messages for different locales. 
Spring’s application context is able to resolve text messages for a target locale by their keys. 

Typically, the messages for one locale should be stored in one separate properties file.
This properties file is called a resource bundle.

MessageSource is an interface that defines several methods for resolving messages. 
The ApplicationContext interface extends this interface so that all application contexts are able to resolve text messages.

1) Create resource bundle

**/src/main/resources/errormsg_en.properties**

```
errormsg.name= Enter name
errormsg.pwd= Enter password 
```

**/src/main/resources/errormsg_de.properties**

```
errormsg.name= Name eingeben
errormsg.pwd= Passwort eingeben

```

2) Configure ResourceBundleMessageSource bean definition

```
package MessageSourceResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

	@Bean
	public MessageSource getMessageSource() {
		
		ResourceBundleMessageSource resourceBundle = new ResourceBundleMessageSource();
		resourceBundle.setBasenames("i18/users","i18/errormsg");
		resourceBundle.setDefaultEncoding("UTF-8");
		return resourceBundle;
	}
}

```


3) Fetch the message directly

```

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
	   	
```




**OUTPUT**


```
2019-07-06 14:17:29.058  INFO 44308 --- [           main] M.SpringBootApplicationExample           : Started SpringBootApplicationExample in 1.56 seconds (JVM running for 2.259)
printing for english............. new Locale(en)......
admin:Jay
superadmin:Surya 
errorName: Enter name
errorPwd:Enter password 

 
printing for german............. new Locale(de)......
admin:Jaywrw
superadmin:Surya 111
errorName: Name eingeben
errorPwd:Passwort eingeben


```
