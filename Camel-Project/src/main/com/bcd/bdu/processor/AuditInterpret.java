package com.bcd.bdu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;



/*
 * Custom class to interact with logstash server
 * To graph rate of success and error routed to deadletter
 *
 */
public class AuditInterpret implements Processor {
	public void process(Exchange exchange) throws Exception {
		//PNR pnr = (PNR) exchange.getIn().getBody();
		//String str  = ((String)exchange.getIn().getHeader("pnrid") != null) 
		//				? (String) exchange.getIn().getHeader("pnrid") : new String("");
		
	}
}
