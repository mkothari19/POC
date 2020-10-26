package mk.foreach;

import java.util.ArrayList;
import java.util.List;

public class ForEachDemo {
	
	public static void main(String[] args) {
		List<String> gamesList = new ArrayList<String>();  
        gamesList.add("Football");  
        gamesList.add("Cricket");  
        gamesList.add("Chess");  
        gamesList.add("Hocky");  
      //Iterate using functional interface
        gamesList.forEach(i->System.out.println(i));
        
      //Iterate using method reference
        gamesList.forEach(System.out::println);
        
        
        
	}

}
