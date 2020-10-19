package com.bcd.bdu.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public final class MessageConsumerClient {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumerClient.class);

 

    public static void main(String[] args) throws Exception {
    	
    }

      /*  LOG.info("About to run Kafka-camel integration...");

        CamelContext camelContext = new DefaultCamelContext();

        // Add route to send messages to Kafka

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
                pc.setPrefixToken("[(");
                pc.setSuffixToken(")]");
                pc.setLocation("application.properties");
        		

                log.info("About to start route: Kafka Server -> Log ");

                from("kafka:[(consumer.topic)]?brokers=[(api.stream.brokers)]"
                        + "&maxPollRecords=[(consumer.maxPollRecords)]"
                        + "&consumersCount=[(consumer.consumersCount)]"
                        + "&seekTo=[(consumer.seekTo)]"
                        + "&groupId=[(consumer.group)]")
                        .routeId("FromKafka")
                    .log("${body}")
                    .to("file://home/smcadmin/landing" + 
     				"?fileName=consumer.xml");
                from("kafka:localhost:9092?topic=test&groupId=testing&autoOffsetReset=earliest&consumersCount=1")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange)
							throws Exception {
						String messageKey = "";
						if (exchange.getIn() != null) {
							Message message = exchange.getIn();
							Integer partitionId = (Integer) message
									.getHeader(KafkaConstants.PARTITION);
							String topicName = (String) message
									.getHeader(KafkaConstants.TOPIC);
							if (message.getHeader(KafkaConstants.KEY) != null)
								messageKey = (String) message
										.getHeader(KafkaConstants.KEY);
							Object data = message.getBody();


							System.out.println("topicName :: "
									+ topicName + " partitionId :: "
									+ partitionId + " messageKey :: "
									+ messageKey + " message :: "
									+ data + "\n");
						}
					}
				}).to("log:input");
                
            	from("direct:api-smcroute")
            	.process(new Processor(){
            		public void process(Exchange exchange) throws Exception {
            			   String kafkaBrokers="uscdc01tlmap005.globalservices.bcdtravel.local:9092";
            			      String topic ="//streams/smcstream:smcxmltopicpoc1";
            			   //   String group = "10654324-929d-4f96-8a0b-3e6e7daf2fa6";
            			      Properties consumerProperties = new Properties();
            			      consumerProperties.setProperty("group.id", "cdc.consumer");
            			      consumerProperties.setProperty("enable.auto.commit", "true");
            			      consumerProperties.setProperty("auto.offset.reset", "earliest");
            			      consumerProperties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            			      consumerProperties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            			      consumerProperties.put("kafka.bootstrap.servers", kafkaBrokers);
            			      System.out.println("==== Step 222222222222222 ===");
            			      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerProperties);
            			      
            			      consumer.subscribe(Arrays.asList(topic));
            			      System.out.println("Subscribed to topic " + topic);
            			      int i = 0;
            			         
            			     
            			         ConsumerRecords<String, String> records = consumer.poll(100);
            			            for (ConsumerRecord<String, String> record : records) {
            			               System.out.printf("offset = %d, key = %s, value = %s\n", 
            			               record.offset(), record.key(), record.value());
            			            exchange.getIn().setBody(record.value());
            			           
            			          
            			      }
            		//	exchange.getIn().setBody("StreamRoute : " + exception.getMessage());
            		}
            	})    	
            	.log("${body}")
            	.to("file://home/smcadmin/landing");
              
            }
        });
        camelContext.start();

        // let it run for 5 minutes before shutting down
        Thread.sleep(5 * 60 * 1000);

        camelContext.stop();
    }
*/
}
