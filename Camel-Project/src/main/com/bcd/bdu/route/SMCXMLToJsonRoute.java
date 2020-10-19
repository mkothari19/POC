package com.bcd.bdu.route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.SMCJsonBuilder;

public class SMCXMLToJsonRoute extends RouteBuilder {

	private Map<String,String>agencyidMap=new HashMap<String,String>();
	private Map<String,String> gdsValue=new HashMap<String,String>();
	private String parllelthread=null;
	public SMCXMLToJsonRoute(Map<String,String>agencyidMap,String parllelthread,Map<String,String> gdsValue) {
		this.agencyidMap=agencyidMap;
		this.parllelthread=parllelthread;
		this.gdsValue=gdsValue;
	}
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		
				ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(parllelthread));
		      	 from("file://[(api.stream.landing.dir)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
		  			
						.routeId("SMCXMTOJSONFileTransferRoute")
						.multicast()
						.parallelProcessing().executorService(executor)
						.doTry()
						.convertBodyTo(String.class)
						//.process(new SMCXML2JsonProcessor())
						//.marshal(xmlJsonFormat)
						.process(new SMCJsonBuilder(agencyidMap,gdsValue))
						.to("file://[(api.stream.json.landing.dir)]?fileName=${header.SMCFileName}.json")
						.doCatch(Exception.class)
						.process(new GetExceptionMessage())
						 .to("file://[(api.endpoint.smc.errorinjson.directory)]")
						 .setBody(simple("<br><b>>></b> ${date:now:yyyy-MM-dd-HH:mm}: Unable to process sourcePNRId '${header.sourcePNRId}'  with in filename ${header.CamelFileName} because of exception > ${header.BduExceptionMessage}"))
						    .transform(body().append("</br>"))
							.to("file://[(api.endpoint.smc.log)]?fileName=xmltojson_transform_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
						 .doFinally()
					     .end();
			
	}

}
