package com.bcd.bdu.poc;


import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class TimeZonePoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        f.setTimeZone(TimeZone.getTimeZone("UTC"));
	        String subject="'BDU "+ "smc"+" Data Pipeline New elements processed'";
			String emailcmd="echo "+ "'"+"Hello"+"'"+" | "+"mail -s " +subject+" "+"manish";
	        System.out.println(emailcmd);
	}

}