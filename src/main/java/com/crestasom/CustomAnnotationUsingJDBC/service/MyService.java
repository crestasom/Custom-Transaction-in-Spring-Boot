package com.crestasom.CustomAnnotationUsingJDBC.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crestasom.CustomAnnotationUsingJDBC.Student;
import com.crestasom.CustomAnnotationUsingJDBC.annotation.MyTransactional;
import com.crestasom.CustomAnnotationUsingJDBC.db.DbAccess;

@Service
public class MyService implements MyServiceInterface {

	@Autowired
	DbAccess db;

	public List<Student> getAll() {
		List<Student> students = db.getResultList("select * from student");
		return students;
	}

	public void insertStudent(Student s) {
		db.executeUpdate("insert into student(name,rollno,semester) values(?,?,?)",
				Arrays.asList(s.getName(), s.getRollNo(), s.getSemester()));

	}

	public void updateStudent(Student s) {
		db.executeUpdate("update student set name=?,rollno=?,semester=? where id=",
				Arrays.asList(s.getName(), s.getRollNo(), s.getSemester(), s.getId() + ""));
	}

	@MyTransactional
	public void insertStudents() {
		insertStudent(new Student("mita", "5", "4th"));
		insertStudent(new Student("rita", "2", "4th"));
		insertStudent(new Student("laxman", "6", "4th"));
	}

	@MyTransactional
	public void insertStudentsExcp() {
		insertStudent(new Student("bharat", "9", "4th"));
		Student s = getAll().get(0);
		s.setName("ramayana");
		updateStudent(s);

	}
}
