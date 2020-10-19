package com.bcd.bdu.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.processor.RedeliveryPolicy;
import org.json.JSONObject;

import com.bcd.bdu.model.bookingdata.list.resp.PNRResponse;
import com.bcd.bdu.processor.BduApiErrorProcessor;
import com.bcd.bdu.processor.CredentialToken;
import com.bcd.bdu.processor.FareLogixJsonBuilderProcessor;
import com.bcd.bdu.processor.GetExceptionMessage;
import com.bcd.bdu.processor.JobStopDateProcessor;
import com.bcd.bdu.util.BduUtils;

/*
 * This Route process Raw Api Data
 */
public class TravelFusionDataRoute extends RouteBuilder {

private JSONObject rawjson;
	
	public TravelFusionDataRoute(JSONObject rawjson) {
		this.rawjson=rawjson;
	}
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		JaxbDataFormat request = new JaxbDataFormat("com.bcd.bdu.model.bookingdata.list.req");
		// JaxbDataFormat response = new
		// JaxbDataFormat("com.bcd.bdu.model.bookingdata.list.resp");
		/*
		 *
		 * Introduce new JAXB component(PNRResponse) for webservice return PNR ID
		 * because existing(PNRList) class not mapped with webservice response
		 * 
		 */
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(PNRResponse.class);
		xmlDataFormat.setContext(con);
		onException(Exception.class).redeliveryPolicy(new RedeliveryPolicy().maximumRedeliveries(1))
				.process(new BduApiErrorProcessor())
				.to("file://[(bdu.api.error.landing.directory)]?fileName=travelfusion-api-error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
				.handled(true);

		from("direct:tf_booking_data").routeId("Invoke API for TF PNRList").process(new CredentialToken(rawjson))
				.marshal(request).setHeader(Exchange.HTTP_PATH, constant("[(api.endpoint.raw.pnr.list)]"))
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
				// .log("${body}")
				.setHeader("apirequest", simple("${body}")).to("https4://[(api.endpoint)]")
				.errorHandler(defaultErrorHandler().maximumRedeliveries(6).delayPattern("1:1000;3:2000;5:10000")
						.maximumRedeliveryDelay(10000).retryAttemptedLogLevel(LoggingLevel.WARN))
				.unmarshal(xmlDataFormat).process(new JobStopDateProcessor("tfjobstopdate.txt"))
				.to("direct:parse_tf_pnr_list");

		from("direct:get_tf_pnr_by_id").routeId("Invoke  API for TF PNR by ID").process(new CredentialToken(rawjson))
				.setHeader("dl_tf_pnr", simple("${body}")).setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant("text/xml;charset=utf-8"))
				.setHeader(Exchange.HTTP_PATH, simple("[(api.endpoint.raw.pnr.get)]/${body}/db"))
				.to("https4://[(api.endpoint)]")
				.errorHandler(defaultErrorHandler().maximumRedeliveries(3).delayPattern("1:1000;2:2000;3:3000")
						.maximumRedeliveryDelay(3000).retryAttemptedLogLevel(LoggingLevel.WARN))
				.setHeader("tfdatasource", constant("travelfussion")).convertBodyTo(String.class)
				// .marshal()
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						String payload = exchange.getIn().getBody(String.class);
						String isprocess = "false";
						if (payload.charAt(0) == '<') {
							isprocess = "true";
						}
						String cdate = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss");
						exchange.getIn().setHeader("currentdate", cdate);
						exchange.getIn().setHeader("isprocess", isprocess);
					}

				}).choice().when(header("isprocess").isEqualTo("true")).choice()
				// Travel Fusion
				
				.when().xpath("boolean(/CommandList/GetBookingDetails/SupplierReference/text())")
				.setHeader("record_locator", xpath("/CommandList/GetBookingDetails/SupplierReference/text()"))
				.setHeader(Exchange.FILE_NAME,
						simple("tf_${header.record_locator}_${header.dl_tf_pnr}_${header.currentdate}.xml"))
				.to("file://[(api.endpoint.tf.pnr.landing.dir)]").to("file://[(api.endpoint.tf.pnr.schemaaval.dir)]")
				.choice().when().simple("[(save.tf.raw.xml)] == 'true'").to("file://[(bdu.s3.tf.raw)]").endChoice()
				.setBody(simple("${header.dl_tf_pnr}")).transform(body().append("\n"))
				.to("file://[(api.endpoint.tf.pnr.parse.ids)]?fileName=pnr.dat&fileExist=Append")
                .otherwise()
                .when().xpath("boolean(/CommandList/GetBookingDetails/Status/text())")
                .setHeader("status", xpath("/CommandList/GetBookingDetails/Status/text()"))
			    .setHeader(Exchange.FILE_NAME,
					simple("tf_${header.status}_${header.dl_tf_pnr}_${header.currentdate}.xml"))
			.to("file://[(api.endpoint.tf.pnr.landing.dir)]").to("file://[(api.endpoint.tf.pnr.schemaaval.dir)]")
			.choice().when().simple("[(save.tf.raw.xml)] == 'true'").to("file://[(bdu.s3.tf.raw)]").endChoice()
			.setBody(simple("${header.dl_tf_pnr}")).transform(body().append("\n"))
			.to("file://[(api.endpoint.tf.pnr.parse.ids)]?fileName=pnr.dat&fileExist=Append")
				.end().endChoice();
		
		
		/*ExecutorService executor = Executors.newFixedThreadPool(10);
		 from("file://[(tf.xml.raw)]?include=.*xml&idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
			.routeId("TFXmlFileProcessing")
			.multicast()
			.parallelProcessing().executorService(executor)
			.doTry()
			.convertBodyTo(String.class)
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					String filename=exchange.getIn().getHeader("CamelFileName",String.class);
					String filearr[] = filename.split("_");

					String pnr_id_os = filearr[2];
					String cdate = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss");
					exchange.getIn().setHeader("currentdate", cdate);
					exchange.getIn().setHeader("isprocess", "true");
					exchange.getIn().setHeader("dl_tf_pnr", pnr_id_os);
					
				}

			}).choice().when(header("isprocess").isEqualTo("true")).choice()
			.when().xpath("boolean(/CommandList/GetBookingDetails/SupplierReference/text())")
			.setHeader("record_locator", xpath("/CommandList/GetBookingDetails/SupplierReference/text()"))
			.setHeader(Exchange.FILE_NAME,
					simple("tf_${header.record_locator}_${header.dl_tf_pnr}_${header.currentdate}.xml"))
			.to("file://[(api.endpoint.tf.pnr.landing.dir)]").to("file://[(api.endpoint.tf.pnr.schemaaval.dir)]")
			.choice().when().simple("[(save.tf.raw.xml)] == 'true'").to("file://[(bdu.s3.tf.raw)]").endChoice()
			.setBody(simple("${header.dl_tf_pnr}")).transform(body().append("\n"))
			.otherwise()
            .when().xpath("boolean(/CommandList/GetBookingDetails/Status/text())")
            .setHeader("status", xpath("/CommandList/GetBookingDetails/Status/text()"))
		    .setHeader(Exchange.FILE_NAME,
				simple("tf_${header.status}_${header.dl_tf_pnr}_${header.currentdate}.xml"))
		.to("file://[(api.endpoint.tf.pnr.landing.dir)]").to("file://[(api.endpoint.tf.pnr.schemaaval.dir)]")
		.choice().when().simple("[(save.tf.raw.xml)] == 'true'").to("file://[(bdu.s3.tf.raw)]").endChoice()
			.end().endChoice();*/
			

	}

}
