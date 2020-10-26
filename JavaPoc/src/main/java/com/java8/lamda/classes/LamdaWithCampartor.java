package com.java8.lamda.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.java8.entity.Product;

public class LamdaWithCampartor {
	
	public static void main(String[] args) {
		//Adding Products  
		List<Product> list=new ArrayList<Product>();
        list.add(new Product(1,"HP Laptop",25000f));  
        list.add(new Product(3,"Keyboard",300f));  
        list.add(new Product(2,"Dell Mouse",150f));  
        
        Collections.sort(list,(p1,p2)->{  
            return p1.getName().compareTo(p2.getName());  
            });  
        
        list.forEach((n)->{System.out.println(n);});
        
	}

}
