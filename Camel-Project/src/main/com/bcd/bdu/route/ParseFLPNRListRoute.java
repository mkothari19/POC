package com.bcd.bdu.route;

import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.model.bookingdata.list.resp.PNRResponse;

public class ParseFLPNRListRoute extends RouteBuilder {
	public void configure(){

    	onException(Exception.class)
    	.process(new Processor(){
    		public void process(Exchange exchange) throws Exception {
    			String filename = exchange.getIn().getHeader("CamelFileName") != null 
    				? (String)exchange.getIn().getHeader("CamelFileName") : "NA";
    			exchange.getIn().setBody("${date:now:yyyyMMddHHmmss}: ParseFLPNRListRoute : " + filename+"\r\n");
    		}
    	})    	
    	.to("file://[(bdu.api.error.landing.directory)]?fileName=flpnrlist_parse_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	.handled(true);
		
		
		from("direct:parse_fl_pnr_list")
		.routeId("ParseFLPNRListRoute")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				
				/*
				 *   Above peace of code commented because processing done through new JAXB component(PNRResponse)
				 */
				PNRResponse list = (PNRResponse) exchange.getIn().getBody();
				
				String pnr=	list.getPNRId().stream().filter(p->!p.isEmpty() && p!=null).map(i-> i).collect(Collectors.joining("\n"));
				
				exchange.getOut().setBody(pnr);
				
            }
		})
		.to("file://[(api.endpoint.fl.pnr.parse.ids)]")
		.to("direct:api_interpret_resp");
    
	}
	
	
}

