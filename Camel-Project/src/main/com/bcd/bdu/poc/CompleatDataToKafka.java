package com.bcd.bdu.poc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class CompleatDataToKafka implements Processor{

	 public static KafkaProducer producer;
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		 configureProducer();
		 File f = exchange.getIn().getBody(File.class);
	        FileReader fr = new FileReader(f);
	        BufferedReader reader = new BufferedReader(fr);
	        String line = reader.readLine();
	        String topic="/data/hs/tsnt/froff/bdu/streams/tsnt_stream_pnrsdiffsrc:compleatpnr";
		 while (line != null) {
			  
	            /* Add each message to a record. A ProducerRecord object
	             identifies the topic or specific partition to publish
	             a message to.*/ 
	            ProducerRecord<String, String> rec = new ProducerRecord<>(topic,  line);

	            // Send the record to the producer client library.
	            producer.send(rec);
	        //    System.out.println("Sent message: " + line);
	            line = reader.readLine();
	             Thread.sleep(600l);

	        }

	        producer.close();
	}

	  public static void configureProducer() {
	        Properties props = new Properties();
	        props.put("key.serializer",
	                "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer",
	                "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("bootstrap.servers", "localhost:9092");

	        producer = new KafkaProducer<>(props);
	    }
}
