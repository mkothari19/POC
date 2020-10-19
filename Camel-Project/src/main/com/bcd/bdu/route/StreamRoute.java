package com.bcd.bdu.route;

import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.kafka.KafkaComponent;
//import org.apache.camel.component.kafka.KafkaConstants;

public class StreamRoute extends RouteBuilder {

	public void configure() throws Exception {
		/*String brokers = getContext().resolvePropertyPlaceholders("[(api.stream.brokers)]");
        KafkaComponent kafka = new KafkaComponent();
        kafka.setBrokers(brokers);
        getContext().addComponent("kafka",kafka);

        final String saslJaasConfig = "org.apache.kafka.common.security.plain.PlainLoginModule required " + 
                "username=\"username\" password=\"password\";";

    	onException(Exception.class)
    	.process(new Processor(){
    		public void process(Exchange exchange) throws Exception {
    			Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
    			exchange.getIn().setBody("StreamRoute : " + exception.getMessage());
    		}
    	})    	
    	.log("${body}")
    	.handled(true);

		// Consumer
		from("kafka:[(api.stream.brokers)]?topic=[(api.stream.topic)]"
			+ "&maxPollRecords=[(api.stream.maxpollrecords)]"
			//+ "&autoOffsetReset=earliest"
            //+ "&saslMechanism=PLAIN" 
            //+ "&securityProtocol=SASL_SSL" 
            //+ "&saslJaasConfig=" + saslJaasConfig
			+ "&consumersCount=[(api.stream.consumers)]")
		 from("kafka:[(api.stream.topic)]?brokers=[(api.stream.server)]"
                 + "&maxPollRecords=[(api.stream.maxpollrecords)]"
                 + "&consumersCount=[(api.stream.consumers)]"
              //   + "&seekTo={{consumer.seekTo}}"
                // + "&groupId={{consumer.group}}")
				 	)
    	
			.routeId("Stream Consumer")
			.doTry()
                .convertBodyTo(String.class)
                .to("file://[(api.stream.landing.dir)]?fileName=stream-${exchangeId}.xml")
            .doCatch(Exception.class)
             .log("Invalid xml : ${body}")
            .doFinally()
            .to("file://[(bdu.error.landing.directory)]")
            .end();

		// Producer: Direct Publish
		from("direct:direct-publish")
			.routeId("Stream Producer")
			.recipientList(simple("kafka:${header.BduStreamServer}?topic=${header.BduStreamTopic}"));
*/
	}
	
	public static void process() {
		
	}
		
}
