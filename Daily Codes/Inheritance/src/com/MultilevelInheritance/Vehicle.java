package com.MultilevelInheritance;

public class Vehicle {
	
	private int Price;
	private String Brand;
	
	public Vehicle( int price, String brand) {
		
		
		Price = price;
		Brand = brand;
	}


	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}
	
	
	

}
