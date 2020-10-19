package com.bcd.bdu.poc;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TFPOC {
	public static String getData(String path) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
		Object obj = new JSONParser().parse(new FileReader(path));
		JSONObject jo = (JSONObject) obj;
		//System.out.println(jo);

		Map<String, Object> segments = (Map<String, Object>) jo.get("Segments");
		//System.out.println("Segments  "+segments);
		JSONArray segment = (JSONArray) segments.get("Segment");
		 TreeSet<Date> al=new TreeSet<Date>(); 
		for(int i=0;i<segment.size();i++) {
			Map<String, String> seg = (Map<String, String>) segment.get(i);
			if(seg.containsKey("segType")&&seg.get("segType").equals("Air")) {
				
				try {
					al.add(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(seg.get("startDateTime")));
				} catch (java.text.ParseException e) {
				
					e.printStackTrace();
				}
			
			}
		
		}
		for(int i=0;i<segment.size();i++) {
			Map<String, String> seg = (Map<String, String>) segment.get(i);
			if(seg.containsKey("segType")&&seg.get("segType").equals("Air")) {
			
				
				seg.put("segmentNumber", Integer.toString(al.headSet(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(seg.get("startDateTime"))).size()+1));
			}
			
		}
		
		Map<String, Object> travelers = (Map<String, Object>) jo.get("Travelers");
		Map<String, Object> traveler = (Map<String, Object>) travelers.get("Traveler");
		Map<String, Object> seats = (Map<String, Object>) traveler.get("Seats");
		JSONArray seat = (JSONArray) seats.get("Seat");
		for(int i=0;i<seat.size();i++) {
			Map<String, String> seg = (Map<String, String>) seat.get(i);
			
			
				
				seg.put("segmentNumber", Integer.toString(al.headSet(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(seg.get("startDateTime"))).size()+1));
			
			
		}
		System.out.println(seat);
		BufferedWriter writer = new BufferedWriter(new FileWriter("\\\\DTCNA05SVMNAS.nasa.bcdtravel.local\\HV2C_Redirect\\AKhan4\\Desktop\\tf\\mydata.txt"));
		writer.write(jo.toString());
		writer.close();
			//System.out.println("Seg  "+segment);
			
		return "";// .replaceAll("[^\\p{ASCII}]", "");
	}

	public static void main(String[] args) throws Exception {
		/*  TreeSet<Date> al=new TreeSet<Date>();  
		al.add(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/11/2019 08:55"));
		al.add(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/11/2018 17:40"));
		
		
		for(Date data:al)
		{
			System.out.println(al.headSet(data).size());
		}
		System.out.println(al);*/
		
		
		String datapath = "\\\\DTCNA05SVMNAS.nasa.bcdtravel.local\\HV2C_Redirect\\AKhan4\\Desktop\\tf\\";
		new TFPOC().getData(datapath + "test.json");

	}
	
}