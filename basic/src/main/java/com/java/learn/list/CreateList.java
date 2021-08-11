package com.java.learn.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class CreateList {
	
	public static ArrayList<String> createArrayList(String ... elements) {
		  ArrayList<String> list = new ArrayList<String>(); 
		  for (String element : elements) {
		    list.add(element);
		  }
		  return list;
		}
	
	public static void main(String[] args) {
		// 1.add 方法
		List<String> list1 = new ArrayList<String>();
	    list1.add("A"); // 每次扩容*1.5
	    list1.add("B");
	    list1.add("C");
	    list1.set(2, "D");
	    list1.add("D");
	    
	    // 2. 匿名类
	    List<String> list2 = new ArrayList<String>() {{
	        add("A");
	        add("B");
	        add("C");
	    }};
	    list2.set(2, "D");
	    list2.add("D");
	    
	    // 3. Arrays 固定大小，内容可变
	    List<String> list3 = Arrays.asList("A", "B", "C");
	    // list3.add("D"); 抛出java.lang.UnsupportedOperationException
	    list3.set(2, "D");
	    
	    // 4. Arrays
	    List<String> list4 = new ArrayList<String>(Arrays.asList("A", "B", "C"));
	    list4.set(2, "D");
	    list4.add("D");
	    
	    // 5. google.common.collect.ImmutableList 不可变集合 
	    List<String> list5 = ImmutableList.of("A", "B", "C");
	    // list5.add("D"); 抛出java.lang.UnsupportedOperationException
	    // list5.set(2, "D"); 抛出java.lang.UnsupportedOperationException
	    
	    // 6. 工厂方法
	    List<String> list6 = createArrayList("A", "B", "C");
	    list6.set(2, "D");
	    list6.add("D");
	    
	    System.out.println(list1);
	    System.out.println(list2);
	    System.out.println(list3);
	    System.out.println(list4);
	    System.out.println(list5);
	    System.out.println(list6);

	}

}
