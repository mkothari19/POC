package com.java8.methodref.classes;

import com.java8.interfaces.FunInNoParameter;

public class ConstructorRef {
	
	public ConstructorRef() {
		System.out.println("Constructor Reference throw lamda");
	}

	public static void main(String[] args) {
		
		FunInNoParameter obj=ConstructorRef::new;
		
		obj.sayHi();
		
	}
	
}
