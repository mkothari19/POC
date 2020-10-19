package com.bcd.bdu.route;

import java.util.Set;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.SchemaEvaluationProcessorSMC;
import com.google.common.collect.Sets;

public class SMCSchemaEvaluation  extends RouteBuilder {

	 Set<String> schema = Sets.newHashSet();
	
	public SMCSchemaEvaluation( Set<String> schema ) {
		this.schema=schema;
		
	}
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		onException(Exception.class)
    	.process(new GetExceptionMessage())
    	.handled(true)
    	.choice()
    		.when()
    			.simple("${header.CamelFileName} != null")
    			.to("file://[(api.endpoint.smc.errorinschemaeval.directory)]")
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: SMCSchemaEvaluationRoute : ${header.CamelFileName} : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.endpoint.smc.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    		.otherwise()
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: SMCSchemaEvaluationRoute : NA : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.endpoint.smc.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	.endChoice();
		
		from("file://[(api.endpoint.pnr.smc.schemaaval.dir)]?sortBy=file:name&idempotent=[(duplicate.file.nerver.process)]&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
   		.routeId("SMCSchemaEvaluationRoute")
			.convertBodyTo(String.class)
			 .process(new SchemaEvaluationProcessorSMC(schema))
			.convertBodyTo(String.class)
				.choice()
				.when(header("isnewtag").isEqualTo("yes"))
				//.to("file://[(api.stream.smc.unprocessed)]?fileName=${header.CamelFileName}")
				.setBody(simple("${header.newxmltags}"))
				.to("file://[(newtags.repository)]?fileName=smc_newtags.txt&fileExist=Append")
			   .otherwise()
				//.to("xslt:file://[(xslt.path)]/[(api.smc.xslt)]")
				//.log("${body}")
				//.to("file://[(api.stream.landing.dir)]?fileName=${header.CamelFileName}")
				.endChoice();
	}
}
