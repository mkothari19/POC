package com.bcd.bdu.route;

import java.util.Set;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.SchemaEvaluationProcessorCompleat;
import com.google.common.collect.Sets;

public class CompleatSchemaEvaluationRoute extends RouteBuilder {

	 Set<String> schema = Sets.newHashSet();

	public CompleatSchemaEvaluationRoute( Set<String> schema ) {
		this.schema=schema;
	
	}
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		
		onException(Exception.class)
    	.process(new GetExceptionMessage())
    	.handled(true)
    	.choice()
    		.when()
    			.simple("${header.CamelFileName} != null")
    			.to("file://[(api.complete.compleat.errorinschemaeval.directory)]?fileName=${header.CamelFileName}")
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: CompleatSchemaEvaluationRoute : ${header.CamelFileName} : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.complete.compleat.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    		.otherwise()
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: CompleatSchemaEvaluationRoute : NA : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.complete.compleat.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	.endChoice(); 
		     	
		from("file://[(api.endpoint.compleat.pnr.schemaeval)]?sortBy=file:name&idempotent=[(duplicate.file.nerver.process)]&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
		   		.routeId("CompleatSchemaEvaluationRoute")
		   			.convertBodyTo(String.class)
					 .process(new SchemaEvaluationProcessorCompleat(schema))
					.convertBodyTo(String.class)
						.choice()
						.when(header("isnewtag").isEqualTo("yes"))
						//.to("file://[(api.complete.compleat.unprocessed)]?fileName=${header.CamelFileName}")
						.setBody(simple("${header.newxmltags}"))
						.to("file://[(newtags.repository)]?fileName=compleat_newtags.txt&fileExist=Append")
		    			//.to("xslt:file://[(xslt.path)]/[(api.compleat.xslt)]")
						//.to("file://[(api.complete.compleat.directory)]?fileName=${header.CamelFileName}")
						.otherwise()
						//.to("xslt:file://[(xslt.path)]/[(api.compleat.xslt)]")
						//.to("file://[(api.complete.compleat.directory)]?fileName=${header.CamelFileName}")
						.endChoice();
				          		 			
				
			}

}
