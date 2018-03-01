package com.javapoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class SpringPrototypeExample{
	
	private int no;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}

public class SpringPrototypeExampleEx10
{
	public static void main (String args[]) {
		
		ApplicationContext context= new ClassPathXmlApplicationContext("SpringPrototypeExampleEx10.xml");
		
		SpringPrototypeExample prototype1 = (SpringPrototypeExample) context.getBean("springprototype");
		SpringPrototypeExample prototype2 = (SpringPrototypeExample) context.getBean("springprototype");
		
		System.out.println("Hashcode of prototype1:" +  prototype1.hashCode());
		System.out.println("Hashcode of prototype2:" +  prototype2.hashCode());
	}
}


/*********************************OUTPUT ************************************


Hashcode of prototype1:1846896625
Hashcode of prototype2:1555690610

*****************************************************************************/