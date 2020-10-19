package com.bcd.bdu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class GetExceptionMessage implements Processor {
	public void process(Exchange exchange) {
		try {
			Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
			exchange.getIn().setHeader("BduExceptionMessage", (exception.getMessage()).replaceAll("\\r\\n|\\r|\\n", ""));
		} catch (Exception ex) {
			//ex.printStackTrace();
			exchange.getIn().setHeader("BduExceptionMessage", ex);
		}
	}
}
