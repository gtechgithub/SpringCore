package ApplicationContextAware;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

class Student {

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
	public String toString() {
		return "no:" + no  + " name:" + name;
	}
}

