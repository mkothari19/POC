package com.java8.interfaces;

@FunctionalInterface
public interface FunInNoParameter {
	
	void sayHi();
	
	default void hi() {
		
	}
	
default void hi(String n) {
		
	}

int hashCode();  
String toString();  
boolean equals(Object obj);  

}
