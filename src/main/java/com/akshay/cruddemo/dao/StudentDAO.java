package com.akshay.cruddemo.dao;

import java.util.List;

import com.akshay.cruddemo.entity.Student;

public interface StudentDAO {

	void save(Student theStudent);
	
	Student findById(Integer id);
	
	List<Student> findAll();
	
	List<Student> findByLastName(String lastName);
	
	void update(Student theStudent);
	
	void delete(Integer id);
	
	int deleteAll();
}
