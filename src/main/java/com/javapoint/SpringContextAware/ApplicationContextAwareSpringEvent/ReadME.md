# Event Publishere and listener using applicationContextAware

Events are one of the more overlooked functionalities in the framework but also one of the more useful.
And – like many other things in Spring – event publishing is one of the capabilities provided by ApplicationContext.
There are a few simple guidelines to follow:

* the event should extend ApplicationEvent
* the publisher should inject an ApplicationEventPublisher object
* the listener should implement the ApplicationListener interface

* Create a custom event

```
@Component
public class CustomEvent   {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

```

* create a event publishier

```
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



```


* create a event listener

```
@Component
public class MyEventListener  {


	@EventListener
	public void getEventMessage(CustomEvent event) {
		System.out.println("Event Message:" + event.getMessage() );
	}
}


```

* spring boot application to getsend message and receiver message


```
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
```

## OUTPUT

```

2019-07-04 23:43:07.959  INFO 49224 --- [           main] A.SpringEventBootApplication             : Started SpringEventBootApplication in 1.571 seconds (JVM running for 2.181)
Event Message:hello how ru 
Event Message:hello how ru 

```
