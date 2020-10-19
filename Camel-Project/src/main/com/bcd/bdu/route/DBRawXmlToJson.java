package com.bcd.bdu.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.json.XML;

public class DBRawXmlToJson extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		 from("file://[(api.complete.directbooking.rawgro)]?idempotent=true&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
			
		//from("file://[(api.endpoint.pnr.landing.dir)]?noop=true")
			.routeId("DBRawXmlToJson")
			.process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					   
					  String body=exchange.getIn().getBody(String.class);
				       
				       JSONObject obj = XML.toJSONObject(body,true);
				       JSONObject pnr=obj.getJSONObject("PNR");
				       exchange.getIn().setBody(pnr.toString());
				}
				 
			 })
			.to("file://[(api.complete.directbooking.jsongro)]?fileName=${header.CamelFileName}.json");
			
	}

}
