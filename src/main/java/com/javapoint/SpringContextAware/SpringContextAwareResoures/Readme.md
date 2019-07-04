# Appication Context Aware get recouces.

This is an simple example to implement ApplicationContextAware and with that context get the 
resource namely a XML file in the class path and finally read the xml file using JAXB unmarsherller.



* ApplicationContextAware implements

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

* getting resource file from the application context


```
		ApplicationContext applicationContext = SpringApplication.run(SpringContextResourcesSpringBootApplication.class, args);

		//get the resource from the application context
		Resource  res  = applicationContext.getResource("classpath:staff.xml");
 		File file = res.getFile();

```

* upon File, reading xml file using JAXBContext and unMarshaller.

```
		
		//Jaxb context for xml files parser
		JAXBContext jaxbContext  =  JAXBContext.newInstance(Company.class);
		
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		//jaxbUnMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		  
		Company company = (Company) jaxbUnMarshaller.unmarshal(file);
		
		System.out.println("company:" + company);
```

* OUTPUT 

```
company:ApplicationContextAwareResources.Company@143defe[staff=[ApplicationContextAwareResources.Staff@efa415[firstname=yong,lastname=mook kim,salary=100000.0,nickname=mkyong], ApplicationContextAwareResources.Staff@1d7f28[firstname=low,lastname=yin fong,salary=200000.0,nickname=fong fong]]]

```
