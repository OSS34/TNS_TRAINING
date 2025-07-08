package com.MultilevelInheritance;

public class Car extends Vehicle{

	private String Model;
	private boolean hasTrunk;
	
	public Car(int price, String brand,String Model,boolean hasTrunk) {
		super(price, brand);
		this.Model=Model;
		this.hasTrunk=hasTrunk;
		
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public boolean isHasTrunk() {
		return hasTrunk;
	}

	public void setHasTrunk(boolean hasTrunk) {
		this.hasTrunk = hasTrunk;
	}
	
	
	
	
	
	
}
