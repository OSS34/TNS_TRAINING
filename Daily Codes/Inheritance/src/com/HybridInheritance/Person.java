package com.HybridInheritance;

public class Person {
	private long AdharNm;
	private long Mobile;
	private String Name;
	
	public Person(long adharNm, long mobile, String name) {
		
		AdharNm = adharNm;
		Mobile = mobile;
		Name = name;
	}

	@Override
	public String toString() {
		return "AdharNm=" + AdharNm + ", Mobile=" + Mobile + ", Name=" + Name ;
	}
	
	
	
	
	
	

}
