package com.bcd.bdu.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class DataPipelineRoute extends RouteBuilder {
	public void configure(){
    	
    	onException(Exception.class)
    	.process(new Processor(){
    		public void process(Exchange exchange) throws Exception {
                Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
    			exchange.getIn().setBody("Encounter error executing Elasticsearch query.. \r\n"
                    + "Error Message : " + exception.getMessage());
    		}
    	})    	
    	.to("stream:out")
    	.handled(true);

		// Direct HBase Operation
    /*
		from("direct:hbase_put_xml")
			.to("xslt:OneLinerXml.xslt")
			.convertBodyTo(String.class)
			.process(new HBaseOperation())
			.to("hbase://[(api.endpoint.pnr.hbase.table)]");

		from("direct:get_json_elastic")
			.unmarshal().json(JsonLibrary.Jackson,JsonNode.class)
			.process(new ElasticSearchQuery())
			.to("http4://[(api.elastic.server)]?bridgeEndpoint=true")
			.unmarshal().json(JsonLibrary.Jackson,JsonNode.class)
			.process(new ParseElasticQueryResponse())
			.to("stream:out");
		*/

	}
}
