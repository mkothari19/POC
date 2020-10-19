package com.bcd.bdu.poc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContext;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContextFactory;
*/
public class DynamicJaxbTest {

	public static void main(String[] args) /*throws FileNotFoundException, JAXBException*/ {
		// TODO Auto-generated method stub
		/*	FileInputStream xmlInputStream = new FileInputStream("D:\\landing\\data\\hs\\tsnt\\bkoff\\bdu\\directbooking\\rawxml\\directbooking-827e4f2f-9d54-41d2-be4d-2877c74ee455.xml");
		
		  FileInputStream xsdInputStream = new FileInputStream("src/main/com/bcd/bdu/schema/DBItinerarySchema_edited.xsd");
	        DynamicJAXBContext jaxbContext = 
	            DynamicJAXBContextFactory.createContextFromXSD(xsdInputStream, null, null, null);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		DynamicEntity PNR = (DynamicEntity) unmarshaller.unmarshal(xmlInputStream);
		
		DynamicEntity identification=PNR.get("identification");
		System.out.println(identification.<String>get("pnrId"));
	*/

	String s = "[<?xml version=\"1.0\" ?><PNRs><PNR num=\"1\"><PNR_ID>771833447127081</PNR_ID><CRS_CODE>S</CRS_CODE><LOCATOR>OCHEFI</LOCATOR><AGENCY_ID>200000</AGENCY_ID><ACCOUNT_ID>2619000</ACCOUNT_ID><BOOKINGAGENT>AMS</BOOKINGAGENT><BOOKINGPCC>OY7G</BOOKINGPCC><TICKETED>0</TICKETED><TICKETDATE>2016-12-07T00:00:00</TICKETDATE><EMULATEPCC>OY7G</EMULATEPCC><ACCOUNT_NUM>2251000101</ACCOUNT_NUM><BOOKINGDATE>2016-12-07T16:25:00</BOOKINGDATE></PNR><PNRNames><PNRName num = \"1\"><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.</NAMENUM><FIRSTNAME>TIMOTHY JAMES</FIRSTNAME><LASTNAME>REEVES</LASTNAME><STATINFO/></PNRName></PNRNames><PNRPNRs><PNRPNR num=\"1\"><PNR_ID>771833447127081</PNR_ID><BOOKINGDATE>2016-12-07T16:25:00</BOOKINGDATE><TICKETDATE>2016-12-07T00:00:00</TICKETDATE><FOP>CreditCard</FOP><CCNUM>478825XxXxX2363</CCNUM><CCTYPE>VI</CCTYPE><TOTALFARE>6959.06</TOTALFARE><COMMAMOUNT>0</COMMAMOUNT><COMPAREFARE>0</COMPAREFARE><FARECODE/><INTLFLIGHT>1</INTLFLIGHT><HOTELCOST>301.05</HOTELCOST><HOTELNIGHTS>4</HOTELNIGHTS><CARCOST>271.33</CARCOST><CARDAYS>4</CARDAYS><AIRPORTSUMMARY>LAX-LHR/MAN-LHR-LAX/</AIRPORTSUMMARY><CARRIERSUMMARY>AA-AA/AA-AA/</CARRIERSUMMARY><FARESUMMARY>D-D-D-D</FARESUMMARY><CLASSSUMMARY>B-B-B-B</CLASSSUMMARY><LASTUPDATE>2016-12-07T22:28:09</LASTUPDATE><CURRENCYTYPE>USD</CURRENCYTYPE></PNRPNR></PNRPNRs><PNRHotels><PNRHotel><PNR_ID>771833447127081</PNR_ID><SEGNUM>4</SEGNUM><CHAINCODE>MC</CHAINCODE><GUESTS>1</GUESTS><ROOMS>1</ROOMS><NIGHTS>3</NIGHTS><INDATE>2017-01-03T00:00:00</INDATE><OUTDATE>2017-01-06T00:00:00</OUTDATE><DAILYRATE>69.00</DAILYRATE><CURRTYPE>GBP</CURRTYPE><CITYCODE>MAN</CITYCODE><STATUSCODE>HK</STATUSCODE><CONFNUM>87285993</CONFNUM><PROPCODE>29701</PROPCODE><CRS_CODE>S</CRS_CODE></PNRHotel><PNRHotel><PNR_ID>771833447127081</PNR_ID><SEGNUM>5</SEGNUM><CHAINCODE>RD</CHAINCODE><GUESTS>1</GUESTS><ROOMS>1</ROOMS><NIGHTS>1</NIGHTS><INDATE>2017-01-06T00:00:00</INDATE><OUTDATE>2017-01-07T00:00:00</OUTDATE><DAILYRATE>94.05</DAILYRATE><CURRTYPE>GBP</CURRTYPE><CITYCODE>MAN</CITYCODE><STATUSCODE>HK</STATUSCODE><CONFNUM>MFSQGNY</CONFNUM><PROPCODE>43774</PROPCODE><CRS_CODE>S</CRS_CODE></PNRHotel></PNRHotels><PNRCars><PNRCar><PNR_ID>771833447127081</PNR_ID><SEGNUM>3</SEGNUM><COMPCODE>ZI</COMPCODE><NUMPAX>0</NUMPAX><NUMCARS>1</NUMCARS><NUMDAYS>4</NUMDAYS><PICKUPDATE>2017-01-03T14:20:00</PICKUPDATE><DROPOFFDATE>2017-01-07T07:55:00</DROPOFFDATE><DAILYRATE>53.29</DAILYRATE><CURRTYPE>GBP</CURRTYPE><PICKUPCITY>MAN</PICKUPCITY><STATUSCODE>HK</STATUSCODE><CONFNUM>08928089US2</CONFNUM><CARTYPE>IDAR</CARTYPE></PNRCar></PNRCars><PNRAirs><PNRAir><PNR_ID>771833447127081</PNR_ID><SEGNUM>1</SEGNUM><CARRIER>AA</CARRIER><FLIGHTNUM>108</FLIGHTNUM><CLASS>D</CLASS><CLASSTYPE>B</CLASSTYPE><DEPDATE>2017-01-02T17:00:00</DEPDATE><ARRDATE>2017-01-03T11:30:00</ARRDATE><DEPCITY>LAX</DEPCITY><ARRCITY>LHR</ARRCITY><STATUSCODE>HK</STATUSCODE><SEAT>12A</SEAT><FAREBASIS>YNN0UPC3</FAREBASIS><SEGTYPE>0</SEGTYPE><CODESHARE>0</CODESHARE><COG>0</COG><COGCITY/><CSOPERATOR/><STOPOVERTYPE>0</STOPOVERTYPE><FARE>0</FARE></PNRAir><PNRAir><PNR_ID>771833447127081</PNR_ID><SEGNUM>2</SEGNUM><CARRIER>AA</CARRIER><FLIGHTNUM>6627</FLIGHTNUM><CLASS>D</CLASS><CLASSTYPE>B</CLASSTYPE><DEPDATE>2017-01-03T13:15:00</DEPDATE><ARRDATE>2017-01-03T14:20:00</ARRDATE><DEPCITY>LHR</DEPCITY><ARRCITY>MAN</ARRCITY><STATUSCODE>HK</STATUSCODE><SEAT>12A</SEAT><FAREBASIS>YNN0UPC3</FAREBASIS><SEGTYPE>0</SEGTYPE><CODESHARE>1</CODESHARE><COG>0</COG><COGCITY/><CSOPERATOR>BA-6627</CSOPERATOR><STOPOVERTYPE>0</STOPOVERTYPE><FARE>0</FARE></PNRAir><PNRAir><PNR_ID>771833447127081</PNR_ID><SEGNUM>6</SEGNUM><CARRIER>AA</CARRIER><FLIGHTNUM>6618</FLIGHTNUM><CLASS>D</CLASS><CLASSTYPE>B</CLASSTYPE><DEPDATE>2017-01-07T07:55:00</DEPDATE><ARRDATE>2017-01-07T09:05:00</ARRDATE><DEPCITY>MAN</DEPCITY><ARRCITY>LHR</ARRCITY><STATUSCODE>HK</STATUSCODE><SEAT>12A</SEAT><FAREBASIS>YNN0UPC3</FAREBASIS><SEGTYPE>0</SEGTYPE><CODESHARE>1</CODESHARE><COG>0</COG><COGCITY/><CSOPERATOR>BA-6618</CSOPERATOR><STOPOVERTYPE>0</STOPOVERTYPE><FARE>0</FARE></PNRAir><PNRAir><PNR_ID>771833447127081</PNR_ID><SEGNUM>7</SEGNUM><CARRIER>AA</CARRIER><FLIGHTNUM>109</FLIGHTNUM><CLASS>D</CLASS><CLASSTYPE>B</CLASSTYPE><DEPDATE>2017-01-07T11:35:00</DEPDATE><ARRDATE>2017-01-07T15:00:00</ARRDATE><DEPCITY>LHR</DEPCITY><ARRCITY>LAX</ARRCITY><STATUSCODE>HK</STATUSCODE><SEAT>12A</SEAT><FAREBASIS>YNN0UPC3</FAREBASIS><SEGTYPE>0</SEGTYPE><CODESHARE>0</CODESHARE><COG>0</COG><COGCITY/><CSOPERATOR/><STOPOVERTYPE>0</STOPOVERTYPE><FARE>0</FARE></PNRAir></PNRAirs><PNRRemarks><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>NORMK</ABBREVIATION><PNRLINE>2251000101</PNRLINE><LABEL>CUSTOMER NUMBER</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>NORMK</ABBREVIATION><PNRLINE>TIMOTHY.REEVES.AT.NGC.COM</PNRLINE><LABEL>TSA-TRAVELER EMAIL</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>NORMK</ABBREVIATION><PNRLINE>2016-12-07T00:00:00</PNRLINE><LABEL>TICKET DATE</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>NORMK</ABBREVIATION><PNRLINE>6959.06</PNRLINE><LABEL>TOTAL FARE</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>AS</PNRLINE><LABEL>SECTOR</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>ST67KP</PNRLINE><LABEL>COST CENTER</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>EMPLOYEE</PNRLINE><LABEL>EMPLOYEE TYPE</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>B66308</PNRLINE><LABEL>MY ID</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>B66308</PNRLINE><LABEL>EMPLOYEE ID</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>REEVES/T5797151</PNRLINE><LABEL>PORTAL G8</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>03216308</PNRLINE><LABEL>PORTAL U14</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>NGC</PNRLINE><LABEL>PORTAL U15</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>TIMOTHY.REEVES</PNRLINE><LABEL>EMAIL BEFORE DOMAIN</LABEL></PNRRemark><PNRRemark><PNR_ID>771833447127081</PNR_ID><LINETYPE>5430</LINETYPE><ABBREVIATION>RI</ABBREVIATION><PNRLINE>NGC.COM</PNRLINE><LABEL>EMAIL DOMAIN</LABEL></PNRRemark></PNRRemarks><PNRContacts><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>22</ITEMCODE><ITEMTEXT>Primary Email Address</ITEMTEXT><ITEMVALUE>TIMOTHY.REEVES.AT.NGC.COM</ITEMVALUE></PNRContact><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>23</ITEMCODE><ITEMTEXT>Secondary Email Address</ITEMTEXT><ITEMVALUE>TRDRUMMER.AT.GMAIL.COM</ITEMVALUE></PNRContact><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>1</ITEMCODE><ITEMTEXT>Primary Emergency Contact Name</ITEMTEXT><ITEMVALUE>NICKKI REEVES PEAVEY</ITEMVALUE></PNRContact><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>2</ITEMCODE><ITEMTEXT>Primary Emergency Contact Phone Number</ITEMTEXT><ITEMVALUE>619-987-4356</ITEMVALUE></PNRContact><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>18</ITEMCODE><ITEMTEXT>Cell Phone Number</ITEMTEXT><ITEMVALUE>530-520-1595</ITEMVALUE></PNRContact><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>17</ITEMCODE><ITEMTEXT>Home Phone Number</ITEMTEXT><ITEMVALUE>530-520-1595</ITEMVALUE></PNRContact><PNRContact><PNR_ID>771833447127081</PNR_ID><NAMENUM>1.1</NAMENUM><ITEMCODE>19</ITEMCODE><ITEMTEXT>Work Phone Number</ITEMTEXT><ITEMVALUE>310-331-3888</ITEMVALUE></PNRContact></PNRContacts><PWR_DATA_REPL_TKTs /><PWR_DATA_REPL_HTLs /></PNRs>]";

	/*s = s.substring(s.indexOf("<PNR_ID>") + 1);
	s = s.substring(0, s.indexOf("</PNR_ID>"));*/
	
	final Pattern pattern = Pattern.compile("<PNR_ID>(.+?)</PNR_ID>", Pattern.DOTALL);
	final Matcher matcher = pattern.matcher(s);
	matcher.find();
	System.out.println(matcher.group(1));

	
	}
}
