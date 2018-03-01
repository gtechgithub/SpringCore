package com.javapoint;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class EmployeeLifeCycleInterface implements DisposableBean, InitializingBean {
	
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

	@Override
	public void afterPropertiesSet() {
		
		System.out.println("After properties set resources");
		no=1;
		name="name1";
	}

	@Override
	public void destroy() {
		System.out.println("Pre Destory resources");
	}
	
	@Override
	public String toString() {
		return "no:" + no  + " name:" + name;
	}
}

public class SpringLifeCycleInterfaceEx12 {
	public static void main(String args[]) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringLifeCycleInterfaceEx12.xml");
		EmployeeLifeCycleInterface empl =     (EmployeeLifeCycleInterface) context.getBean("employee");
		System.out.println("Employee detials:" + empl);
		
		//destroy a bean
		empl.destroy();
		empl = null;
	}
}

/*************OUTPUT **********************

19:29:17.270 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@79b4d0f: defining beans [employee]; root of factory hierarchy
19:29:17.271 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'employee'
19:29:17.271 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating instance of bean 'employee'
19:29:17.288 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Eagerly caching bean 'employee' to allow for resolving potential circular references
19:29:17.336 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Invoking afterPropertiesSet() on bean with name 'employee'

After properties set resources

19:29:17.338 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Finished creating instance of bean 'employee'
19:29:17.339 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Unable to locate LifecycleProcessor with name 'lifecycleProcessor': using default [org.springframework.context.support.DefaultLifecycleProcessor@1dfe2924]
19:29:17.340 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'lifecycleProcessor'
19:29:17.343 [main] DEBUG org.springframework.core.env.PropertySourcesPropertyResolver - Could not find key 'spring.liveBeansView.mbeanDomain' in any property source
19:29:17.344 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Returning cached instance of singleton bean 'employee'

Employee detials:no:1 name:name1
Pre Destory resources





*********************************************/