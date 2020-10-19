package com.bcd.bdu.poc;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.bcd.bdu.util.BduUtils;

public class Test {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		/*Map<String,String>gds=new HashMap<String,String>();
		gds.put("4", "Amadeus");
		gds.put("1A", "Amadeus");
		gds.put("5", "Galileo");
		gds.put("1G", "Galileo");
		gds.put("2", "Apollo");
		gds.put("1V", "Apollo");
		gds.put("1", "Sabre");
		gds.put("1S", "Sabre");
		gds.put("3", "Worldspan");
		gds.put("1P", "Worldspan");
		gds.put("6", "Abacus");
		gds.put("1B", "Abacus");
	    
	   System.out.println(gds.get("1A"));*/
		
	/*	String uid=BDUPnrIDGeneration.getInstance().createUUIDForPnr_ID("YXAKDS", "2019-09-30 07:50:00", "Sabre");
		System.out.println("pnr_id "+ uid );
		
		 String pnrid=BDUPnrIDGeneration.getInstance().create64BitPnrID("YXAKDS", "2019-09-30 07:50:00", "Sabre");
		 
		 System.out.println("pnrid "+ pnrid );
*/	
		DateTimeFormatter pastdateFormater = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		SimpleDateFormat formatterold = new SimpleDateFormat("yyyyMMddHHmmss");
	
		formatterold.setTimeZone(TimeZone.getTimeZone("UTC"));
		String currentd = formatterold.format(new Date());
		String enddate="20200413100300";
		LocalDateTime aLDT = null;
		ZonedDateTime zdt = null;
		aLDT = LocalDateTime.parse(currentd.trim(), pastdateFormater);
		zdt = aLDT.atZone(ZoneId.of("UTC"));
		ZonedDateTime minPrev = zdt.plusMonths(3);
		currentd = pastdateFormater.format(minPrev);
		System.out.println(currentd);

	}
}
