package com.bcd.bdu.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.simple.parser.JSONParser;
import org.json.JSONObject;

public class RichDataEnrichmentProcessor implements Processor {
	
	public ConcurrentHashMap<String,String> oldata=null;
	public RichDataEnrichmentProcessor(ConcurrentHashMap<String,String> oldata) {
		this.oldata=oldata;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		 final SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		    f1.setTimeZone(TimeZone.getTimeZone("UTC"));
		   String timestamp=f1.format(new Date());
		String actualjson=oldata.get(exchange.getIn().getHeader("dl_db_pnr",String.class));
	    JSONObject actual = new JSONObject(actualjson);
			
		String richdata=exchange.getIn().getBody(String.class);
		 Object object=null;
		 object = new JSONParser().parse(richdata);
		 org.json.simple.JSONObject richdatajson = (org.json.simple.JSONObject) object;
		actual.put("status", richdatajson.get("status_type"));
		System.out.println(actual.get("instance"));
		String filename="directbooking-"+timestamp+actual.get("recordLocator");
		  exchange.getIn().setHeader("DBFileName",filename);
		
		/*System.out.println("actualjson " +actual);
		System.out.println("richdata " +richdata);*/
		oldata.remove(oldata.get(exchange.getIn().getHeader("dl_db_pnr",String.class)));
		exchange.getIn().setBody(actual, String.class);
		
	}

}

