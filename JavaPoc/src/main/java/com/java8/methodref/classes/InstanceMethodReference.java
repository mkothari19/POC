package com.java8.methodref.classes;

import java.util.function.BiFunction;

import com.java8.interfaces.FunInNoParameter;

public class InstanceMethodReference {

	public void sayInstanceMethod() {
		System.out.println("Hello Instance Method Reference");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //Create Object Of InstanceMethodReference
		
		InstanceMethodReference obj1=new InstanceMethodReference();
		
		FunInNoParameter ins=obj1::sayInstanceMethod;
		
		ins.sayHi();
		
		BiFunction<Float, Float,Float> ins1=new AirthMetic()::add;
		float f=ins1.apply(100f, 200f);
		
		System.out.println(f);
	}

}
