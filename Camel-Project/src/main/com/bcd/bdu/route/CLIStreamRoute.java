package com.bcd.bdu.route;

import java.util.concurrent.CountDownLatch;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;

public class CLIStreamRoute extends RouteBuilder {

    private final CountDownLatch latch;

    public CLIStreamRoute(CountDownLatch latch){
        this.latch = latch;
    }

	public void configure() throws Exception {
		String brokers = System.getProperty("producer.brokers");
        KafkaComponent kafka = new KafkaComponent();
        kafka.setBrokers(brokers);
        getContext().addComponent("kafka",kafka);
        getContext().getShutdownStrategy().setLogInflightExchangesOnTimeout(false);

        final String saslJaasConfig = "org.apache.kafka.common.security.plain.PlainLoginModule required " + 
                "username=\"username\" password=\"password\";";

    	onException(Exception.class)
    	    .continued(true)
    	    .process(new Processor(){
    		    public void process(Exchange exchange) throws Exception {
    			    Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
    			 //   System.out.println("\n Stream Producer Error : " + exception.getMessage() + "\n");
                    if(exchange.getIn().getHeader("CamelFileName") != null){
    			        exchange.getIn().setHeader(Exchange.FILE_NAME, simple("${file:onlyname.noext}-${date:now:yyyyMMddHHmmss}.json"));
                    }else{
    			        exchange.getIn().setHeader(Exchange.FILE_NAME, simple("direct_message-${date:now:yyyyMMddHHmmss}.json"));
                    }
    		    }
    	    })
            .to("properties:error.landing.dir")
            .to("direct:shutdown_exception")
            .end();

        from("direct:shutdown_exception")
            .process(new Processor(){ public void process(Exchange exchange) throws Exception { 
                latch.countDown();
            }})
            .log("File successfully move to error landing directory.");

        // Producer: Direct Publish
        from("direct:direct-publish")
            .routeId("Stream Producer")
            .recipientList(simple("kafka:${header.BduStreamServer}?topic=${header.BduStreamTopic}"));

        // Producer: Publish File's content to Streams Topic
        from("properties:consume.dir")
			.routeId("PubDirFiles")
            .noAutoStartup()
            .shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
            .onCompletion()
                .process(new Processor(){ public void process(Exchange exchange) throws Exception { 
                    getContext().stop();
                    latch.countDown();
                }})
            .end()
			.to("properties:kafka.producer.config");
	}
		
}
