package com.bcd.bdu.route;

import java.util.Set;

import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.SchemaEvaluationProcessorTF;
import com.google.common.collect.Sets;

public class TFSchemaEvaluationRoute extends RouteBuilder {

	Set<String> schema = Sets.newHashSet();
	 
	public TFSchemaEvaluationRoute( Set<String> schema ) {
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
    			.to("file://[(api.endpoint.tf.errorinschemaeval.directory)]")
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: TFSchemaEvaluationRoute : ${header.CamelFileName} : ${header.BduExceptionMessage}\n"))
    			.transform(body().append("\n"))
    			.to("file://[(api.endpoint.tf.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    		.otherwise()
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: TFSchemaEvaluationRoute : NA : ${header.BduExceptionMessage}\n"))
    			.transform(body().append("\n"))
    			.to("file://[(api.endpoint.tf.log)]?fileName=schemaevaluation_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    	
    	.endChoice();
		
		 from("file://[(api.endpoint.tf.pnr.schemaaval.dir)]?delete=true")
			.routeId("TFSchemaEvaluationRoute")
			.convertBodyTo(String.class)
					 .process(new SchemaEvaluationProcessorTF(schema))
			 .convertBodyTo(String.class)
				.choice()
				.when(header("isnewtag").isEqualTo("yes"))
			.setBody(simple("${header.newxmltags}"))
			.to("file://[(newtags.repository)]?fileName=tf_newtags.txt&fileExist=Append")
			.otherwise()
			.to("direct:api_interpret_resp")
			.endChoice();
	}
}