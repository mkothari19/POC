package com.bcd.bdu.route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;

import com.bcd.bdu.processor.CredentialToken;
import com.bcd.bdu.processor.DataEnrichmentProcessor;
import com.bcd.bdu.processor.FareLogixJsonBuilderProcessor;
import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.util.BduUtils;

public class FareLogixXmlToJson extends RouteBuilder {

	Map<String,String> citymap=new HashMap<String,String>();
	Map<String,String>  gds=new HashMap<String,String>();
	public FareLogixXmlToJson(Map<String,String> citymap,Map<String,String>  gds) {
		this.citymap=citymap;
		this.gds=gds;
	}
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
				ExecutorService executor = Executors.newFixedThreadPool(20);
		      	 from("file://[(api.fl.transform.directory)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
						.routeId("FareLogixXmlToJson")
						.multicast()
						.parallelProcessing().executorService(executor)
						.doTry()
						.convertBodyTo(String.class)
						.process(new FareLogixJsonBuilderProcessor(citymap,gds))
						.to("file://[(api.fl.enrich.files)]?fileName=${header.FLFileName}")
						.doCatch(Exception.class)
						.process(new GetExceptionMessage())
					    .to("file://[(api.endpoint.fl.errorinjson.directory)]")
					    .setBody(simple("<br><b>>></b> ${date:now:yyyy-MM-dd-HH:mm}: Unable to process sourcePNRId '${header.sourcePNRId}'  with in filename ${header.CamelFileName} because of exception > ${header.BduExceptionMessage}")).transform(body().append("\n"))
					    .transform(body().append("</br>"))
					    .to("file://[(api.endpoint.fl.log)]?fileName=xmltojson_transform_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
						 .doFinally()
					     .end();
		   
		      	 
			
	}

}
