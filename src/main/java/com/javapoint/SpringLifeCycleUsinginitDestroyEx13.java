package com.javapoint;

import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;

class SpringLifeCycleUsinginitDestroy {

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

	public void init() {
		System.out.println(" post init method");
		no=1;
		name="name1";
	}
	
	
	public void destroy() {
		System.out.println(" pre destory method");
	}
	
	@Override
	public String toString() {
		return "no:" + no  + " name:" + name;
	}
}

public class SpringLifeCycleUsinginitDestroyEx13 {
	public static void main(String args[]) {
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("SpringLifeCycleUsinginitDestroyEx13.xml");
		SpringLifeCycleUsinginitDestroy empl =     (SpringLifeCycleUsinginitDestroy) context.getBean("employee");
		System.out.println("Employee detials:" + empl);
		
		
		//context.registerShutdownHook();
		// or 
		context.close();
	}
}

/*************OUTPUT **********************

19:43:20.971 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@79b4d0f: defining beans [employee]; root of factory hierarchy
19:43:20.972 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'employee'
19:43:20.972 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating instance of bean 'employee'
19:43:20.994 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Eagerly caching bean 'employee' to allow for resolving potential circular references
19:43:21.049 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Invoking init method  'init' on bean with name 'employee'
 post init method
19:43:21.051 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Finished creating instance of bean 'employee'
19:43:21.053 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Unable to locate LifecycleProcessor with name 'lifecycleProcessor': using default [org.springframework.context.support.DefaultLifecycleProcessor@1f36e637]
19:43:21.053 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'lifecycleProcessor'
19:43:21.055 [main] DEBUG org.springframework.core.env.PropertySourcesPropertyResolver - Could not find key 'spring.liveBeansView.mbeanDomain' in any property source
19:43:21.057 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'employee'
Employee detials:no:1 name:name1
19:43:21.057 [main] INFO org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@cac736f: startup date [Thu Mar 01 19:43:20 IST 2018]; root of context hierarchy
19:43:21.057 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'lifecycleProcessor'
19:43:21.057 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Destroying singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@79b4d0f: defining beans [employee]; root of factory hierarchy
19:43:21.057 [main] DEBUG org.springframework.beans.factory.support.DisposableBeanAdapter - Invoking destroy method 'destroy' on bean with name 'employee'
 pre destory method




*********************************************/