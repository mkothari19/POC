package mk.kafka.producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SynProducer {
	
	public static void main(String[] args)  {
	
		String topicName=args[0];
		String filepath=args[1];
		File files=new File(filepath);
		Properties configs=new Properties();
	    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9091,localhost:9092");
	    configs.put(ProducerConfig.ACKS_CONFIG,"all");
	    configs.put(ProducerConfig.BATCH_SIZE_CONFIG, "32384");
	    configs.put(ProducerConfig.LINGER_MS_CONFIG, "2");
	    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	
	    KafkaProducer<String,String> producer=new KafkaProducer<String, String>(configs);
	    BufferedReader reader =null;
	    String currentLine=null;
	    try {
			 reader = new BufferedReader(new FileReader(files));
			
				 currentLine = reader.readLine();
				 reader.close();
				 ProducerRecord<String, String> record = new ProducerRecord<String,String>(topicName, currentLine);
				Future<RecordMetadata>future= producer.send(record);
				
				try {
					System.out.println("Topic ="+future.get().topic()+"\n Partition= "+future.get().partition()+"\n Offset= "+future.get().offset()+"\n TimeStamp= "+future.get().timestamp());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			producer.close();
		}
	    
	    
	    
	    
	}

}
