package com.Encapsulation;

public class EncapsulationDemo {

		private int serialNum,age;
		private String name;
		
		
		
		// Getter and Setter
		
		public void setSerialNum(int serialNum) {
			this.serialNum=serialNum;
			
		}
		public void setAge(int age) {
			this.age=age;
			
		}
		public void setName(String name) {
			this.name=name;
			
		}
		
		
		
		public int getSerialNum() {
			 return this.serialNum;
			
		}
		public int getAge() {
			return this.age;
			
		}
		public String getName() {
			return this.name;
			
		}
		
		//toString
		@Override
		public String toString() {
			return "EncapsulationDemo [serialNum=" + serialNum + ", age=" + age + ", name=" + name + "]";
		}

}
