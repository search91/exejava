package com.java.learn.throwerror;



public class ThrowErrorClass {
	
	public int calculate(int divNumber){
		if (divNumber == 0){
		  throw new IllegalArgumentException("0!!!!");
		}
		return 100/divNumber;
		
	}
	

	
	public static void main(String args[]){
		try {
			ThrowErrorClass instance = new ThrowErrorClass();
			int n = instance.calculate(0);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}	
	}

}
