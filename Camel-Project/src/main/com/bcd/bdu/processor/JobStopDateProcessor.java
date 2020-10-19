package com.bcd.bdu.processor;

import java.io.StringReader;

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

import com.bcd.bdu.util.BduRestApiUtil;

public class JobStopDateProcessor extends BduRestApiUtil implements Processor {
	private String filename=null;
	public JobStopDateProcessor(String filename) {
		this.filename=filename;
		}

	@Override
	public void process(Exchange exchange) throws Exception  {
		// TODO Auto-generated method stub
		String filepath= exchange.getContext().resolvePropertyPlaceholders("[(api.job.stop.date.path)]");
		   String request=	exchange.getIn().getHeader("apirequest",String.class);
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(request)));
			 XPathFactory xpathfactory = XPathFactory.newInstance();
		        XPath xpath = xpathfactory.newXPath();
			doc.getDocumentElement().normalize();
			XPathExpression expr = xpath.compile("//PNRListRequest/DateRanges/EndDate/text()");
	        
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			String enddate=null;
			
			 NodeList nodes = (NodeList) result;
		        for (int i = 0; i < nodes.getLength(); i++) {
		        	enddate=nodes.item(i).getNodeValue();
		        }
		        	createBduApiStartDate(filepath,filename,enddate);
				        
		
	}
	
	

}
