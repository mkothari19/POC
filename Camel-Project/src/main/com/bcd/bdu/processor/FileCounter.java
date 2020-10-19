package com.bcd.bdu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileCounter implements Processor {

	private int count;
	public FileCounter(int counter){
		this.count=counter;
	}
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		count++;
		exchange.getIn().setHeader("numberoffile",count);
	}

}
