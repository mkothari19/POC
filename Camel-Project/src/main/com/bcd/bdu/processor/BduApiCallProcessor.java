package com.bcd.bdu.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest;
import com.bcd.bdu.util.BduRestApiUtil;

public class BduApiCallProcessor extends BduRestApiUtil implements Processor  {
	
	private String pastdate=null;
	
	private String filename=null;
	
public BduApiCallProcessor(String pastdate,String filename) {
	this.pastdate=pastdate;
	
	this.filename=filename;
	
}
	public void process(Exchange exchange) throws Exception {
		
		int timeinterval=Integer.parseInt(exchange.getContext().resolvePropertyPlaceholders("[(api.time.interval)]"));
		String path=exchange.getContext().resolvePropertyPlaceholders("[(api.job.stop.date.path)]");
		String logenable=exchange.getContext().resolvePropertyPlaceholders("[(is.api.date.range.log.enabled)]");
		PNRListRequest request = new PNRListRequest();
		String lastrundate=null;
		/*Get lastrundate of Rest Api i.e used to prepare startdate and enddate of request parameter*/
		lastrundate =getLastEndDateForApiCall(path,filename);
		
		/*Create request parameter of Api */
	    createRequest(request,path,timeinterval,pastdate,lastrundate, logenable,filename);
		
		
		exchange.getIn().setBody(request);
		
	}

		
}
