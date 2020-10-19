package com.bcd.bdu.helpers;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.UUID;

/*
 *Class used to create 64bit as Int  and UUID 
 */
public class BDUPnrIDGeneration {
	
	private static BDUPnrIDGeneration instance;
	
 private BDUPnrIDGeneration() {
	 
 }
 
 public static BDUPnrIDGeneration getInstance() {
     if (instance == null) {
         instance = new BDUPnrIDGeneration();
     } 

     return instance;
 }
 
 /*
  * Logic to generate 64 Bit Pnrid
  * System calculating 64Bits of "sourcePnrId"+"instance"+"bookingdate"
  * 
  */
 public String create64BitPnrID(String recordL,String bookingdate,String vop) throws ParseException, UnsupportedEncodingException {
	 		String str_key = bookingdate+vop+recordL;
	     	byte[] bytes = str_key.getBytes("UTF-8");
	     	UUID uuid = UUID.nameUUIDFromBytes(bytes);
	     	 final long hash_string =uuid.getMostSignificantBits(); // SecurityHandler.hashFNV1a(uuid.toString().getBytes());
			String rowkey = Long.toUnsignedString(hash_string);
	  
	 return rowkey;
 }
 
 /*
  * Logic to generate UUID
  * 
  * 
  */
 
 public String createUUIDForPnr_ID(String recordL,String bookingdate,String vop) throws ParseException, UnsupportedEncodingException {
	String str_key =  bookingdate+vop+recordL;
  	byte[] bytes = str_key.getBytes("UTF-8");
  	UUID uuid = UUID.nameUUIDFromBytes(bytes);
  	
return  uuid.toString();
}
 
}
