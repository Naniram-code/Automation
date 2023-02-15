package classnote.udemy;

import java.util.Arrays;

public class Student {

	private String name;
	private String location;
	private String phone;
	private String courses[];

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", location='" + location + '\'' +
				", phone='" + phone + '\'' +
				", courses=" + Arrays.toString(courses) +
				'}';
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	
}
