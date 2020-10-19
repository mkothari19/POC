package com.bcd.bdu.route;

import org.apache.camel.builder.RouteBuilder;


public class DirectBookingFileTransferRoute extends RouteBuilder {


	@Override
	public void configure() throws Exception {
	
		//from("file://[(api.endpoint.pnr.landing.dir)]?delete=false")
		from("file://[(api.endpoint.pnr.landing.dir)]?delete=true")
			
			.routeId("DirectBookingFileTransferRoute")
			.doTry()
			//.unmarshal(pnr)
		//	.process(new KeyGenerator())
			.to("direct:booking_data_to_compleat")
			 .doCatch(Exception.class)
		    .to("file://[(api.endpoint.directbooking.standardization.directory)]")
		    .setBody(simple("<br><b>>></b><b>Date</b>: ${date:now:yyyy-MM-dd-HH:mm}: <b>FileName</b>: '${header.dbfilename}': <b>Error</b> : ${header.BduExceptionMessage}"))
			  .transform(body().append("</br>"))
		    .to("file://[(api.endpoint.directbooking.log)]?fileName=standardization_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
			 .doFinally()
		     .end();
		   
	
	}

}
