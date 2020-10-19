package com.bcd.bdu.poc;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
public class TestCamelRoute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create formatter
		
		
			CommandLineParser parser = new DefaultParser();
		CamelContext apiClient = new DefaultCamelContext();
		//registerRoutes(apiClient);
		ProducerTemplate template = apiClient.createProducerTemplate();
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

        PropertiesComponent prop = apiClient.getComponent("properties", PropertiesComponent.class);
        prop.setPrefixToken("[(");
        prop.setSuffixToken(")]");
        prop.setLocation("application.properties");
        try {	
      
			registerRoutes(apiClient);
			 apiClient.start();
			 Thread.sleep(100000);
			 apiClient.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void registerRoutes(CamelContext apiClient) throws Exception{
		
		//apiClient.addRoutes(new SMCKafkaConsumer());
		apiClient.addRoutes(new ZipFileRoute());
		apiClient.addRoutes(new CompleatXMLToJson());
		apiClient.addRoutes(new PublishToKafka());
	    
		
		//apiClient.addRoutes(new StreamRoute());

        // Disable route
		//apiClient.addRoutes(new DataPipelineRoute());
	}

}