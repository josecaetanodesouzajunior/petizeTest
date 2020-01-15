package petize;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Student {

	private String name;
	private String surname;
	private List<Class> classes = new ArrayList<Class>();


	public Student(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public LocalTime getCourseHoursPerWeek() {

		List<Class> list = getClasses();
		Long result;
		result = list.stream().mapToLong(item -> item.getDailyCourseHours()).sum();		
		return LocalTime.ofNanoOfDay(result);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}


	@Override
	public String toString() {
		return "Student [name=" + name + ", surname=" + surname + ", classes=" + classes + "]";
	}





}
