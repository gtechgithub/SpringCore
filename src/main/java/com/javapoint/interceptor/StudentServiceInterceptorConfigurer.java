package interceptorEX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class StudentServiceInterceptorConfigurer implements WebMvcConfigurer {
	
	//please note that Abstract class WebMvcConfigurerAdapter is decomissioned, 
	//hence use WebMvcConfigurer interface

	@Autowired
	StudentServiceInterceptor studentServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(studentServiceInterceptor);
	}

}
