package mk.foreach;

import java.util.ArrayList;
import java.util.List;

public class ForeachOrdered {
	public static void main(String[] args) {
		List<String> gamesList = new ArrayList<String>();  
        gamesList.add("Football");  
        gamesList.add("Cricket");  
        gamesList.add("Chess");  
        gamesList.add("Hocky");  
      
        // Iterate using lamda
        System.out.println("------------Iterating by passing lambda expression---------------");  
        gamesList.stream().forEachOrdered(i->System.out.println(i));
        //Iterate using method reference
        System.out.println("------------Iterating by passing method reference---------------");  
        gamesList.stream().forEachOrdered(System.out::println);
        
	}

}
