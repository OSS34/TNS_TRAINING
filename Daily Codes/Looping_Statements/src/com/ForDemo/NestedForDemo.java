package com.ForDemo;

public class NestedForDemo {

	public static void main(String[] args) {
		
		for(int i=1;i<=10;i++) {
			int val=0;
			for(int j=1;j<=10;j++) {
				if(val>9) {
					System.out.print((val+=i)+ "  ");
				}
				else {
					System.out.print((val+=i)+ "   ");
				}
			}
			System.out.println();
		}

	}

}
