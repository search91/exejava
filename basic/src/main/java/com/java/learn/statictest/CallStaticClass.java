package com.java.learn.statictest;



public class CallStaticClass {

	public static void main(String args[]){
		int staticVal =  StaticClass.staticVal;
		staticVal ++;
		StaticClass.testStatic();
		System.out.println(staticVal);
		
		
		CountClass countClass = new CountClass();
		countClass.addStaticCount();
		countClass.addCount();
		
		CountClass countClass2 = new CountClass();
		countClass2.addStaticCount();
		countClass2.addCount();
		
		System.out.println(countClass.getCount());
		System.out.println(countClass.getStaticCount());
		
		System.out.println(countClass2.getCount());
		System.out.println(countClass2.getStaticCount());
		
	}

}
