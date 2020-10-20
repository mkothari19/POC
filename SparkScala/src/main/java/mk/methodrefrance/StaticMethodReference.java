package mk.methodrefrance;

import java.util.function.BiFunction;

import mk.lamda.Bank;
import mk.lamda.GeneralFunction;

public class StaticMethodReference {
	
	public static int  add(int a,int b) {
		return a+b;
	}
	
	public static void display(Double rate) {
		System.out.println("INTREST RATE "+rate);
	}
	
	
	public static void main(String[] args) {
	
	BiFunction<Integer,Integer,Integer> reference=	StaticMethodReference::add;
	  int result=reference.apply(10, 20);
	  System.out.println(result);
	  
	
	  GeneralFunction greference= StaticMethodReference::add;
	 System.out.println(greference.apply(10, 20));
	  
	Bank bankrefrence=StaticMethodReference::display;
	bankrefrence.intrestRate(7.5);
	
		
	}

}
