package com.bcd.bdu.poc;

import javax.xml.bind.JAXBContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class FileTransferRouteTest extends RouteBuilder  {

	public void configure() throws Exception {
		JAXBContext jc = JAXBContext.newInstance(
			com.bcd.bdu.model.bookingdata.db.ObjectFactory.class,
			com.bcd.bdu.model.bookingdata.db.Rail.class,
			com.bcd.bdu.model.bookingdata.db.Tour.class,
			com.bcd.bdu.model.bookingdata.db.Bus.class,
			com.bcd.bdu.model.bookingdata.db.CruiseFerry.class,
			com.bcd.bdu.model.bookingdata.db.Air.class,
			com.bcd.bdu.model.bookingdata.db.Car.class,
			com.bcd.bdu.model.bookingdata.db.Hotel.class
    		);
		JaxbDataFormat pnr = new JaxbDataFormat(jc);

 		
		// from("file://[(api.endpoint.pnr.landing.dir)]?delete=true")
		 from("file://[(api.endpoint.pnr.landing.dir)]?noop=true")
			
			.routeId("Polling PNRs for Compleat Transformation")
			.unmarshal(pnr)
		//	.process(new KeyGenerator())
			.to("direct:booking_data_to_compleat");
		 
		

		/*
		 * BookingData XML to Compleat XML
		 */
		from("direct:booking_data_to_compleat")
			.routeId("Transform BookingData to Compleat")
			.to("xslt:[(api.directbooking.xslt)]")
			.to("xslt:CompressCompleat.xslt")
			.to("file://[(api.compleat.directory)]?fileName=directbooking.xml");
		//	.to("direct:api_interpret_resp");
	

	
	}
}
