package com.java8.methodref.classes;

import java.util.function.BiFunction;

public class MethodRefrence {
	
	public static void main(String[] args) {
		
		BiFunction<Integer,Integer, Integer> obj1=AirthMetic::add;
		
         int result1=  obj1.apply(200, 300);
         
         System.out.println(result1);
	}

}
