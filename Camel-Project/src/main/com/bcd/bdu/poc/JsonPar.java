package com.bcd.bdu.poc;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
;

public class JsonPar {
	
	public static void main(String[] args) throws JSONException, ParseException {
		String s = "{\"TicketInfo\":{\"carrier\":\"LH\",\"isETicket\":\"\",\"TotalTax\":\"\",\"invoiceNumber\":\"\",\"BaseFare\":{\"amount\":\"48700\",\"currency\":\"EUR\"},\"ticketNum\":\"22038962793610\",\"FareTotal\":{\"amount\":\"67012\",\"currency\":\"EUR\"},\"CommissionTotal\":{\"amount\":\"\",\"currency\":\"\"}}}";
	    JSONObject json = new JSONObject();
	    json.put("DeliveryAddressInfo",new JSONParser().parse(s));
	   
	    System.out.println(json);
	}

}
