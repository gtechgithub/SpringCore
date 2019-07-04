# use of ApplicationContextAware interface


implememt this interface, if you are willing to collaborate with bean lookup process.



a) if an object needs access to file resources

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationContextAware.html


b) wants to call getResource

https://stackoverflow.com/questions/22096983/spring-mvc-read-file-from-src-main-resources

c) want to publish an event

@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)


https://www.baeldung.com/spring-events


d) requeries an access to the message source.




* here we have used used ImportResource which will import the spring context beans and it creates the bean. 

```
@Configuration
@ImportResource({"classpath*:SpringContextAwareConfig.xml"})
public class SpringXmlConfiguration {

}

* secondly, we have implements SpringContextAware interface to get the beans initiated information.

```
@Component
public class SpringApplicationContextAware  implements ApplicationContextAware{

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setContext(applicationContext);
	}

	private static void setContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return context;
	}
}	
  ```
  

* thirdly, we have reading beans here.

```
@SpringBootApplication
public class SpringContextAwareApplication {
		
	public static void main(String args[]) {
		ApplicationContext applicationContext = SpringApplication.run(SpringContextAwareApplication.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
			
			
		}
		
		//print the student servcie bean names
		StudentService stuservice = (StudentService) applicationContext.getBean("studentService");
		System.out.println("student:" + stuservice.getStudent());


	}
}
```

* one important thing is that since we are importingResources using beans are alraedy created hence no need to 
include @Component in Student and @Service in StudentService.

* output

```
2019-07-04 13:53:15.801  INFO 64252 --- [           main] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
This default constructor is used for byName and byType
inside init method
2019-07-04 13:53:16.143  INFO 64252 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-07-04 13:53:16.428  INFO 64252 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9090 (http) with context path ''
2019-07-04 13:53:16.435  INFO 64252 --- [           main] A.SpringContextAwareApplication          : Started SpringContextAwareApplication in 3.307 seconds (JVM running for 4.786)
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
springContextAwareApplication
org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
springApplicationContextAware
springXmlConfiguration
student
studentService
org.springframework.boot.autoconfigure.AutoConfigurationPackages
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
org.springframework.boot.autoconfigure.condition.BeanTypeRegistry
propertySourcesPlaceholderConfigurer
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration$TomcatWebSocketConfiguration
websocketServletWebServerCustomizer
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration$EmbeddedTomcat
tomcatServletWebServerFactory
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
servletWebServerFactoryCustomizer
tomcatServletWebServerFactoryCustomizer
server-org.springframework.boot.autoconfigure.web.ServerProperties
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata
webServerFactoryCustomizerBeanPostProcessor
errorPageRegistrarBeanPostProcessor
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletConfiguration
dispatcherServlet
spring.http-org.springframework.boot.autoconfigure.http.HttpProperties
spring.mvc-org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration$DispatcherServletRegistrationConfiguration
dispatcherServletRegistration
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
taskExecutorBuilder
applicationTaskExecutor
spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration
defaultValidator
methodValidationPostProcessor
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration
error
beanNameViewResolver
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration
conventionErrorViewResolver
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
errorAttributes
basicErrorController
errorPageCustomizer
preserveErrorControllerTargetClassPostProcessor
spring.resources-org.springframework.boot.autoconfigure.web.ResourceProperties
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter$FaviconConfiguration
faviconHandlerMapping
faviconRequestHandler
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
requestMappingHandlerAdapter
requestMappingHandlerMapping
mvcConversionService
mvcValidator
mvcContentNegotiationManager
mvcPathMatcher
mvcUrlPathHelper
viewControllerHandlerMapping
beanNameHandlerMapping
resourceHandlerMapping
mvcResourceUrlProvider
defaultServletHandlerMapping
mvcUriComponentsContributor
httpRequestHandlerAdapter
simpleControllerHandlerAdapter
handlerExceptionResolver
mvcViewResolver
mvcHandlerMappingIntrospector
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter
defaultViewResolver
viewResolver
welcomePageHandlerMapping
requestContextFilter
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
hiddenHttpMethodFilter
formContentFilter
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
mbeanExporter
objectNamingStrategy
mbeanServer
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
springApplicationAdminRegistrar
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration
standardJacksonObjectMapperBuilderCustomizer
spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperBuilderConfiguration
jacksonObjectMapperBuilder
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$ParameterNamesModuleConfiguration
parameterNamesModule
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperConfiguration
jacksonObjectMapper
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
jsonComponentModule
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration$StringHttpMessageConverterConfiguration
stringHttpMessageConverter
org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration$MappingJackson2HttpMessageConverterConfiguration
mappingJackson2HttpMessageConverter
org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
messageConverters
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$LoggingCodecConfiguration
loggingCodecCustomizer
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration$JacksonCodecConfiguration
jacksonCodecCustomizer
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration
taskSchedulerBuilder
spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration
restTemplateBuilder
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration
tomcatWebServerFactoryCustomizer
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
characterEncodingFilter
localeCharsetMappingsCustomizer
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
multipartConfigElement
multipartResolver
spring.servlet.multipart-org.springframework.boot.autoconfigure.web.servlet.MultipartProperties
student:no:1 name:gopal
```
