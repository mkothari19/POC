package com.bcd.bdu.route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;

/** 
 SMCStreamingRoute :Standardize the raw SMC data 
 
 **/

public class SMCStreamingRoute extends RouteBuilder {
	 
	
	
private String parllelthread=null;

public SMCStreamingRoute(String parllelthread ) {
	this.parllelthread=parllelthread;
}

@Override
 public void configure() throws Exception {
		
		
		ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(parllelthread));
	from("file://[(bdu.datapipeline.smc.directory)]?include=part-.*&idempotent=false&delete=true" +
				"&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
	.routeId("SMCStreamingRoute")
	.multicast()
	.parallelProcessing().executorService(executor)
	.doTry()
	.convertBodyTo(String.class)
		 .process(new Processor() {

		@Override
		public void process(Exchange exchange) throws Exception {
			// TODO Auto-generated method stub
			 final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	 	        f.setTimeZone(TimeZone.getTimeZone("UTC"));
	 	       	String ts=f.format(new Date());
	 	       	String xmlfilename=	exchange.getIn().getHeader("CamelFileName",String.class);
	 	       	xmlfilename=xmlfilename.replaceAll(" ", "-");
	 	       	String filename=ts+"landingts"+xmlfilename;
	 	       	exchange.getIn().setHeader("smcfilename", filename);
	} })
	.setHeader("smc_pnr", xpath("/PNRs/PNR/PNR_ID/text()"))
	.choice()
	.when().simple("[(save.smc.raw.xml)] == 'true'")
	.to("file://[(bdu.s3.smc.raw)]?fileName=smc-${date:now:yyyyMMddhhmmss}-${header.smc_pnr}.xml")
    .endChoice()
	.endDoTry()
	 .to("file://[(api.endpoint.pnr.smc.schemaaval.dir)]?fileName=${header.smcfilename}.xml")
	  .to("xslt:file://[(xslt.path)]/[(api.smc.xslt)]")
	     .to("file://[(api.stream.landing.dir)]?fileName=${header.smcfilename}.xml")
	 .doCatch(Exception.class)
	 .process(new GetExceptionMessage())
	 .to("file://[(api.endpoint.smc.standardization.directory)]")
	  .setBody(simple("<br><b>>></b><b>Date</b>: ${date:now:yyyy-MM-dd-HH:mm}: <b>FileName</b>: '${header.smcfilename}': <b>Error</b> : ${header.BduExceptionMessage}"))
			   .transform(body().append("</br>"))
	.to("file://[(api.endpoint.smc.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
	 .doFinally()
     .end();
	 	}
}
