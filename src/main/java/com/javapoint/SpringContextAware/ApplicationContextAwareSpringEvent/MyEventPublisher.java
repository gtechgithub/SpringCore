package ApplicationContextAwareSpringEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher implements ApplicationEventPublisherAware{

	
    ApplicationEventPublisher publisher;
    
	@Autowired
	CustomEvent customEvent;
	
	public void sendMessage(String message) {
		customEvent.setMessage(message);
		publisher.publishEvent(customEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
	}
	

	
}

