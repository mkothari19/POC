package com.bcd.bdu.poc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;

public class XPathDemo {

  public static void main(String[] args) 
  {
	
        URL url1 = XPathDemo.class.getResource("directbooking-1d7b4a9b-1408-44ed-9406-97a8a18c41cd.xml");
        URL url2 = XPathDemo.class.getResource("directbooking-2f2d9397-b53d-41b3-9dd3-9720a4e8a01a.xml");
        FileReader fr1 = null;
        FileReader fr2 = null;
        try {
            fr1 = new FileReader("directbooking-1d7b4a9b-1408-44ed-9406-97a8a18c41cd.xml");
            fr2 = new FileReader("directbooking-2f2d9397-b53d-41b3-9dd3-9720a4e8a01a.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        try {
            Diff diff = new Diff(fr1, fr2);
            System.out.println("Similar? " + diff.similar());
            System.out.println("Identical? " + diff.identical());
           
 
            DetailedDiff detDiff = new DetailedDiff(diff);
           
            List differences = detDiff.getAllDifferences();
            for (Object object : differences) {
                Difference difference = (Difference)object;
                System.out.println("***********************");
                System.out.println(difference);
                System.out.println("***********************");
            }
 
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
 
}

}