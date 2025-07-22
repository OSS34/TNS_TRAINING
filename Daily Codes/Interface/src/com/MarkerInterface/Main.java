package com.MarkerInterface;

public class Main {

	public static void main(String[] args) {

		Student s1 = new Student(1,"Om","IT",544644.30);
		
		Teacher t1 = new Teacher(2,"Prof.Rakesh","OOP");
		
		if(s1 instanceof Registrable) {
			System.out.println("Student is registrable\n"+s1);
			
		}else {
			System.out.println("Student is not registrable");
		}
		
		if(t1 instanceof Registrable) {
			System.out.println(t1);
			
		}else {
			System.out.println("Teacher is not registrable");
		}
	}

}
