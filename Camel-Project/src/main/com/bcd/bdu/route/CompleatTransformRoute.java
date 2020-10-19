package com.bcd.bdu.route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;



public class CompleatTransformRoute extends RouteBuilder {

	public void configure() {

    	/*onException(Exception.class)
    	.process(new GetExceptionMessage())
    	.handled(true)
    	.choice()
    		.when()
    			.simple("${header.dbfilename} != null")
    			.to("file://[(api.endpoint.directbooking.standardization.directory)]")
    			.setBody(simple("<br><b>>></b> ${date:now:yyyy-MM-dd-HH:mm}: CompleatTransformRoute Unable to process '${header.dbfilename}'  because of exception > ${header.BduExceptionMessage}"))
    		    .transform(body().append("</br>"))
    		    .to("file://[(api.endpoint.directbooking.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
    		.otherwise()
    		.setBody(simple("<br><b>>></b> ${date:now:yyyy-MM-dd-HH:mm}: CompleatTransformRoute Unable to process 'NA' because of exception > ${header.BduExceptionMessage}"))
		    .transform(body().append("</br>"))
		    .to("file://[(api.endpoint.directbooking.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
    	.endChoice();*/

	/*	
		 *  Route transformation based on Datasource
		 
		from("direct:transform_to_compleat")
			.routeId("Select Transformation by Data Source")
			//.process(new CorrelatePNR())
			.choice()
			.when()
				.simple("${header.CamelFileNameOnly}  starts with 'directbooking'")
				.to("direct:booking_data_to_compleat")
			.otherwise()
				.log("unknown source")
			.endChoice();

		
		 * BookingData XML to DB XML
		 */
		from("direct:booking_data_to_compleat")
			.routeId("Transform BookingData to Compleat")
			.doTry()
			.convertBodyTo(String.class)
			.to("xslt:file://[(xslt.path)]/[(api.directbooking.xslt)]")
			.to("file://[(api.compleat.directory)]")
			.doCatch(Exception.class)
			.process(new GetExceptionMessage())
			.to("file://[(api.endpoint.directbooking.standardization.directory)]")
			.setBody(simple("<br><b>>></b><b>Date</b>: ${date:now:yyyy-MM-dd-HH:mm}: <b>FileName</b>: '${header.CamelFileName}': <b>Error</b> : ${header.BduExceptionMessage}"))
			  .transform(body().append("</br>"))
		    .to("file://[(api.endpoint.directbooking.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
			 .doFinally()
		     .end();
			}
}
