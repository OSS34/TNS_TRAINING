package com.Inheritance;

public class Person {
	private String name;
	private long  adhar;
	private int  age;
	
	public Person() {
		System.out.println("Cititizen Object Created");
		
	}

	public Person(String name, long adhar, int age) {
		this.name = name;
		this.adhar = adhar;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAdhar() {
		return adhar;
	}

	public void setAdhar(long adhar) {
		this.adhar = adhar;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", adhar=" + adhar + ", age=" + age + "]";
	}
	
	
	
	
	
	

}
