package com.bcd.bdu.route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;

public class FLFileTransferRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		 from("file://[(api.endpoint.fl.pnr.landing.dir)]?delete=true")
			.routeId("FLFileTransferRoute")
			.doTry()
			.convertBodyTo(String.class)
			.to("xslt:file://[(xslt.path)]/[(api.fl.xslt)]")
			.to("file://[(api.fl.transform.directory)]")
			.doCatch(Exception.class)
			.process(new GetExceptionMessage())
			.to("file://[(api.endpoint.fl.standardization.directory)]")
			.setBody(simple("<br><b>>></b><b>Date</b>: ${date:now:yyyy-MM-dd-HH:mm}: <b>FileName</b>: '${header.CamelFileName}': <b>Error</b> : ${header.BduExceptionMessage}"))
			
			.transform(body().append("</br>"))
			.to("file://[(api.endpoint.fl.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
			.doFinally()
		     .end();
			
	}

}
