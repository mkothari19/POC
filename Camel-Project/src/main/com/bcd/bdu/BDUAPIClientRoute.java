package com.bcd.bdu;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest;

public class BDUAPIClientRoute extends RouteBuilder {

	public void configure(){
        /*
		PropertiesComponent prop = getContext().getComponent("properties", PropertiesComponent.class);
		prop.setPrefixToken("[(");
		prop.setSuffixToken(")]");
		prop.setLocation("application.properties");
        */

		from("direct:api_route")
			.log("Start Processing")
			.choice()
				.when()
					.body(PNRListRequest.class::isInstance)
					.to("direct:booking_data")
				.when()
					.simple("${header.elastic} == 'get_bookingdata'")
					.to("direct:get_json_elastic")
				.otherwise()
					.log("Unknown command")
			.endChoice();
		
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
		
		from("direct:fl_api_route")
		.log("Start FL Processing")
		.choice()
			.when()
				.body(PNRListRequest.class::isInstance)
				.to("direct:fl_booking_data")
			.when()
				.simple("${header.elastic} == 'get_bookingdata'")
				.to("direct:get_json_elastic")
			.otherwise()
				.log("Unknown command")
		.endChoice();
	}
}
