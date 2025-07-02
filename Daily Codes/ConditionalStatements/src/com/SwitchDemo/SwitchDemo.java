package com.SwitchDemo;
import java.util.*;

public class SwitchDemo {

	public static void main(String[] args) {
		String ch;
		Scanner sc= new Scanner(System.in);
		while(true) {
		System.out.println("Enter Operation :");
		//ch =sc.nextLine().charAt(0);
		ch =sc.nextLine();
		switch(ch) {
			case "+" :
			case "P":
				System.out.println("10+20 : "+(10+20));
				break;
			case "-":
			case "M":
				System.out.println("10-20 : "+(10-20));
				break;
			case "*":
			case "Mu":
				System.out.println("10*20 : "+(10*20));
				break;
			case "/":
				System.out.println("10/20 : "+(10/20));
				break;
			default:
				System.out.println("Enter Correct operation !!");
				break;
		}
		
		}
		
		
	}
	

}
