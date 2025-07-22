package com.Interface;

@FunctionalInterface
public interface GreetInterface {
	
	public String greet();
	
	default void m1() {
		System.out.println("Default method");
	}
	static void m2() {
		System.out.println("Static method");
	}

}
