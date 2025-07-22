package com.Interface;

public class Main {

	public static void main(String[] args) {
		
		Greet g = new Greet();
		System.out.println(g.greet());
		
		GreetInterface g1 = ()->{
			return "Lambda Expression";
		};
		System.out.println(g1.greet());

	}

}
