package com.bcd.bdu.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
/*
 * CompleatXmlTransformation : This route used for standardization
 */
public class CompleatXmlTransformation extends RouteBuilder  {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newFixedThreadPool(80);
		/*from("file://[(api.complete.raw.directory)]?maxMessagesPerPoll=500&sortBy=file:name&idempotent=[(duplicate.file.nerver.process)]&delete=true" +
					"&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")*/
		from("file://[(api.complete.raw.directory)]?delete=true")
		.routeId("CompleatXmlTransformation")
		.multicast()
		.parallelProcessing().executorService(executor)
		.doTry()
		.process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				String xmldata=exchange.getIn().getBody(String.class);
				xmldata=xmldata.replaceAll("&", "");
				exchange.getIn().setBody(xmldata);
			}
			 
		 })
		.convertBodyTo(String.class)
		.to("xslt:file://[(xslt.path)]/[(api.compleat.xslt)]")
		 .to("file://[(api.complete.compleat.directory)]?fileName=${header.CamelFileName}")
		 .doCatch(Exception.class)
		 .process(new GetExceptionMessage())
		 .to("file://[(api.complete.compleat.standardization.directory)]")
		 .process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					String filename=exchange.getIn().getHeader("CamelFileName",String.class);
					String exception=exchange.getIn().getHeader("BduExceptionMessage",String.class);
					String fileinsertts[]=filename.split("landingts");
			        String filearr[]=fileinsertts[1].split(".zip");  
			        String zipfilename=filearr[0]+".zip";
			        filename= filearr[1];
			        String errormessage=filename+" with in  "+zipfilename+" file : "+exception+"\n";
			        exchange.getIn().setHeader("InvalidXMLException", errormessage);
				}
				 
			 })
		 .setBody(simple("CompleatXmlTransformation :Invalid  ${header.InvalidXMLException}"))
		 .to("file://[(api.complete.compleat.log)]?fileName=standardization_error_${date:now:MM-dd-yyyyhh}.log&fileExist=Append")
		 .doFinally()
	     .end();
	}

}
