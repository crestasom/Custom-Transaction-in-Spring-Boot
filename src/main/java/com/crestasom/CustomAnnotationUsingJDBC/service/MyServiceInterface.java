package com.crestasom.CustomAnnotationUsingJDBC.service;

import java.util.List;

import com.crestasom.CustomAnnotationUsingJDBC.Student;

public interface MyServiceInterface {
	List<Student> getAll();

	void insertStudent(Student s);

	void updateStudent(Student s);

}
