package com.bcd.bdu.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.BduApiCallProcessor;
import com.bcd.bdu.processor.FilterPNRListStrategy;
import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.RefereshCacheProcessor;
import com.bcd.bdu.processor.SchemaEvaluationEmailNotification;


public class PollingDataRoute extends RouteBuilder {
	
	private String airbnbpastdate=null;
	private String tfpastdate=null;
	private String flpastdate=null;
	Map<String,String> citymap=new HashMap<String,String>();
	
	public PollingDataRoute(String airbnbpastdate,String tfpastdate,String flpastdate,Map<String,String> citymap) {
		this.airbnbpastdate=airbnbpastdate;
		this.tfpastdate=tfpastdate;
		this.flpastdate=flpastdate;
		this.citymap=citymap;
	}

	public void configure() throws Exception {
		 
    	onException(Exception.class)
    	.process(new GetExceptionMessage())
    	.handled(true)
    	.choice()
    		.when()
    			.simple("${header.CamelFileName} != null")
    			.to("file://[(bdu.api.error.landing.directory)]")
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: PollingDataRoute : ${header.CamelFileName} : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(bdu.api.error.landing.directory)]?fileName=jobcrown_error-${date:now:MM-dd-yyyy}.log&fileExist=Append")
    		.otherwise()
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: PollingDataRoute : NA : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(bdu.api.error.landing.directory)]?fileName=jobcrown_error-${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	.endChoice();    	    	

		from("quartz2://myGroup/bduApiClientTimer?cron=[(api.polling.cron)]")
			.routeId("DirectBookingPoll")
			.noAutoStartup()
			.process(new BduApiCallProcessor(airbnbpastdate,"airbnbjobstopdate.txt"))
			.to("direct:booking_data");
			
		
		from("quartz2://myGroup/bduTFApiClientTimer?cron=[(api.tf.batch.cron)]")
		.routeId("TFDirectBookingPoll")
		.noAutoStartup()
		.process(new BduApiCallProcessor(tfpastdate,"tfjobstopdate.txt"))
		.to("direct:tf_booking_data");
		
		from("quartz2://myGroup/bduFLApiClientTimer?cron=[(api.tf.batch.cron)]")
		.routeId("FLDirectBookingPoll")
		.noAutoStartup()
		.process(new BduApiCallProcessor(flpastdate,"fljobstopdate.txt"))
		.to("direct:fl_booking_data");
		
		
		
		from("quartz2://myGroup/bduCompleatSEEmailNotification?cron=[(schemaEval.email.notification)]")
		.routeId("CompleatEmailNotification")
		.noAutoStartup()
		.process(new SchemaEvaluationEmailNotification("Compleat"))
		.setBody(simple("${header.compleatnewtags}"))
		.to("file://[(newtags.repository)]?fileName=compleat_newtags.txt&fileExist=Override");
		
		from("quartz2://myGroup/bduDBSEEmailNotification?cron=[(schemaEval.email.notification)]")
		.routeId("DBEmailNotification")
		.noAutoStartup()
		.process(new SchemaEvaluationEmailNotification("DirectBooking"))
		.setBody(simple("${header.dbnewtags}"))
		.to("file://[(newtags.repository)]?fileName=directbooking_newtags.txt&fileExist=Override");
		
		from("quartz2://myGroup/bduSMCSEEmailNotification?cron=[(schemaEval.email.notification)]")
		.routeId("SMCEmailNotification")
		.noAutoStartup()
		.process(new SchemaEvaluationEmailNotification("SMC"))
		.setBody(simple("${header.smcnewtags}"))
		.to("file://[(newtags.repository)]?fileName=smc_newtags.txt&fileExist=Override");
		
		from("quartz2://myGroup/bduTFSEEmailNotification?cron=[(schemaEval.email.notification)]")
		.routeId("TFEmailNotification")
		.noAutoStartup()
		.process(new SchemaEvaluationEmailNotification("Travelfusion"))
		.setBody(simple("${header.tfnewtags}"))
		.to("file://[(newtags.repository)]?fileName=tf_newtags.txt&fileExist=Override");
		
		from("quartz2://myGroup/bduFLSEEmailNotification?cron=[(schemaEval.email.notification)]")
		.routeId("FLEmailNotification")
		.noAutoStartup()
		.process(new SchemaEvaluationEmailNotification("FareLogix"))
		.setBody(simple("${header.flnewtags}"))
		.to("file://[(newtags.repository)]?fileName=fl_newtags.txt&fileExist=Override");
		
	
		from("file://[(api.endpoint.pnr.parse.ids)]?delete=true&exclude=pnr.dat")
			.pollEnrich("file://[(api.endpoint.pnr.parse.ids)]?noop=true&fileName=pnr.dat"
				,10000,new FilterPNRListStrategy())  	    	
    	.to("direct:poll_pnr");
		
		from("file://[(api.endpoint.tf.pnr.parse.ids)]?delete=true&exclude=pnr.dat")
		.pollEnrich("file://[(api.endpoint.tf.pnr.parse.ids)]?noop=true&fileName=pnr.dat"
			,10000,new FilterPNRListStrategy())  	    	
	.to("direct:tf_poll_pnr");
		
		from("file://[(api.endpoint.fl.pnr.parse.ids)]?delete=true&exclude=pnr.dat")
		.pollEnrich("file://[(api.endpoint.fl.pnr.parse.ids)]?noop=true&fileName=pnr.dat"
			,10000,new FilterPNRListStrategy())  	    	
	.to("direct:fl_poll_pnr");

		from("direct:poll_pnr")
      .routeId("Polling PNR by ID")
      .split(body().tokenize("\n"))
      .streaming().parallelProcessing()
      .to("direct:get_pnr_by_id");
		
		from("direct:tf_poll_pnr")
	      .routeId("Polling TF PNR by ID")
	      .split(body().tokenize("\n"))
	      .streaming().parallelProcessing()
	      .to("direct:get_tf_pnr_by_id");
		
		from("direct:fl_poll_pnr")
	      .routeId("Polling FL PNR by ID")
	      .split(body().tokenize("\n"))
	      .streaming().parallelProcessing()
	      .to("direct:get_fl_pnr_by_id");
		
		from("quartz2://myGroup/updatecitycache?cron=[(bdu.city.cache.cron)]")
		.routeId("UpdateCityCache")
		.noAutoStartup()
		.process(new RefereshCacheProcessor(citymap))
		.to("direct:notification_resp");
	     
	}
}
