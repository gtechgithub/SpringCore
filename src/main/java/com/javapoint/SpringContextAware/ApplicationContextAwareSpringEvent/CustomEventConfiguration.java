package ApplicationContextAwareSpringEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomEventConfiguration {

	@Bean
	public MyEventPublisher getEventPublisher() {
		return new MyEventPublisher();
	}
	
	@Bean
	public MyEventListener getEventListner() {
		return new MyEventListener();
	}
}
