# ApplicationEventMulticaster

## Async Events

Async events
By default event listeners receive events synchronously, 
meaning that the publishing thread will block until all listeners have finished processing the event. 
The advantage of this is that if the publisher is running in a transactional context, 
the listener will receive the event within the same transactional context. 
However if processing events takes long time and scaling is important we can tell Spring to handle events asynchronously.
In order to do this we need to redefine the ApplicationEventMulticaster bean
with id applicationEventMulticaster configuring it with an asynchronous TaskExecutor.


### refer to the below read me 
https://github.com/gtechgithub/SpringCore/blob/master/src/main/java/com/javapoint/SpringContextAware/ApplicationContextAwareSpringEvent/ReadME.md

### except configuration as the bean declartion for ApplicationEventMulticaster

```
	@Bean
	public ApplicationEventMulticaster simpleAppicationEventMulticaster() {
		SimpleApplicationEventMulticaster simpleMultiCaster = new SimpleApplicationEventMulticaster();
		simpleMultiCaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
		
		return simpleMultiCaster;
		
	}

```

### output

```
2019-07-06 13:13:05.190  INFO 24128 --- [           main] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
inside event publisher
2019-07-06 13:13:05.354  INFO 24128 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-07-06 13:13:05.510  INFO 24128 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9090 (http) with context path ''
2019-07-06 13:13:05.513  INFO 24128 --- [           main] A.SpringEventBootApplication             : Started SpringEventBootApplication in 1.666 seconds (JVM running for 2.486)
Event Message:hello how ru 
Event Message:hello how ru 
```
