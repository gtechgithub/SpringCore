package ApplicationContextAwareSpringEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener  {


	@EventListener
	public void getEventMessage(CustomEvent event) {
		System.out.println("Event Message:" + event.getMessage() );
	}
}
