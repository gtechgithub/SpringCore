package ApplicationContextAwareResources;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@XmlRootElement
class Staff {

	String firstname;
	String lastname;
	double salary;
	String nickname;

	public String getFirstname() {
		return firstname;
	}

	@XmlElement
	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	@XmlElement
	public void setLastname(String lastName) {
		this.lastname = lastName;
	}

	public double getSalary() {
		return salary;
	}

	@XmlElement
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getNickname() {
		return nickname;
	}

	@XmlElement
	public void setNickname(String nickName) {
		this.nickname = nickName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}
}

@XmlRootElement
public class Company {
	ArrayList<Staff> staff;

	public ArrayList<Staff> getStaff() {
		return staff;
	}

	@XmlElement
	public void setStaff(ArrayList<Staff> staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}
}
