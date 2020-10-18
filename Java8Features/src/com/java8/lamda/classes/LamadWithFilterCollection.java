package com.java8.lamda.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.java8.entity.Product;

public class LamadWithFilterCollection {
	
	public static void main(String[] args) {
		 List<Product> list=new ArrayList<Product>();  
	        list.add(new Product(1,"Samsung A5",17000f));  
	        list.add(new Product(3,"Iphone 6S",65000f));  
	        list.add(new Product(2,"Sony Xperia",25000f));  
	        list.add(new Product(4,"Nokia Lumia",15000f));  
	        list.add(new Product(5,"Redmi4 ",26000f));  
	        list.add(new Product(6,"Lenevo Vibe",19000f)); 
	        
	        Stream<Product>product_stream=list.stream().filter(p->p.getPrice()>20000f);
	        product_stream.forEach(p->System.out.println(p));
	        
	}

}
