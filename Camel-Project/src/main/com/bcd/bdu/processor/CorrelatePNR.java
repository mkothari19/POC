package com.bcd.bdu.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.bcd.bdu.model.bookingdata.db.PNR;



public class CorrelatePNR implements Processor {
	public void process(Exchange exchange) throws Exception {
		String rowkey =  (String) exchange.getIn().getHeader("rowkey");
		String orignalpnrid =  (String) exchange.getIn().getHeader("orignalpnrid");
		PNR pnr = (PNR) exchange.getIn().getBody();
		pnr.getIdentification().setPnrId(rowkey);
		pnr.getIdentification().setPNRID(orignalpnrid);
		
	}
}
