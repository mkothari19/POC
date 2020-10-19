package com.bcd.bdu.route;

import java.util.Set;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.SchemaEvaluationProcessorFL;
import com.google.common.collect.Sets;

public class FLSchemaEvaluationRoute extends RouteBuilder {

	Set<String> schema = Sets.newHashSet();
	 
	public FLSchemaEvaluationRoute( Set<String> schema ) {
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
    			.to("file://[(api.endpoint.fl.errorinschemaeval.directory)]")
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: FLSchemaEvaluationRoute : ${header.CamelFileName} : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.endpoint.fl.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    		.otherwise()
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: FLSchemaEvaluationRoute : NA : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.endpoint.fl.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	
    	.endChoice();
		
		 from("file://[(api.endpoint.fl.pnr.schemaaval.dir)]?delete=true")
			.routeId("FLSchemaEvaluationRoute")
			.convertBodyTo(String.class)
					 .process(new SchemaEvaluationProcessorFL(schema))
			 .convertBodyTo(String.class)
				.choice()
				.when(header("isnewtag").isEqualTo("yes"))
			.setBody(simple("${header.newxmltags}"))
			.to("file://[(newtags.repository)]?fileName=fl_newtags.txt&fileExist=Append")
			.otherwise()
			.to("direct:api_interpret_resp")
			.endChoice();
	}
}