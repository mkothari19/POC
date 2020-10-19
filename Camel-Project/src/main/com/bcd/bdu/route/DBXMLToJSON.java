package com.bcd.bdu.route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.DirectBookingJsonBuilder;
import com.bcd.bdu.processor.GetExceptionMessage;

public class DBXMLToJSON extends RouteBuilder {
	private Map<String,String> gdsValue=new HashMap<String,String>();
	public DBXMLToJSON(Map<String,String> gdsValue) {
		this.gdsValue=gdsValue;
	}
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
				ExecutorService executor = Executors.newFixedThreadPool(10);
		      	 from("file://[(api.compleat.directory)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
						.routeId("DBXMTOJSONFileTransferRoute")
						.multicast()
						.parallelProcessing().executorService(executor)
						.doTry()
						.convertBodyTo(String.class)
						//.marshal(xmlJsonFormat)
						//.process(new DBXML2JsonProcessor())
						.process(new DirectBookingJsonBuilder(gdsValue))
						.to("file://[(api.directbooking.enrich.files)]?fileName=${header.DBFileName}")
						.doCatch(Exception.class)
						.process(new GetExceptionMessage())
						.to("file://[(api.endpoint.directbooking.errorinjson.directory)]")
					    .setBody(simple("<br><b>>></b> ${date:now:yyyy-MM-dd-HH:mm}: Unable to process sourcePNRId '${header.sourcePNRId}'  with in filename ${header.CamelFileName}  because of exception > ${header.BduExceptionMessage}"))
					    .transform(body().append("</br>"))
						 .to("file://[(api.endpoint.directbooking.log)]?fileName=xmltojson_transform_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
						 .doFinally()
					     .end();
			
	}

}
