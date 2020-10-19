package com.bcd.bdu.route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;

public class TFFileTransferRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		 from("file://[(api.endpoint.tf.pnr.landing.dir)]?delete=true")
			.routeId("TFFileTransferRoute")
			.doTry()
			.convertBodyTo(String.class)
			.to("xslt:file://[(xslt.path)]/[(api.tf.xslt)]")
			.to("file://[(api.tf.transform.directory)]")
			.doCatch(Exception.class)
			.process(new GetExceptionMessage())
			.to("file://[(api.endpoint.tf.standardization.directory)]")
			.setBody(simple("<br><b>>></b><b>Date</b>: ${date:now:yyyy-MM-dd-HH:mm}: <b>FileName</b>: '${header.CamelFileName}': <b>Error</b> : ${header.BduExceptionMessage}"))     .transform(body().append("</br>"))
			.to("file://[(api.endpoint.tf.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
			 .doFinally()
		     .end();
			
	}

}
