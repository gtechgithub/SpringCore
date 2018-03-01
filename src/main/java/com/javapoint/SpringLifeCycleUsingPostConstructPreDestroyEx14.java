package com.javapoint;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
class SpringLifeCycleUsingPostConstructPreDestroy {

	private int no;
	private String name;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PostConstruct
	public void init() {
		System.out.println(" post init method");
		no=1;
		name="name1";
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println(" pre destory method");
	}
	
	@Override
	public String toString() {
		return "no:" + no  + " name:" + name;
	}
}

public class SpringLifeCycleUsingPostConstructPreDestroyEx14 {
	public static void main(String args[]) {
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("SpringLifeCyclePostConstructPreDestroyEx14.xml");
		SpringLifeCycleUsingPostConstructPreDestroy empl =     (SpringLifeCycleUsingPostConstructPreDestroy) context.getBean("employee");
		System.out.println("Employee detials:" + empl);
		
		
		//context.registerShutdownHook();
		// or 
		context.close();
	}
}

/*************OUTPUT **********************

20:00:20.923 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'employee'
20:00:20.923 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating instance of bean 'employee'
20:00:20.927 [main] DEBUG org.springframework.context.annotation.CommonAnnotationBeanPostProcessor - Found init method on class [com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy]: public void com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy.init()
20:00:20.927 [main] DEBUG org.springframework.context.annotation.CommonAnnotationBeanPostProcessor - Found destroy method on class [com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy]: public void com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy.destroy()
20:00:20.928 [main] DEBUG org.springframework.context.annotation.CommonAnnotationBeanPostProcessor - Registered init method on class [com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy]: org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleElement@316510
20:00:20.928 [main] DEBUG org.springframework.context.annotation.CommonAnnotationBeanPostProcessor - Registered destroy method on class [com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy]: org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleElement@5cd39ffa
20:00:20.934 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Eagerly caching bean 'employee' to allow for resolving potential circular references
20:00:20.964 [main] DEBUG org.springframework.context.annotation.CommonAnnotationBeanPostProcessor - Invoking init method on bean 'employee': public void com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy.init()
 post init method
20:00:20.965 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Finished creating instance of bean 'employee'
20:00:20.965 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
20:00:21.004 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Unable to locate LifecycleProcessor with name 'lifecycleProcessor': using default [org.springframework.context.support.DefaultLifecycleProcessor@45afc369]
20:00:21.004 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'lifecycleProcessor'
20:00:21.006 [main] DEBUG org.springframework.core.env.PropertySourcesPropertyResolver - Could not find key 'spring.liveBeansView.mbeanDomain' in any property source
20:00:21.009 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'employee'
Employee detials:no:1 name:name1
20:00:21.009 [main] INFO org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@cac736f: startup date [Thu Mar 01 20:00:20 IST 2018]; root of context hierarchy
20:00:21.009 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'lifecycleProcessor'
20:00:21.010 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Destroying singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@643b1d11: defining beans [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,employee]; root of factory hierarchy
20:00:21.010 [main] DEBUG org.springframework.context.annotation.CommonAnnotationBeanPostProcessor - Invoking destroy method on bean 'employee': public void com.javapoint.SpringLifeCycleUsingPostConstructPreDestroy.destroy()
 pre destory method



*********************************************/