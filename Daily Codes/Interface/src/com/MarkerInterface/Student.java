package com.MarkerInterface;

public class Student implements Registrable{
	private int id;
	private String name,course;
	private double fees;
	
	public Student() {
		super();
	}

	public Student(int id, String name, String course, double fees) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.fees = fees;
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
		return "Student \nId=" + id + ", Name=" + name + ", Course=" + course + ", Fees=" + fees ;
	}
	
	
	
	

}
