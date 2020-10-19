package com.bcd.bdu.poc;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;

public class ZipFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		 from("file://[(api.endpoint.tf.pnr.landing.dir)]?consumer.delay=10000&maxMessagesPerPoll=2&?idempotent=true&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
	     .routeId("compleatziptoxml")
		 .split(new ZipSplitter())
	      .streaming()
	      .to("xslt:[(api.compleat.xslt)]")
	      .to("file://[(api.tf.transform.directory)]")
	     .end();
	}

}
