package com.bcd.bdu.processor;



import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.bcd.bdu.helpers.SecurityHandler;
import com.bcd.bdu.model.bookingdata.db.PNR;
import com.bcd.bdu.model.bookingdata.db.PNR.Identification;



public class KeyGenerator implements Processor {

	public void process(Exchange exchange) throws Exception {
		PNR pnr = (PNR)exchange.getIn().getBody();
		Identification ident = pnr.getIdentification();
		
		String resourceL=ident.getRecordLocator();

		String instance = ident.getInstance();
		long srcPnrId = ident.getSourcePNRId();
		String pnrid=ident.getPnrId();
	 	
		String str_key = instance + "|" + String.valueOf(srcPnrId);

    	final long hash_string = SecurityHandler.hashFNV1a(str_key.getBytes());
    	String rowkey = Long.toUnsignedString(hash_string);

    	// Get Instant Least Significant Bit then Add to hash value to make sortable
    	
    	/*String qualifier = Long.toUnsignedString(hash_string + ((int)SecurityHandler.utcAtomicTimestamp()));
    	  DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
    	  String timestamp = format.format(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));*/
    	/*
    	 * Comment above line code because direct booking file name change requested.
    	 * Format:  YYYY-MM-DDHH-MM-SSZ-XXXXXX.xml
			where	YYYY-MM-DD is the date
			HH-MM-SS is the time
			Z is a constant
			XXXXXX is the record locator.

    	 */
		/* exchange.getIn().setBody(obj.toString().getBytes());*/
    	String qualifier=SecurityHandler.fileNaming(resourceL);
    	exchange.getIn().setHeader("rowkey",rowkey);
    	exchange.getIn().setHeader("orignalpnrid",pnrid);
    	exchange.getIn().setHeader("qualifier",qualifier);
    	
	}

}
