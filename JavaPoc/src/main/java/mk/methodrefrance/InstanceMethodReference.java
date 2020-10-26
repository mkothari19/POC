package mk.methodrefrance;

import mk.lamda.GeneralFunction;

public class InstanceMethodReference {
	
	public int add(int a,int b) {
		return a+b;
	}

	public static void main(String[] args) {
		GeneralFunction reference=new InstanceMethodReference()::add;
		System.out.println(reference.apply(20, 30));
		
	}
}
