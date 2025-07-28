package com.Airfare;

public class KingFisher implements Airfare{
	private int hours;
	private double costPerHour;
	
	

	public KingFisher(int hours, double costPerHour) {
		this.hours = hours;
		this.costPerHour = costPerHour;
	}
	
	



	public int getHours() {
		return hours;
	}





	public void setHours(int hours) {
		this.hours = hours;
	}





	public double getCostPerHour() {
		return costPerHour;
	}





	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}

	@Override
	public double calculateAmount(int costPerHour, int hoursOfTravel) {
		
		return costPerHour*hoursOfTravel*4;
		
	}

}
