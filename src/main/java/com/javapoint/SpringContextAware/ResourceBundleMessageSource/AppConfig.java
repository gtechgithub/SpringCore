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
