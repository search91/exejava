package com.java.learn.statictest;



public class StaticClass {
	static int staticVal = 1;

	public static void testStatic(){
		staticVal = staticVal + 100;
	}
	
	public static void main(String args[]){
		testStatic();
		staticVal ++;
		System.out.println(staticVal);
		
		System.out.println(Math.ceil(996*1.0/20));
		
		try {
			System.out.println(Integer.parseInt(""));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
		
   
		
	}

}
