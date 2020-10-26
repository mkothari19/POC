package com.java8.supplier.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class SupplierDemo {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Supplier<LocalDateTime> sup1=()->LocalDateTime.now();
		LocalDateTime time=sup1.get();
		
		System.out.println(time);
		
		Supplier<String> sup2=()->dtf.format(LocalDateTime.now());
		String timestr=sup2.get();
		
		System.out.println(timestr);
		
	}

}
