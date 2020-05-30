package com.crestasom.CustomAnnotationUsingJDBC;

public class Student {
	private int id;
	private String name;
	private String rollNo;
	private String semester;

	
	public Student( String name, String rollNo, String semester) {
		super();
		this.name = name;
		this.rollNo = rollNo;
		this.semester = semester;
	}
	
	public Student(int id, String name, String rollNo, String semester) {
		super();
		this.id = id;
		this.name = name;
		this.rollNo = rollNo;
		this.semester = semester;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollN0) {
		this.rollNo = rollN0;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", rollNo=" + rollNo + ", semester=" + semester + "]";
	}
	
	

}
