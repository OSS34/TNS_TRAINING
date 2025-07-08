package com.Inheritance;

public class Student extends Person{
	private double Percentage;
	private String College;
	
	

	public Student() {
		super();
	}

	


	public Student(String name, long adhar, int age,double d, String college) {
		super(name, adhar, age);
		Percentage = d;
		College = college;

	}
	



	public double getPercentage() {
		return Percentage;
	}




	public void setPercentage(float percentage) {
		Percentage = percentage;
	}




	public String getCollege() {
		return College;
	}




	public void setCollege(String college) {
		College = college;
	}




	@Override
	public String toString() {
		return "Student [Percentage=" + Percentage + ", College=" + College + ", Name=" + getName()
				+ ", Adhar=" + getAdhar() + ", Age=" + getAge() + "]";
	}




	

}
