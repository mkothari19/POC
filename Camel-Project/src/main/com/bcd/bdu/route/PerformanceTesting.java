package com.bcd.bdu.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;

public class PerformanceTesting extends RouteBuilder {
	public void configure(){
		getContext().addRoutePolicyFactory(new MetricsRoutePolicyFactory());

		from("cxfrs://http://localhost:17979?resourceClasses=com.bcd.bdu.helpers.PerformanceService")
			.routeId("API Route Metrics")
			.setBody(constant("PERF"));
		/*
		// Mode : Perf 
		from("cxfrs://http://localhost:17679?resourceClasses=com.bcd.bdu.helpers.PerformanceService")
			.routeId("Performance Testing")
			.choice()
				.when()
					.simple("${header.CamelHttpPath} == '/performancetesting/get'")
					.process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							String query = exchange.getContext().resolvePropertyPlaceholders("[(api.elastic.pnr.query)]");
							String emsg = "{\"query\":" + query + "}"; 
							exchange.getIn().setHeader("elastic","get_bookingdata");
							exchange.getIn().setHeader("pnrid","932497544780417");
							exchange.getIn().setBody(emsg);
							//exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
						}
					})
					//.setBody(constant("ERROR"))
					.to("direct:get_json_elastic")
				.when()
					.simple("${header.CamelHttpPath} == '/performancetesting/poll'")
					.process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							PNRListRequest request = new PNRListRequest();
							DateRanges ranges = new DateRanges();
							ranges.setStartDate("20180323000000"); ranges.setEndDate("20180324000000");
							request.setDateRanges(ranges);
							exchange.getIn().setBody(request);
							exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
						}
					})
					.to("direct:api_route")
				.otherwise()
					.setBody(constant("ERROR"))
			.endChoice();
		*/
	}
}
