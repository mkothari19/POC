package com.bcd.bdu.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.bcd.bdu.helpers.BDUPnrIDGeneration;
import com.bcd.bdu.util.BduUtils;
import com.bcd.bdu.util.InvalidFieldException;

public class FareLogixJsonBuilderProcessor implements Processor {
	Map<String, String> citymap = new HashMap<String, String>();
	Map<String, String> gdsMap = new HashMap<String, String>();
	
	public FareLogixJsonBuilderProcessor(Map<String, String> citymap,Map<String,String>  gdsMap) {
		this.citymap = citymap;
		this.gdsMap=gdsMap;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		String filename = exchange.getIn().getHeader("CamelFileName", String.class);
		String ts = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss.SSS");
		String filearr[] = filename.split("_");
		String pnr_id_os = filearr[2];
		 String filenamets=filearr[3].replaceAll(".xml","");
		String body = exchange.getIn().getBody(String.class);

		JSONObject obj = XML.toJSONObject(body, true);
		JSONObject pnr = obj.getJSONObject("pnr");

	
		JSONTokener tokener = new JSONTokener(pnr.toString());
		JSONObject object = new JSONObject(tokener);
		object.put("DL_XmlFileNameTS", filenamets);
		object.put("DL_XmlInsertTS", filenamets);
		object.put("DL_ZipFileName", JSONObject.NULL);
		object.put("PNR_ID_OS", pnr_id_os);
		object.put("Last_Update_DT", ts);
		String recordLocator = object.get("recordLocator").toString();
		exchange.getIn().setHeader("sourcePNRId", recordLocator);
		String gds=object.get("GDS").toString();
		 String gdsval=gdsMap.get(gds.trim());
		   if(gdsval==null) {
		 	  gdsval=gds.trim();
		   }
		   object.put("GDS", gdsval); 
		String vendoperator = object.get("PNRid").toString();
		if (vendoperator.equals("null")) {
			throw new InvalidFieldException("Source Name, AirlineCode and TicketInfo Source are empty");
		}
		vendoperator = (String) vendoperator.subSequence(0, 2);
		String updatedBookingDate = BduUtils.getInstance().dateParser(object.get("BookingDateTime").toString().trim());
		object.put("BookingDateTime", updatedBookingDate);

		// UUID generation
		String pnr_id = BDUPnrIDGeneration.getInstance().createUUIDForPnr_ID(recordLocator, updatedBookingDate,
				vendoperator);
		object.put("PNR_ID", pnr_id);
		// 64 Bits PnrID
		String pnrid = BDUPnrIDGeneration.getInstance().create64BitPnrID(recordLocator, updatedBookingDate,
				vendoperator);
		object.put("PNRid", pnrid);

		if (!"null".equals(object.get("intlFlight"))) {
			if (object.get("intlFlight").toString().contains("/")) {
				String citycd[] = object.get("intlFlight").toString().split("/")[0].split("-");
				if (citymap.get(citycd[0].toLowerCase()) != null && citymap.get(citycd[1].toLowerCase()) != null) {
					String fromcountry = citymap.get(citycd[0].toLowerCase()).split("=")[1];
					String tocountry = citymap.get(citycd[1].toLowerCase()).split("=")[1];
					if (fromcountry.toLowerCase().equals(tocountry.toLowerCase())) {
						object.put("intlFlight", "D");
					} else {
						object.put("intlFlight", "I");
					}
				}
			} else {
				String citycd[] = object.get("intlFlight").toString().split("-");
				if (citymap.get(citycd[0].toLowerCase()) != null && citymap.get(citycd[1].toLowerCase()) != null) {
					String fromcountry = citymap.get(citycd[0].toLowerCase()).split("=")[1];
					String tocountry = citymap.get(citycd[1].toLowerCase()).split("=")[1];
					if (fromcountry.toLowerCase().equals(tocountry.toLowerCase())) {
						object.put("intlFlight", "D");
					} else {
						object.put("intlFlight", "I");
					}
				}
			}
		}

		Object segjsonobject = new JSONParser().parse(object.get("Segments").toString());
		org.json.simple.JSONObject segjo = (org.json.simple.JSONObject) segjsonobject;
		if (object.get("status").toString().equals("VOIDED") || object.get("status").toString().equals("REFUNDED")) {
			object.put("Segments", new JSONObject(updateSegment(segjo, citymap)));
		}
		object.put("DL_XmlFileName", filename);
		
		exchange.getIn().setHeader("FLFileName", filename.replaceAll("xml", "json"));
		String jsonobj = object.toString().replaceAll("\"null\"", "null");
		exchange.getIn().setBody(jsonobj);
	}

	private String updateSegment(org.json.simple.JSONObject obj, Map<String, String> citymap) {
		Map<String, Object> segments = (Map<String, Object>) obj;
		JSONArray segment = (JSONArray) segments.get("Segment");
		for (int i = 0; i < segment.size(); i++) {
			Map<String, String> seg = (Map<String, String>) segment.get(i);
			if (seg.containsKey("segType") && seg.get("segType").equals("Air")) {
				if (!"null".equals(seg.get("startCityName"))) {
					if (citymap.get(seg.get("startCityName").toLowerCase()) != null) {
						String startcityname = citymap.get(seg.get("startCityName").toLowerCase()).split("=")[0];
						seg.put("startCityName", startcityname);
					} else {
						seg.put("startCityName", seg.get("startCityName"));
					}
				}
				if (!"null".equals(seg.get("endCityName"))) {
					if (citymap.get(seg.get("endCityName").toLowerCase()) != null) {
						String endcityname = citymap.get(seg.get("endCityName").toLowerCase()).split("=")[0];
						seg.put("endCityName", endcityname);
					} else {
						seg.put("endCityName", seg.get("endCityName"));
					}
				}

			}

		}
		String updatedseg = "{" + "\"Segment\"" + ":" + segment + "}";
		return updatedseg;

	}

}
