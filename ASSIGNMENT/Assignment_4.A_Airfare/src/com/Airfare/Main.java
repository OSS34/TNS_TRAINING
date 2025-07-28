package com.Airfare;
import java.util.*;
import java.text.*;
public class Main {

	public static void main(String[] args) {
		int choice,hours,costPerHour;
		double finalAmount = 0;
		DecimalFormat df = new DecimalFormat("#.00");
		Scanner sc = new Scanner(System.in);
		
		choice = sc.nextInt();
		hours = sc.nextInt();
		costPerHour = sc.nextInt();
		
		switch(choice) {
		case 1:
			AirIndia a= new AirIndia(hours,costPerHour);
			finalAmount = a.calculateAmount(costPerHour, hours);
			break;
		case 2:
			KingFisher k= new KingFisher(hours,costPerHour);
			finalAmount = k.calculateAmount(costPerHour, hours);
			break;
		case 3:
			Indigo i= new Indigo(hours,costPerHour);
			finalAmount = i.calculateAmount(costPerHour, hours);
			break;
		default :
			System.out.println("Invalid Choice !!!!");
			break;
		}
		System.out.println(df.format(finalAmount));

	}

}
