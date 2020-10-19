package com.bcd.bdu.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;

public class PublishJsonToKafka extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		 ExecutorService executor = Executors.newFixedThreadPool(4);
		from("direct:processjsondata_route")
		.routeId("PublishJsonToKafka")
		.multicast()
		.parallelProcessing().executorService(executor)
		.doTry()
		.convertBodyTo(String.class)
		//.process(new KafkaRestClient())
		.to("log:xml")
		.doCatch(Exception.class)
	    .to("file://[(api.complete.compleat.jsonerror.directory)]")
		 .to("file://[(api.endpoint.compleat.pnr.deadletter)]?fileName=json_deadletters.txt&fileExist=Append")
		 .doFinally()
	     .end();
	}

}
