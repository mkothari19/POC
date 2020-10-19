package com.bcd.bdu.route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.TravelFusionJsonBuilder;

public class TravelFusionXmlToJson extends RouteBuilder {
	
	Map<String, String> gdsMap = new HashMap<String, String>();
	
	public TravelFusionXmlToJson(Map<String, String> gdsMap) {
		this.gdsMap=gdsMap;
	}

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
				ExecutorService executor = Executors.newFixedThreadPool(20);
		      	 from("file://[(api.tf.transform.directory)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
						.routeId("TravelFusionXmlToJsonRoute")
						.multicast()
						.parallelProcessing().executorService(executor)
						.doTry()
						.convertBodyTo(String.class)
						.process(new TravelFusionJsonBuilder(gdsMap))
						.choice()
						.when(header("skip").isEqualTo("false"))
						.to("file://[(api.tf.enrich.files)]?fileName=${header.TFFileName}")
						.endChoice()
					    .endDoTry()
						.doCatch(Exception.class)
						.process(new GetExceptionMessage())
					    .to("file://[(api.endpoint.tf.errorinjson.directory)]")
					    .setBody(simple("<br><b>>></b> ${date:now:yyyy-MM-dd-HH:mm}: Unable to process sourcePNRId '${header.sourcePNRId}'  with in filename ${header.CamelFileName} because of exception > ${header.BduExceptionMessage}"))
					    .transform(body().append("</br>"))
						 .to("file://[(api.endpoint.tf.log)]?fileName=xmltojson_transform_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
						 .doFinally()
					     .end();
			
	}

}
