package com.bcd.bdu.route;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.json.JSONObject;

import com.bcd.bdu.processor.CredentialToken;
import com.bcd.bdu.processor.DataEnrichmentProcessor;
import com.bcd.bdu.processor.GetExceptionMessage;

/*
 *  Data Enrichment Route
 */
public class DataEnrichmentRoute extends RouteBuilder {

private JSONObject rawjson;
private JSONObject richjson;
	
	public DataEnrichmentRoute(JSONObject richjson,JSONObject rawjson) {
		this.richjson=richjson;
		this.rawjson=rawjson;
	}
	private ConcurrentHashMap<String, String> olddata = new ConcurrentHashMap<String, String>();

	@Override
	public void configure() throws Exception {

		onException(Exception.class).maximumRedeliveries(5).redeliveryDelay(1000).handled(true)
				.process(new GetExceptionMessage()).choice().when().simple("${header.FLFileName} != null")
				.to("file://[(api.fl.json.directory)]")
				.setBody(simple(
						"<br>${date:now:yyyy-MM-dd HH:mm:ss}:FareLogix PNR_ID_OS '${header.pnr_id_os}' ${header.FLFileName}  ${header.BduExceptionMessage}"))
				.transform(body().append("</br>"))
				.to("file://[(api.endpoint.fl.log)]?fileName=enrichment_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
				.when().simple("${header.TFFileName} != null").to("file://[(api.tf.json.directory)]")
				.setBody(simple(
						"<br>${date:now:yyyy-MM-dd HH:mm:ss}: TravelFusion PNR_ID_OS '${header.pnr_id_os}' ${header.TFFileName}  ${header.BduExceptionMessage}"))
				.transform(body().append("</br>"))
				.to("file://[(api.endpoint.tf.log)]?fileName=enrichment_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
				.when().simple("${header.DBFileName} != null").to("file://[(api.db.json.directory)]")
				.setBody(simple(
						"<br>${date:now:yyyy-MM-dd HH:mm:ss}: AirBnb PNR_ID_OS '${header.pnr_id_os}' ${header.DBFileName}  ${header.BduExceptionMessage}"))
				.transform(body().append("</br>"))
				.to("file://[(api.endpoint.directbooking.log)]?fileName=enrichment_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
				.otherwise().setBody(simple("<br>${date:now:yyyy-MM-dd HH:mm:ss}:   ${header.BduExceptionMessage}"))
				.transform(body().append("</br>"))
				.to("file://[(bdu.api.error.landing.directory)]?fileName=enrichment_error_${date:now:yyyy-MM-dd}.log&fileExist=Append");

		from("file://[(api.fl.enrich.files)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
				.routeId("InvokeFLApiFLRoute").process(new CredentialToken(richjson))
				.setHeader("pnr_id_os", jsonpath("PNR_ID_OS", String.class)).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						olddata.put(exchange.getIn().getHeader("pnr_id_os", String.class),
								exchange.getIn().getBody(String.class));
						exchange.getIn().setHeader("FLFileName", exchange.getIn().getHeader("CamelFileName"));
					}
				}).setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/xml;charset=utf-8"))
				.setHeader(Exchange.HTTP_PATH, simple("[(api.endpoint.rich.pnr.get)]/${header.pnr_id_os}/db"))
				.to("https4://[(api.endpoint)]").convertBodyTo(String.class)
				.process(new DataEnrichmentProcessor(olddata)).to("file://[(api.fl.json.directory)]");

		from("file://[(api.tf.enrich.files)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
				.routeId("InvokeTFApiFLRoute").process(new CredentialToken(richjson))
				.setHeader("pnr_id_os", jsonpath("PNR_ID_OS", String.class)).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						olddata.put(exchange.getIn().getHeader("pnr_id_os", String.class),
								exchange.getIn().getBody(String.class));
						exchange.getIn().setHeader("TFFileName", exchange.getIn().getHeader("CamelFileName"));

					}
				}).setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/xml;charset=utf-8"))
				.setHeader(Exchange.HTTP_PATH, simple("[(api.endpoint.rich.pnr.get)]/${header.pnr_id_os}/db"))
				.to("https4://[(api.endpoint)]").convertBodyTo(String.class)
				.process(new DataEnrichmentProcessor(olddata)).to("file://[(api.tf.json.directory)]");

		from("file://[(api.directbooking.enrich.files)]?idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
				.routeId("InvokeRawApiFLRoute").process(new CredentialToken(rawjson))
				.setHeader("pnr_id_os", jsonpath("PNR_ID_OS", String.class)).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						olddata.put(exchange.getIn().getHeader("pnr_id_os", String.class),
								exchange.getIn().getBody(String.class));
						exchange.getIn().setHeader("DBFileName", exchange.getIn().getHeader("CamelFileName"));

					}
				}).setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json;charset=utf-8"))
				.setHeader(Exchange.HTTP_PATH, simple("[(api.endpoint.raw.pnr.get)]/${header.pnr_id_os}/db"))
				.to("https4://[(api.endpoint)]").convertBodyTo(String.class)
				.process(new DataEnrichmentProcessor(olddata)).to("file://[(api.db.json.directory)]");

	}

}
