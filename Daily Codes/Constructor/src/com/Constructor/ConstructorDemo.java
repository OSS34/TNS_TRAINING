package com.Constructor;
import java.util.Scanner;
public class ConstructorDemo {
	private int rollnum;
	private String name;
	
	public ConstructorDemo(){
		System.out.println("Default Constructor\n");
	}
	
	public ConstructorDemo(int rollnum, String name) {
		this.rollnum=rollnum;
		this.name=name;
		
	}
	
	
	@Override
	public String toString() {
		return "Value [rollnum=" + rollnum + ", name=" + name + "]";
	}

	public static void main(String[] args) {
		
		int rollnm;
		String name;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter roll number : ");
		rollnm=sc.nextInt();
		System.out.println("Enter Name : ");
		name = sc.next();
		ConstructorDemo obj1= new ConstructorDemo();

		ConstructorDemo obj= new ConstructorDemo(rollnm,name);
		System.out.println(obj);
		
		

	}

}
