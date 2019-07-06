package ApplicationContextAwareSpringEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class CustomEventConfiguration {

	@Bean
	public MyEventPublisher getEventPublisher() {
		System.out.println("inside event publisher");
		return new MyEventPublisher();
	}
	
	@Bean
	public MyEventListener getEventListner() {
		return new MyEventListener();
	}
	
	@Bean
	public ApplicationEventMulticaster simpleAppicationEventMulticaster() {
		SimpleApplicationEventMulticaster simpleMultiCaster = new SimpleApplicationEventMulticaster();
		simpleMultiCaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
		
		return simpleMultiCaster;
		
	}
}
