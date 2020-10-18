package com.java8.methodref.classes;

import com.java8.interfaces.FunInNoParameter;

public class StaticMethodRef {
	
	public static void sayHello() {
		
		System.out.println("Hello Staic Method");
	}

	public static void main(String[] args) {
		
		FunInNoParameter obj=StaticMethodRef::sayHello;
		
		obj.sayHi();
	}
}
