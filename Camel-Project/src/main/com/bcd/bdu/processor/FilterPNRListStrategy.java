package com.bcd.bdu.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class FilterPNRListStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange original, Exchange resource){
		if(resource == null) return original;

		String rs = resource.getIn().getBody(String.class);
		String new_rs = original.getIn().getBody(String.class); 
		List<String> rs_list = new ArrayList<String>(Arrays.asList(rs.split("\n")));
		List<String> new_rs_list = new ArrayList<String>(Arrays.asList(new_rs.split("\n")));

		String out = new_rs_list.stream()
			.filter(s -> !rs_list.contains(s)).collect(Collectors.joining("\n"));

	//	System.out.println(out);
		resource.getIn().setBody(out);
		return resource;
	}

}
