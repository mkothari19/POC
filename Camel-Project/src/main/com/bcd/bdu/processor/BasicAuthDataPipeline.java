package com.bcd.bdu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BasicAuthDataPipeline implements Processor {
	public void process(Exchange exchange) throws Exception {
		String username = (String) exchange.getIn().getHeader("username");
		String password = (String) exchange.getIn().getHeader("password");
		String job = (String) exchange.getIn().getHeader("job");
		
		String credential = "authUsername=" + username + "&authPassword=" + password;
		String authMode = "authMethod=Basic&authenticationPreemptive=true";
		String pipeline = "/job/" + job + "/build";

		exchange.getIn().setHeader(Exchange.HTTP_QUERY,credential + "&" + authMode);
		exchange.getIn().setHeader(Exchange.HTTP_PATH,pipeline);
	}
}
