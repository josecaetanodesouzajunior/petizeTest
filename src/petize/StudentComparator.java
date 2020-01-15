package petize;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		if (s1.getSurname().equals(s2.getSurname())) {
			return s1.getName().compareTo(s2.getName());
		}
		else {
			return s1.getSurname().compareTo(s2.getSurname());
		}
		
	}

}
