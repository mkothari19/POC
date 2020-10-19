package com.bcd.bdu.processor;

import java.util.Base64;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http4.HttpMethods;

public class TokenDataPipeline implements Processor {
	public void process(Exchange exchange) throws Exception {
		String username = (String) exchange.getIn().getHeader("username");
		String token = (String) exchange.getIn().getHeader("token");
		String job = (String) exchange.getIn().getHeader("job");
		String inparam = (String) exchange.getIn().getHeader("param");
		String pipeline = "/job/" + job + "/buildWithParameters";

		String enc_token = Base64.getEncoder().encodeToString((username+":"+token).getBytes());
		String param = "param=" + inparam;

		exchange.getIn().setHeader("Authorization", "Basic " + enc_token);
		exchange.getIn().setHeader(Exchange.HTTP_METHOD, HttpMethods.POST);
		exchange.getIn().setHeader(Exchange.HTTP_QUERY,param);
		exchange.getIn().setHeader(Exchange.HTTP_PATH,pipeline);		
	}
}
