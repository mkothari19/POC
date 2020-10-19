package com.bcd.bdu.route;

import org.junit.Test;
import org.junit.Ignore;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class NotificationRouteTest {

    @Test
    public void testNone(){}

	@Ignore
	public void testSMTPEmailProtocol() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                from("direct:smtp-test")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                exchange.getIn().setHeader("To","patpenetrante@gmail.com");
                                exchange.getIn().setHeader("Subject","SMTP notification test");
                                exchange.getIn().setBody("Test two");
                            }
                        })
                        .to("smtps://smtp.gmail.com:465?username=spexprotomail&password=p3@rth04&debugMode=true");
            }
        });
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
        template.requestBody("direct:smtp-test","Test",String.class);
	}
}
