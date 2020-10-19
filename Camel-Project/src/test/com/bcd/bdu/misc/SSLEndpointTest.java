package com.bcd.bdu.misc;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.fail;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class SSLEndpointTest {
	
	@Ignore
	public void testExternalEndpointGET() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder(){
			@Override
			public void configure() throws Exception {
				from("direct:start")
					.setExchangePattern(ExchangePattern.InOut)
					.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
					.setHeader(Exchange.HTTP_PATH, constant("/posts/1"))
					.to("https4://jsonplaceholder.typicode.com");
			}
		});
		context.start();
		ProducerTemplate template = context.createProducerTemplate();
		template.requestBody("direct:start","Test",String.class);
	}

	@Ignore
	public void testExternalEndpointPOST() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder(){
			@Override
			public void configure() throws Exception {
				from("direct:start")
					.setExchangePattern(ExchangePattern.InOut)
					.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
					.setHeader(Exchange.HTTP_PATH, constant("/posts"))
					.to("https4://jsonplaceholder.typicode.com");
			}
		});
		context.start();
		ProducerTemplate template = context.createProducerTemplate();
		template.requestBody("direct:start","Test",String.class);
	}	
}

