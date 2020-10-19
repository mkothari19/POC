package com.bcd.bdu.poc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;



public class PublishToKafka extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// setup kafka component with the brokers

       /* KafkaComponent kafka = new KafkaComponent();

        kafka.setBrokers("[(api.stream.brokers)]");
      
        getContext().addComponent("kafka", kafka);*/
		
		  ExecutorService executor = Executors.newFixedThreadPool(20);
		
		// TODO Auto-generated method stub
		from("file://[(api.complete.compleat.directory)]?delete=true")
		.routeId("DirectToKafka")
		.multicast()
		.parallelProcessing().executorService(executor)
		.doTry()
		.process(new KafkaRestClient())
		.to("file://[(api.complete.compleat.process.directory)]")
		.doCatch(Exception.class)
	    .to("file://[(bdu.error.landing.directory)]")
		 .to("file://[(api.endpoint.pnr.deadletter)]?fileName=deadletters.txt&fileExist=Append")
		 .doFinally()
	     .end();
       /* from("file://[(api.complete.compleat.directory)]?delete=true")
		.routeId("DirectToKafka")
        .recipientList(simple("kafka:/data/hs/tsnt/froff/bdu/streams/tsnt_stream_pnrsdiffsrc:compleatpnr"));*/
		/*
		from("file://[(api.complete.compleat.directory)]?delete=true")
		.routeId("DirectToKafka")
		.process(new CompleatDataToKafka())
		.to("file://[(api.complete.compleat.process.directory)]");*/
	}

	 
}
