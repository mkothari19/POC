package com.bcd.bdu.route;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.AuditInterpret;

public class NotificationRoute extends RouteBuilder {
	public void configure(){

    	onException(Exception.class)
    	.handled(true);
        
		from("direct:api_interpret_resp")
			.routeId("Interpret API Response")
			.process(new AuditInterpret())
			.to("log:xml?level=OFF&showBody=false&showBodyType=false&showStreams=true");
		
		from("direct:notification_resp")
		.routeId("Notification API Response")
		.process(new AuditInterpret())
		.to("log:xml?level=OFF&showBody=false&showBodyType=false&showStreams=true");
    }
}
