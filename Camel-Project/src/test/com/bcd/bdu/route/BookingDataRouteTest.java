package com.bcd.bdu.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class BookingDataRouteTest extends CamelTestSupport {

	@EndpointInject(uri = "mock:DESTINATION")
	private MockEndpoint mockDestination;

	@Test
	public void testPollingByQuartz() throws Exception {
		resetMocks();
		mockDestination.expectedMessageCount(3);
		Thread.sleep(10000);
		assertMockEndpointsSatisfied();
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {

		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				//@formatter:off
				from("quartz2://myGroup/myTimerNameOne?trigger.repeatInterval=100&trigger.repeatCount=1")
					.setProperty("test").constant("This is the job 1")
					.to(mockDestination);
				from("quartz2://myGroup/myTimerNameTwo?trigger.repeatInterval=100&trigger.repeatCount=0")
				.setProperty("test").constant("This is the job 2")
				.to(mockDestination);
				//@formatter:on
			}
		};
	}

}
