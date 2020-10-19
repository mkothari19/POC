package com.bcd.bdu.route;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SMCFileTransferRoute  extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("direct:smcfiletransfer")
		.routeId("SMCFileConsume")
		 .process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				  ProcessBuilder processBuilder = new ProcessBuilder();
				try {
					String smchdfspath= exchange.getContext().resolvePropertyPlaceholders("[(api.smc.hdfs.path)]");
					String smcrawfilepath= exchange.getContext().resolvePropertyPlaceholders("[(bdu.datapipeline.smc.directory)]");
					String command="hadoop fs -mv "+smchdfspath.trim()+"/part-*"+" "+smcrawfilepath.trim()+"/";
					System.out.println(command);
					//String command="move  "+smchdfspath.trim()+"/part-*"+" "+smcrawfilepath.trim()+"/";
					processBuilder.command("bash", "-c", command);
					Process process = processBuilder.start();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					process.destroy();
				}catch(Exception ex) {
					System.out.println("DATA IS NOT AVAILABLE IN SMC KAFKA TOPIC"+ex);
				}
			}
			 
		 })
		.to("direct:api_interpret_resp");	
	}

}
