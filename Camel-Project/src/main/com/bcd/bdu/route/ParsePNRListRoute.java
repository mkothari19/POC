package com.bcd.bdu.route;

import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.model.bookingdata.list.resp.PNRResponse;

public class ParsePNRListRoute extends RouteBuilder {
	public void configure(){

    	onException(Exception.class)
    	.process(new Processor(){
    		public void process(Exchange exchange) throws Exception {
    			String filename = exchange.getIn().getHeader("CamelFileName") != null 
    				? (String)exchange.getIn().getHeader("CamelFileName") : "NA";
    			exchange.getIn().setBody("${date:now:yyyyMMddHHmmss}: ParsePNRListRoute : " + filename+"\r\n");
    		}
    	})    	
    	.to("file://[(api.endpoint.directbooking.log)]?fileName=airbnbpnrlist_parse_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	.handled(true);
		
		from("direct:parse_pnr_list")
		.routeId("DBrsePNRLISTRoute")
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					
					PNRResponse list = (PNRResponse) exchange.getIn().getBody();
					
					String pnr=	list.getPNRId().stream().filter(p->!p.isEmpty() && p!=null).map(i-> i).collect(Collectors.joining("\n"));
					
					exchange.getOut().setBody(pnr);
					              
					
				}
			})
			.to("file://[(api.endpoint.pnr.parse.ids)]")
			.to("direct:api_interpret_resp");
		
		
		
	}
	
	
}
