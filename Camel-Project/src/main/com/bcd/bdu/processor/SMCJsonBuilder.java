package com.bcd.bdu.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;

import com.bcd.bdu.helpers.BDUPnrIDGeneration;
import com.bcd.bdu.util.BduUtils;

public class SMCJsonBuilder implements Processor {

	private Map<String, String> agencyidMap = new HashMap<String, String>();
	private Map<String, String> gdsValue = new HashMap<String, String>();

	public SMCJsonBuilder(Map<String, String> agencyidMap, Map<String, String> gdsValue) {
		this.agencyidMap = agencyidMap;
		this.gdsValue = gdsValue;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
		f.setTimeZone(TimeZone.getTimeZone("UTC"));
		String ts = f.format(new Date());
		final SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		f1.setTimeZone(TimeZone.getTimeZone("UTC"));
		String timestamp = f1.format(new Date());

		String filename = exchange.getIn().getHeader("CamelFileName", String.class);

		String fileinsertts[] = filename.split("landingts");

		String body = exchange.getIn().getBody(String.class);

		JSONObject obj = XML.toJSONObject(body, true);
		JSONObject pnr = obj.getJSONObject("pnr");
		JSONTokener tokener = new JSONTokener(pnr.toString());
		JSONObject object = new JSONObject(tokener);
		object.put("DL_XmlFileNameTS", timestamp);
		// object.put("DL_XmlFileName", filename);
		object.put("DL_XmlInsertTS", fileinsertts[0]);
		object.put("DL_ZipFileName", JSONObject.NULL);
		object.put("Last_Update_DT", ts);

		String recordLocator = object.get("recordLocator").toString();
		String spnrid = object.get("sourcePNRId").toString();
		exchange.getIn().setHeader("sourcePNRId", spnrid);
		String agency_id = object.get("instance").toString();
		String source = agencyidMap.get(agency_id);

		String gds = object.get("GDS").toString();

		String updatedBookingDate = BduUtils.getInstance().dateParser(object.get("BookingDateTime").toString().trim());
		String gdsval = gdsValue.get(gds.trim());
		if (gdsval == null) {
			gdsval = gds.trim();
		}
		// UUID generation

		String pnr_id = BDUPnrIDGeneration.getInstance().createUUIDForPnr_ID(recordLocator, updatedBookingDate, gdsval);
		object.put("PNR_ID", pnr_id);
		String pnrid = BDUPnrIDGeneration.getInstance().create64BitPnrID(recordLocator, updatedBookingDate, gdsval);
		object.put("PNRid", pnrid);
		object.put("instance", source);
		object.put("BookingDateTime", updatedBookingDate);
		object.put("sourceChannel", agency_id);
		object.put("GDS", gdsval);
		filename = "smc-" + timestamp.replaceAll(" ", "-") + "Z-" + recordLocator;
		object.put("DL_XmlFileName", filename + ".xml");
		filename = "smc-" + timestamp.replaceAll(" ", "-") + "Z-" + exchange.getExchangeId() + "-" + recordLocator;
		exchange.getIn().setHeader("SMCFileName", filename);
		String jsonobj = object.toString().replaceAll("\"null\"", "null");
		exchange.getIn().setBody(jsonobj);
	}

}
