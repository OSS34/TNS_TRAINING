package com.NestedIf;

public class NestedIf {

	public static void main(String[] args) {
		
		int age =20;
		boolean hasVId=true;
		if(age>=18) {
			if(hasVId) {
				System.out.println("You can vote");
			}
			else {
				System.out.println("Create voter id");
			}
		}
		else {
			System.out.println("Not eligible for voting");
		}

	}

}
