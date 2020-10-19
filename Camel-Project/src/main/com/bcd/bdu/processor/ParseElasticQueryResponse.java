package com.bcd.bdu.processor;

//import org.elasticsearch.index.query.ConstantScoreQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.JsonNode;

public class ParseElasticQueryResponse implements Processor {
	public void process(Exchange exchange) throws Exception {
		JsonNode response = (JsonNode)exchange.getIn().getBody();
		if(response.at("/hits/total").asInt() > 0){
			JsonNode result = response.at("/hits/hits/0/_source");
			exchange.getIn().setBody(result.toString());
		} else {
			exchange.getIn().setBody("{}");
		}
	}
}
