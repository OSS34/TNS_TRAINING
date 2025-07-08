package com.MultilevelInheritance;

public class ElectricCar extends Car{
	
	private double BatteryCapacity;

	public ElectricCar(int price, String brand, String Model, boolean hasTrunk,double BatteryCapacity) {
		super(price, brand, Model, hasTrunk);
		this.BatteryCapacity=BatteryCapacity;
	}

	@Override
	public String toString() {
		return "ElectricCar [BatteryCapacity=" + BatteryCapacity + ", Model=" + getModel() + ", Trunk="
				+ isHasTrunk() + ", Price=" + getPrice() + ", Brand=" + getBrand() + "]";
	}
	
	
	
	
	
	

}
