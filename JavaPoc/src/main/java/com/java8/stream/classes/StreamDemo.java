package com.java8.stream.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.java8.entity.Product;

public class StreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<Product> productsList=new ArrayList<Product>();
productsList.add(new Product(1,"HP Laptop",25000f));  
productsList.add(new Product(2,"Dell Laptop",30000f));  
productsList.add(new Product(3,"Lenevo Laptop",28000f));  
productsList.add(new Product(4,"Sony Laptop",28000f));  
productsList.add(new Product(5,"Apple Laptop",90000f));  

List<Float>filterl=productsList.stream().filter(n->n.getPrice() >30000).map(n->n.getPrice()).collect(Collectors.toList());
	
System.out.println(filterl);
//This is more compact approach for filtering data  

productsList.stream().filter(p->p.getPrice()>30000).forEach(p->System.out.println(p.getName()));

//Use of reduce

float totalPrice=productsList.stream().map(p->p.getPrice()).reduce(0.0f,(sum,price)->sum+price);

System.out.println(totalPrice);

//More Precise code of reduce
float total=productsList.stream().map(p->p.getPrice()).reduce(0.0f,Float::min);

System.out.println(total);

//Summing using Collectors
	
double summing=productsList.stream().collect(Collectors.summingDouble(p->p.getPrice()));
System.out.println(summing);

//Max and Min

Product productA = productsList.stream()  
.max((product1, product2)->   
product1.getPrice() > product2.getPrice() ? 1: -1).get();  

System.out.println(productA.getPrice());

Product productB = productsList.stream()  
.max((product1, product2)->   
product1.getPrice() < product2.getPrice() ? 1: -1).get();  

System.out.println(productB.getPrice());

//Count example

long count=productsList.stream().filter(p->p.getPrice()<30000).count();

System.out.println(count);

//Convert List to Set

Set<Float> toset=productsList.stream().filter(p->p.getPrice()<30000).map(p->p.getPrice()).collect(Collectors.toSet());
	System.out.println(toset);
	//Method Reference in stream
	 List<Float> productPriceList =   
             productsList.stream()  
                         .filter(p -> p.getPrice() > 30000) // filtering data  
                         .map(Product::getPrice)         // fetching price by referring getPrice method  
                         .collect(Collectors.toList());  // collecting as list  
     System.out.println(productPriceList);  
	}

}
