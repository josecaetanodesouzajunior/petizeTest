package petize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static Class class1 = new Class(DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(20, 0));
	static Class class2 = new Class(DayOfWeek.TUESDAY, LocalTime.of(17, 0), LocalTime.of(18, 0));
	static Class class3 = new Class(DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(20, 0));
	static Class class4 = new Class(DayOfWeek.THURSDAY, LocalTime.of(20, 0), LocalTime.of(22, 0));
	static Class class5 = new Class(DayOfWeek.FRIDAY, LocalTime.of(17, 0), LocalTime.of(20, 0));

	static List<Student> students = new ArrayList<Student>();

	public static void readFile(String file, Class classDay) {

		String line = null;

		try {
			FileReader fileReader = new FileReader("src/database/" + file);
			BufferedReader reader = new BufferedReader(fileReader);
			StringTokenizer st = null;

			String name;
			String surname;

			while ((line = reader.readLine()) != null) {

				st = new StringTokenizer(line, " ");
				String data = null;

				while (st.hasMoreTokens()) {

					data = st.nextToken();
					name = data;

					data = st.nextToken();
					surname = data.substring(0, data.length() - 1);

					createStudent(name, surname, classDay);
					
                    System.out.println("================================");
					System.out.println("Nome: " + name);
					System.out.println("Sobrenome: " + surname);
					System.out.println("================================");

				}
			}
			reader.close();
			fileReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createStudent(String name, String surname, Class selectedClass) {

		boolean exists = verifyIfExists(name, surname, selectedClass);

		if (!exists) {
			Student newStudent = new Student(name, surname);
			newStudent.getClasses().add(selectedClass);
			students.add(newStudent);
		} else {
			System.out.println("Usuário já cadastrado!");
		}

	}

	public static boolean verifyIfExists(String name, String surname, Class selectedClass) {
		boolean result = false;
		for (Student student : students) {
			if (student.getName().equals(name) && student.getSurname().equals(surname)) {
				student.getClasses().add(selectedClass);
				result = true;
			}
		}
		return result;
	}

	public static BigDecimal calculateFee(Student student, LocalDate start, LocalDate end, BigDecimal perHourValue,
			BigDecimal percentual) {

		double numberOfHours = 0;

		for (Class item : student.getClasses()) {

			DayOfWeek day = item.getDayOfWeek();

			while (start.isBefore(end)) {
				if (start.getDayOfWeek().equals(day)) {

					int hours = LocalTime.ofNanoOfDay(item.getDailyCourseHours()).getHour();
					int minutes = LocalTime.ofNanoOfDay(item.getDailyCourseHours()).getMinute();

					numberOfHours += Double.parseDouble(Integer.toString(hours) + "." + Integer.toString(minutes));
					start = start.plusDays(7);
				} else {
					start = start.plusDays(1);
				}
			}

		}

		if (percentual.doubleValue() != BigDecimal.ZERO.doubleValue()) {

			BigDecimal additional = perHourValue.multiply(percentual.divide(BigDecimal.valueOf(100)));
			perHourValue = perHourValue.add(additional);
		}

		BigDecimal parsedHours = BigDecimal.valueOf(numberOfHours);
		BigDecimal feeValue = perHourValue.multiply(parsedHours).setScale(2, BigDecimal.ROUND_HALF_EVEN);

		return feeValue;

	}

	public static void main(String[] args) {

		readFile("turma1.txt", class1);
		readFile("turma2.txt", class2);
		readFile("turma3.txt", class3);
		readFile("turma4.txt", class4);
		readFile("turma5.txt", class5);

		StudentComparator comparator = new StudentComparator();

		Collections.sort(students, comparator);

		students.forEach(student -> {

			BigDecimal januaryFee = calculateFee(student, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 31),
					BigDecimal.valueOf(29.99), BigDecimal.ZERO);

			BigDecimal februaryFee = calculateFee(student, LocalDate.of(2020, 2, 1), LocalDate.of(2020, 2, 28),
					BigDecimal.valueOf(29.99), BigDecimal.ZERO);

			BigDecimal marchFee = calculateFee(student, LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31),
					BigDecimal.valueOf(29.99), BigDecimal.valueOf(7.1837));

			System.out.println(

					"Aluno: " + student.getName() + " " + student.getSurname() + " - Horas de aulas por semana: "
							+ student.getCourseHoursPerWeek() + " - Mensalidade em Janeiro: R$ " + januaryFee
							+ " - Mensalidade em Fevereiro: R$ " + februaryFee + " - Mensalidade em Março: R$ " + marchFee+"\n"

			);
		}

		 );

	}

}
