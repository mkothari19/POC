package com.bcd.bdu.route;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.json.JSONObject;

import com.bcd.bdu.model.bookingdata.list.resp.PNRResponse;
import com.bcd.bdu.processor.CredentialToken;

 
public class CLIDirectBookingRoute extends RouteBuilder {
    private final CountDownLatch latch;

    public CLIDirectBookingRoute(CountDownLatch latch){
        this.latch = latch;
    }

	public void configure() throws Exception {
        JaxbDataFormat request = new JaxbDataFormat("com.bcd.bdu.model.bookingdata.list.req");
        JaxbDataFormat response = new JaxbDataFormat("com.bcd.bdu.model.bookingdata.list.resp");

    	onException(Exception.class)
    	    .continued(true)
    	    .process(new Processor(){
    		    public void process(Exchange exchange) throws Exception {
    			    Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
                    exchange.getIn().setHeader(Exchange.FILE_NAME, simple("PNRListRequest-${date:now:yyyyMMddHHmmss}.txt"));
    		    }
    	    })
            .to("file://[(bdu.error.landing.directory)]")
            .end();

        from("direct:booking_data_sa")
            .routeId("GetDirectBooking")
            .process(new CredentialToken(new JSONObject()))
            .setHeader(Exchange.HTTP_PATH, constant("[(api.endpoint.pnr.list)]"))
            .marshal(request)
            .to("https4://[(api.endpoint)]")
            .errorHandler(defaultErrorHandler()
                .maximumRedeliveries(6)
                .delayPattern("1:1000;3:2000;5:10000") 
                .maximumRedeliveryDelay(10000)
                .retryAttemptedLogLevel(LoggingLevel.WARN))
            .unmarshal(response)
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                  /*  PNRList list = (PNRList) exchange.getIn().getBody();
                    String pnr = list.getPNRInfo().stream()
                        .filter(p -> !p.getPNRId().isEmpty() && p.getPNRId() != null)
                        .map(i -> i.getPNRId() )
                        .collect(Collectors.joining("\n"));                        
                    exchange.getOut().setBody(pnr);*/
                	/*
					 *   Above peace of code commented because processing done through new JAXB component(PNRResponse)
					 */
                	PNRResponse list = (PNRResponse) exchange.getIn().getBody();
                	String pnr=	list.getPNRId().stream().filter(p->!p.isEmpty() && p!=null).map(i-> i).collect(Collectors.joining("\n"));
					exchange.getOut().setBody(pnr);
                }
            })            
            .to("file://[(api.endpoint.pnr.parse.ids)]?fileName=PNRList-${date:now:yyyyMMddHHmmss}.txt")
            .to("direct:shutdown");

        from("direct:shutdown")
            .process(new Processor(){ public void process(Exchange exchange) throws Exception { 
                latch.countDown();
            }})
            .log("File successfully move to error landing directory.");            
	}
		
}
