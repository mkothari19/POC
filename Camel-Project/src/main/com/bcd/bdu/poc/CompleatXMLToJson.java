package com.bcd.bdu.poc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;


public class CompleatXMLToJson extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setForceTopLevelObject(true);
        ExecutorService executor = Executors.newFixedThreadPool(16);
      	 from("file://[(api.tf.transform.directory)]?delete=true")
			//from("file://[(api.endpoint.pnr.landing.dir)]?noop=true")
				
				.routeId("CompleatXMTOJSONFileTransferRoute")
				.multicast()
				.parallelProcessing().executorService(executor)
				.doTry()
				.convertBodyTo(String.class)
				.marshal(xmlJsonFormat)
				.process(new Processor() {
			        public void process(Exchange exchange) throws Exception {
			        String filename=	exchange.getIn().getHeader("CamelFileName",String.class);
			        filename= filename.replaceAll(".xml","");  
			        // do something with the payload and/or exchange here
			           exchange.getIn().setHeader("CompleatFileName", filename);
			       }})
				.to("file://[(api.complete.compleat.directory)]?fileName=${header.CompleatFileName}.json")
				.doCatch(Exception.class)
			    .to("file://[(bdu.error.landing.directory)]")
				 .to("file://[(api.endpoint.pnr.deadletter)]?fileName=deadletters.txt&fileExist=Append")
				 .doFinally()
			     .end();
		
	}
}
