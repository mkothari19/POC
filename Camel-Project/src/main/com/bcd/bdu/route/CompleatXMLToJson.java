package com.bcd.bdu.route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.CompleatJsonBuilderProcessor;
import com.bcd.bdu.processor.GetExceptionMessage;
/*
 * CompleatXMLToJson: This route used from xml to json transformation
 */


public class CompleatXMLToJson extends RouteBuilder {

	private Map<String,String> gdsValue=new HashMap<String,String>();
	
	public CompleatXMLToJson(Map<String,String> gdsValue) {
		this.gdsValue=gdsValue;
	}

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(70);
      	 from("file://[(api.complete.compleat.directory)]?sortBy=file:name&idempotent=[(duplicate.file.nerver.process)]&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
		//from("file://[(api.complete.compleat.directory)]?maxMessagesPerPoll=500&delete=true")
		//from("file://[(api.complete.compleat.directory)]?idempotent=true&scheduler=quartz2&scheduler.cron=[(compleat.xml.cron)]")
			.routeId("CompleatXMTOJSONFileTransferRoute")
				.multicast()
				.parallelProcessing().executorService(executor)
				.doTry()
				.convertBodyTo(String.class)
				.process(new CompleatJsonBuilderProcessor(gdsValue)) 
				.to("file://[(api.complete.json.directory)]?fileName=${header.CompleatFileName}.json")
				.doCatch(Exception.class)
				.process(new GetExceptionMessage())
				.to("file://[(api.complete.compleat.errorinjson.directory)]")
				 .process(new Processor() {

						@Override
						public void process(Exchange exchange) throws Exception {
							// TODO Auto-generated method stub
							String filename=exchange.getIn().getHeader("CamelFileName",String.class);
							String fileinsertts[]=filename.split("landingts");
					        String filearr[]=fileinsertts[1].split(".zip");  
					        String zipfilename=filearr[0]+".zip";
					        filename= filearr[1];
					        String errormessage=filename+" of zip "+zipfilename;
					        exchange.getIn().setHeader("FileName", errormessage);
						}
						 
					 })
				  .setBody(simple("<br><b>>></b>Date: ${date:now:yyyy-MM-dd-HH:mm}:FileName: '${header.FileName}':Error: ${header.BduExceptionMessage}"))
				   .transform(body().append("</br>"))
			     .to("file://[(api.complete.compleat.log)]?fileName=xmltojson_transform_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
				 .doFinally()
			     .end();
		
	}
}
