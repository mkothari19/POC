package mk.optional;

import java.util.Optional;

/*
 * Final class introduce in Java8 to handle NullPointerException
 */
public class OptionalDemo {
	
	public static void main(String[] args) {
		
		String[] str=new String[10];
		
		/* 
		 * Causes of NullPointerException
		 * System.out.println(str[0].toLowerCase()); 
		 * 
		 * 
		 * */
		Optional<String> checknull=Optional.ofNullable(str[0]);
		if(checknull.isPresent()) {
			System.out.println("Contant is available");
		}else {
			System.out.println("Contant is not available");
		}
		
		
	}
	

}
