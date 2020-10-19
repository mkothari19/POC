package com.bcd.bdu;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest;

public class BDUTFAPIClientRoute extends RouteBuilder {

	public void configure(){
        /*
		PropertiesComponent prop = getContext().getComponent("properties", PropertiesComponent.class);
		prop.setPrefixToken("[(");
		prop.setSuffixToken(")]");
		prop.setLocation("application.properties");
        */

		from("direct:tf_api_route")
			.log("Start TF Processing")
			.choice()
				.when()
					.body(PNRListRequest.class::isInstance)
					.to("direct:tf_booking_data")
				.when()
					.simple("${header.elastic} == 'get_bookingdata'")
					.to("direct:get_json_elastic")
				.otherwise()
					.log("Unknown command")
			.endChoice();
	}
}

