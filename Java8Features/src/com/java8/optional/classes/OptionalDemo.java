package com.java8.optional.classes;

import java.util.Optional;

/*
 * It is a public final class and used to deal with NullPointerException in Java application
 */
public class OptionalDemo {
	
	public static void main(String[] args) {
		String[] str=new String[10];
	    /*String lownerLowercase=str[5].toLowerCase();
	    
	    System.out.println(lownerLowercase);*/
		
		Optional<String>checknull=Optional.ofNullable(str[5]);	
		
		if(checknull.isPresent()) {
			System.out.println(str[0]);
		}else {
			System.out.println("Is empty");
		}
		
	/*
	 * Cosumer,Supplier and Predicates
	 */
		
	}

}
