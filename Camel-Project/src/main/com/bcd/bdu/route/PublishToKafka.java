package com.bcd.bdu.route;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.CompleatJsonBuilder;



public class PublishToKafka extends RouteBuilder {
	  private   int count1=1;
	  private   int count2=1;
	@Override
	public void configure() throws Exception {
		// setup kafka component with the brokers

       /* KafkaComponent kafka = new KafkaComponent();

        kafka.setBrokers("[(api.stream.brokers)]");
      
        getContext().addComponent("kafka", kafka);*/
		
		  //ExecutorService executor = Executors.newFixedThreadPool(50);
		//int parition=-1;
		// TODO Auto-generated method stub
		
		from("file://[(api.complete.json.directory)]?idempotent=true&delete=true&maxMessagesPerPoll=1000&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
		.routeId("DirectToKafka")
		/*.multicast()
		.parallelProcessing().executorService(executor)*/
		.doTry()
		.convertBodyTo(String.class)
		.process(new CompleatJsonBuilder(count1))
		//.process(new KafkaRestClient(count2))
		//.to("direct:processjsondata_route")
		.to("file://[(api.complete.compleat.process.directory)]")
		.doCatch(Exception.class)
	    .to("file://[(api.complete.compleat.jsonerror.directory)]")
		 .to("file://[(api.endpoint.compleat.pnr.deadletter)]?fileName=json_deadletters.txt&fileExist=Append")
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
