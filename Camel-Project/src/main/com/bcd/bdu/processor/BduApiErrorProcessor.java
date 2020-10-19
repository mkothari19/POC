package com.bcd.bdu.processor;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class BduApiErrorProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		 final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	        f.setTimeZone(TimeZone.getTimeZone("UTC"));
	       	String ts=f.format(new Date());
	       String enddate=null;
	       String startdate=null;
	 try {
	       String request=	exchange.getIn().getHeader("apirequest",String.class);
	     
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(request)));
			 XPathFactory xpathfactory = XPathFactory.newInstance();
		        XPath xpath = xpathfactory.newXPath();
			doc.getDocumentElement().normalize();
			XPathExpression exprenddate = xpath.compile("//PNRListRequest/DateRanges/EndDate/text()");
			XPathExpression exprstartdate = xpath.compile("//PNRListRequest/DateRanges/StartDate/text()");
	        
			Object resultenddate = exprenddate.evaluate(doc, XPathConstants.NODESET);
			Object resultstartdate = exprstartdate.evaluate(doc, XPathConstants.NODESET);
			
			
			 NodeList nodesenddate = (NodeList) resultenddate;
		        for (int i = 0; i < nodesenddate.getLength(); i++) {
		        	enddate=nodesenddate.item(i).getNodeValue();
		        }
		       NodeList nodesstartdate = (NodeList) resultstartdate;
		        for (int i = 0; i < nodesstartdate.getLength(); i++) {
		        	startdate=nodesstartdate.item(i).getNodeValue();
		        }
	   }catch(NullPointerException ex) {
		   
	   }
	       	if(startdate!=null && enddate!=null) {
		    exchange.getIn().setBody(ts+ ": "+" ApiStartdate=" +startdate+" ApiEndDate="+enddate+" "+exchange.getIn().getHeader(Exchange.HTTP_PATH)+": "+exchange.getProperty("CamelExceptionCaught")+"\r\n");
	       	}else {
	       	exchange.getIn().setBody(ts+ " : "  +exchange.getIn().getHeader(Exchange.HTTP_PATH)+": "+exchange.getProperty("CamelExceptionCaught")+"\r\n");
	 	    
	       	}	
	}

}
