package com.bcd.bdu.poc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class ReadFromJson {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path path = Paths.get("jsondata.txt");
		Scanner scanner = new Scanner(path);
		System.out.println("Read text file using Scanner");
		//read line by line
		 String line=null;
		 
		while(scanner.hasNextLine()){
		    //process each line
		     line = scanner.nextLine();
		  //  System.out.println(line);
		     
		}
	
		line=line.replace("\"[", "[").replace("]\"", "");
		//line=line.replaceAll("]\"", "");
		
		System.out.println(line);
		
		scanner.close();
	}

}
