package com.bcd.bdu.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.processor.RedeliveryPolicy;
import org.json.JSONObject;
import org.w3c.dom.Document;

import com.bcd.bdu.model.bookingdata.list.resp.PNRResponse;
import com.bcd.bdu.processor.BduApiErrorProcessor;
import com.bcd.bdu.processor.CredentialToken;
import com.bcd.bdu.processor.JobStopDateProcessor;
/*
 * This Route process Rick Api Data
 */
import com.bcd.bdu.util.BduUtils;

public class BookingDataRoute extends RouteBuilder {
	
	private JSONObject richjson;
	
	public BookingDataRoute(JSONObject richjson) {
		this.richjson=richjson;
	}
	public void configure() throws Exception {
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
		// JAXBContext con = JAXBContext.newInstance(PNRResponse.class);
		JAXBContext con = JAXBContext.newInstance(PNRResponse.class);

		xmlDataFormat.setContext(con);
		onException(Exception.class).redeliveryPolicy(new RedeliveryPolicy().maximumRedeliveries(3))
				.process(new BduApiErrorProcessor())
				.to("file://[(bdu.api.error.landing.directory)]?fileName=airbnb-api-error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
				.handled(true);

		from("direct:booking_data").routeId("Invoke API for PNRList").process(new CredentialToken(richjson))
				.marshal(request).setHeader(Exchange.HTTP_PATH, constant("[(api.endpoint.rich.pnr.list)]"))
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
				// .log("${body}")
				.setHeader("apirequest", simple("${body}")).to("https4://[(api.endpoint)]")
				.errorHandler(defaultErrorHandler().maximumRedeliveries(6).delayPattern("1:1000;3:2000;5:10000")
						.maximumRedeliveryDelay(10000).retryAttemptedLogLevel(LoggingLevel.WARN))
				.unmarshal(xmlDataFormat).process(new JobStopDateProcessor("airbnbjobstopdate.txt"))
				.to("direct:parse_pnr_list");

		from("direct:get_pnr_by_id").routeId("Invoke API for PNR by ID").process(new CredentialToken(richjson))
				.setHeader("dl_pnr", simple("${body}")).setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant("text/xml;charset=utf-8"))
				.setHeader(Exchange.HTTP_PATH, simple("[(api.endpoint.rich.pnr.get)]/${body}/db"))
				.to("https4://[(api.endpoint)]")
				.errorHandler(defaultErrorHandler().maximumRedeliveries(3).delayPattern("1:1000;2:2000;3:3000")
						.maximumRedeliveryDelay(3000).retryAttemptedLogLevel(LoggingLevel.WARN))
				.convertBodyTo(Document.class).choice().when().xpath("/PNR/Identification/instance = 'AIRBNB'")
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						String cdate = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss");
						exchange.getIn().setHeader("currentdate", cdate);
					}

				}).setHeader("xp_pnrid", xpath("/PNR/Identification/pnrId/text()"))
				.setHeader("record_locator", xpath("/PNR/Identification/recordLocator/text()"))
				.setHeader(Exchange.FILE_NAME,
						simple("db_${header.record_locator}_${header.xp_pnrid}_${header.currentdate}.xml"))

				.to("file://[(api.endpoint.pnr.landing.dir)]").to("file://[(api.endpoint.pnr.schemaaval.dir)]").choice()
				.when().simple("[(save.airbnb.raw.xml)] == 'true'").to("file://[(bdu.s3.airbnb.raw)]").endChoice()
				.setBody(simple("${header.dl_pnr}")).transform(body().append("\n"))
				.to("file://[(api.endpoint.pnr.parse.ids)]?fileName=pnr.dat&fileExist=Append")

				.end();

	}
}
