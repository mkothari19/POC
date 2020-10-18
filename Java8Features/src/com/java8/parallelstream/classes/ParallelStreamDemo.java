package com.java8.parallelstream.classes;

import java.util.ArrayList;
import java.util.List;

import com.java8.entity.Product;

public class ParallelStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long t1,t2;
		
		List<Product> productsList=new ArrayList<Product>();
		productsList.add(new Product(1,"HP Laptop",25000f));  
		productsList.add(new Product(2,"Dell Laptop",30000f));  
		productsList.add(new Product(3,"Lenevo Laptop",28000f));  
		productsList.add(new Product(4,"Sony Laptop",28000f));  
		productsList.add(new Product(5,"Apple Laptop",90000f)); 
		
		t1=System.currentTimeMillis();
		
		long count=productsList.stream().filter(p->p.getPrice()>15000).count();
		t2=System.currentTimeMillis();
		System.out.println(count);
		
		System.out.println("Total Time elasped::: "+(t2-t1));
		t1=System.currentTimeMillis();
		long count1=productsList.parallelStream().filter(p->p.getPrice()>15000).count();
		t2=System.currentTimeMillis();
		System.out.println(count1);
		System.out.println("Total Time elasped::: "+(t2-t1));
		
	}

}
