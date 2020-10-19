package com.bcd.bdu.poc;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMCKafkaConsumer extends RouteBuilder{
	 private static final Logger LOG = LoggerFactory.getLogger(SMCKafkaConsumer.class);
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		LOG.info("About to run Kafka-camel integration...");
		
		 from("kafka:[(api.stream.topic)]?brokers=[(api.stream.server)]"
                 + "&maxPollRecords=[(api.stream.maxpollrecords)]"
                 + "&consumersCount=[(api.stream.consumers)]"
              //   + "&seekTo={{consumer.seekTo}}"
                // + "&groupId={{consumer.group}}")
				 	)
                 .routeId("FromKafka")
                 .doTry()
                 .convertBodyTo(String.class)
               .to("file://[(bdu.datapipeline.smc.directory)]" + 
     				"?fileName=bdu_smc_raw_${date:now:yyyyMMddHHmm}.xml&fileExist=Append")
		 .doCatch(Exception.class)
        // .log("Invalid xml : ${body}")
        .doFinally()
        .end();
	}

}
