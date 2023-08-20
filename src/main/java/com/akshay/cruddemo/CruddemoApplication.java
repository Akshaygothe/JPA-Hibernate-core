package com.akshay.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.akshay.cruddemo.dao.StudentDAO;
import com.akshay.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);
			createMultipalStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAll(studentDAO);
		};
	}

	private void deleteAll(StudentDAO studentDAO) {
		System.out.println("Deleting All Student..");
		int rowsDeleted = studentDAO.deleteAll();
		System.out.println("No. of rows deleted: " + rowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student of id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		
		Student myStudent = studentDAO.findById(studentId);
		System.out.println("student with id: " + studentId + " : " + myStudent);
		System.out.println("Updating students lastName..");
		myStudent.setFirstName("Madara");
		
		studentDAO.update(myStudent);
		
		System.out.println("Updated Student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Uchiha");
		
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findAll();
		
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Akshay", "Gothe", "akshay@gmail.com");
		
		System.out.println("Saving the Student...");
		studentDAO.save(tempStudent);
		
		int theId = tempStudent.getId();
		System.out.println("Saved student.. Generated Id: " + theId);
		
		System.out.println("Retriving the student by Id: " + theId);
		Student myStudent = studentDAO.findById(theId);
		
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipalStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new student object...");
		Student tempStudent1 = new Student("Kaven", "Tiwari", "kaven@gmail.com");
		Student tempStudent2 = new Student("Sasuke", "Uchiha", "sasuke@gmail.com");
		Student tempStudent3 = new Student("Kakashi", "Hatake", "kakashi@gmail.com");
		
		System.out.println("Saving the Student...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Akshay", "Gothe", "Akshay@gmail.com");
		
		System.out.println("Saving the Student...");
		studentDAO.save(tempStudent);
		
		System.out.println("Saved Student. Generated Id: " + tempStudent.getId());
	}

}
