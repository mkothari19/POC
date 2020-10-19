package com.bcd.bdu.processor;

//import org.elasticsearch.index.query.ConstantScoreQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http4.HttpMethods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ElasticSearchQuery implements Processor {
	public void process(Exchange exchange) throws Exception {
		/*
		 * Commented line below because of performance concern
		 * Refractor to avoid overhead on parsing JSON Object
		*/
		//String pnrid = (String) exchange.getIn().getBody();
		//ObjectMapper mapper = new ObjectMapper();
		//JsonNode rootNode = mapper.readTree("{}");
		//String term = QueryBuilders.termQuery("pnrid",10).toString();
    	//ConstantScoreQueryBuilder csb =  QueryBuilders
   		//        .constantScoreQuery(QueryBuilders.termQuery("pnrd",pnrid));
    	//JsonNode query = mapper.readTree(term);
    	//((ObjectNode)rootNode).put("query",query);

		String pnrid = (String) exchange.getIn().getHeader("pnrid");
        JsonNode rootNode = (JsonNode) exchange.getIn().getBody();
		String elastic_httpPath = exchange.getContext().resolvePropertyPlaceholders("[(api.elastic.server.index)]");
		String query = exchange.getContext().resolvePropertyPlaceholders("[(api.elastic.pnr.query)]");
		String pnrIdPath = exchange.getContext().resolvePropertyPlaceholders("[(api.elastic.pnrid.path)]");
		
		JsonNode pnr = rootNode.at("/query" + pnrIdPath);
		((ObjectNode) pnr).put("pnrid",pnrid);

		exchange.getIn().setHeader(Exchange.HTTP_METHOD, HttpMethods.POST);
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
		exchange.getIn().setHeader(Exchange.HTTP_PATH, elastic_httpPath + "/_search");
    	exchange.getIn().setBody(rootNode.toString());		
	}
}
