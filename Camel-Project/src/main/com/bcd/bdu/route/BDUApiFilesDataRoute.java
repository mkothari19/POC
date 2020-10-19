package com.bcd.bdu.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import com.bcd.bdu.processor.BduApiFilesProcessor;
import com.bcd.bdu.processor.GetExceptionMessage;

public class BDUApiFilesDataRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		onException(Exception.class).process(new GetExceptionMessage()).handled(true).choice().when()
				.simple("${header.CamelFileName} != null").to("file://[(xml.raw.error)]")
				.setBody(simple(
						"${date:now:yyyyMMddHHmmss}: BDUApiFilesDataRoute : ${header.CamelFileName} : ${header.BduExceptionMessage}"))
				.transform(body().append("\n"))
				.to("file://[(xml.raw.error.logs)]?fileName=badfileformat_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
				.otherwise()
				.setBody(
						simple("${date:now:yyyyMMddHHmmss}: BDUApiFilesDataRoute : NA : ${header.BduExceptionMessage}"))
				.transform(body().append("\n"))
				.to("file://[(xml.raw.error.logs)]?fileName=badfileformat_error__${date:now:MM-dd-yyyy}.log&fileExist=Append")
				.endChoice();
		
		/* FareLogix */
		ExecutorService executor = Executors.newFixedThreadPool(10);
		from("file://[(fl.xml.raw)]?include=.*xml&idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
				.routeId("BDUApiFLFilesDataRoute").multicast().parallelProcessing().executorService(executor)
				.convertBodyTo(String.class).choice().when()
				.xpath("boolean(/PNRViewRS/PNRIdentification/RecordLocator/text())")
				.process(new BduApiFilesProcessor("farelogix"))
				.setHeader("record_locator", xpath("/PNRViewRS/PNRIdentification/RecordLocator/text()"))
				.setHeader(Exchange.FILE_NAME,
						simple("fl_${header.record_locator}_${header.fl_pnr_id_os}_${header.fl_currentdate}.xml"))
				.to("file://[(api.endpoint.fl.pnr.landing.dir)]").choice().when()
				.simple("[(save.fl.raw.xml)] == 'true'").to("file://[(bdu.s3.fl.raw)]").end().endChoice();

		/* TravelFusion */
		from("file://[(tf.xml.raw)]?include=.*xml&idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
				.routeId("BDUApiTFFilesDataRoute").multicast().parallelProcessing().executorService(executor).doTry()
				.convertBodyTo(String.class).process(new BduApiFilesProcessor("travelfussion")).choice()
				.when(header("isprocess").isEqualTo("true")).choice().when()
				.xpath("boolean(/CommandList/GetBookingDetails/SupplierReference/text())")
				.setHeader("record_locator", xpath("/CommandList/GetBookingDetails/SupplierReference/text()"))
				.setHeader(Exchange.FILE_NAME,
						simple("tf_${header.record_locator}_${header.tf_pnr_id_os}_${header.tf_currentdate}.xml"))
				.to("file://[(api.endpoint.tf.pnr.landing.dir)]").choice().when()
				.simple("[(save.tf.raw.xml)] == 'true'").to("file://[(bdu.s3.tf.raw)]").endChoice()
				.setBody(simple("${header.dl_tf_pnr}")).transform(body().append("\n")).otherwise().when()
				.xpath("boolean(/CommandList/GetBookingDetails/Status/text())")
				.setHeader("status", xpath("/CommandList/GetBookingDetails/Status/text()"))
				.setHeader(Exchange.FILE_NAME,
						simple("tf_${header.status}_${header.tf_pnr_id_os}_${header.tf_currentdate}.xml"))
				.to("file://[(api.endpoint.tf.pnr.landing.dir)]").choice().when()
				.simple("[(save.tf.raw.xml)] == 'true'").to("file://[(bdu.s3.tf.raw)]").endChoice().end().endChoice();
		/* AirBnb */

		from("file://[(db.xml.raw)]?include=.*xml&idempotent=false&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.polling.cron)]")
				.routeId("BDUApiDBFilesDataRoute").multicast().parallelProcessing().executorService(executor)
				.convertBodyTo(String.class).choice().when().xpath("boolean(/PNR/Identification/recordLocator/text())")
				.setHeader("record_locator", xpath("/PNR/Identification/recordLocator/text()"))
				.setHeader("db_pnr_id_os", xpath("/PNR/Identification/pnrId/text()"))
				.process(new BduApiFilesProcessor("airbnb"))
				.setHeader(Exchange.FILE_NAME,
						simple("db_${header.record_locator}_${header.db_pnr_id_os}_${header.db_currentdate}.xml"))
				.to("file://[(api.endpoint.pnr.landing.dir)]").choice().when()
				.simple("[(save.airbnb.raw.xml)] == 'true'").to("file://[(bdu.s3.airbnb.raw)]").end().endChoice();

	}

}
