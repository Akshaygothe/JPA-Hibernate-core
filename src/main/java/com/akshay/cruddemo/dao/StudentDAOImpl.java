package com.akshay.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akshay.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

	private EntityManager entityManage;
	
	@Autowired
	public StudentDAOImpl(EntityManager entityManage) {
		this.entityManage = entityManage;
	}

	@Override
	@Transactional
	public void save(Student theStudent) {
		entityManage.persist(theStudent);
	}

	@Override
	public Student findById(Integer id) {
		return entityManage.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
//		TypedQuery<Student> theQuery = entityManage.createQuery("from Student", Student.class);
		TypedQuery<Student> theQuery = entityManage.createQuery("from Student order by lastName", Student.class);
		return theQuery.getResultList();
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		TypedQuery<Student> theQuery = entityManage.createQuery(
				"from Student where lastName=: theData", Student.class);
		theQuery.setParameter("theData", lastName);
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public void update(Student theStudent) {
		entityManage.merge(theStudent);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Student theStudent = entityManage.find(Student.class, id);
		
		entityManage.remove(theStudent);
	}

	@Override
	@Transactional
	public int deleteAll() {
		int numRowsDeleted = entityManage.createQuery("DELETE FROM Student").executeUpdate();
		return numRowsDeleted;
	}

}
