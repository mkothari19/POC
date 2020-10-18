package com.java8.lamda.classes;

import com.java8.interfaces.FunInNoParameter;
import com.java8.interfaces.FunInWithMulParam;
import com.java8.interfaces.FunInWithParameter;

public class NoParamClass {
	
	public static void main(String[] args) {
		
		FunInNoParameter obj=()->{System.out.println("Hello");
	};
	obj.sayHi();
	
	FunInWithParameter obj1= (name)-> {return "Hi "+name;};
	
	System.out.println(obj1.sayHello("Manish"));
	
	// You can omit function parentheses  
	FunInWithParameter obj2= name-> {return "Hi "+name;};
	
	System.out.println(obj2.sayHello("Kothari"));
	
	//Multiple param with out param
	FunInWithMulParam obj3=(a,b)-> a+b;
	
	System.out.println(obj3.add(100, 200));
	
	//Multiple param with return
	
	FunInWithMulParam obj4=(a,b)-> { return a+b;};
	System.out.println(obj4.add(200, 300));
	
	
	}
}
