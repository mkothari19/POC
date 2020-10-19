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

public class DirectBookingJsonBuilder implements Processor {
	private Map<String, String> gdsValue = new HashMap<String, String>();

	public DirectBookingJsonBuilder(Map<String, String> gdsValue) {
		this.gdsValue = gdsValue;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String filename = exchange.getIn().getHeader("CamelFileName", String.class);
		String ts = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss.SSS");
		String time=filename.split("_")[3].replaceAll(".xml","");
		String body = exchange.getIn().getBody(String.class);
		JSONObject obj = XML.toJSONObject(body, true);
		JSONObject pnr = obj.getJSONObject("pnr");
		JSONTokener tokener = new JSONTokener(pnr.toString());
		JSONObject object = new JSONObject(tokener);
		object.put("DL_XmlFileNameTS", time);
		object.put("DL_ZipFileName", JSONObject.NULL);
		String t = object.get("BookingDateTime").toString().replaceAll("T", " ");
		object.put("BookingDateTime", t);
		object.put("Last_Update_DT", ts);
		object.put("DL_XmlInsertTS", time);
		String recordLocator = object.get("recordLocator").toString();
		String spnrid = object.get("sourcePNRId").toString();
		exchange.getIn().setHeader("sourcePNRId", spnrid);
		String gds = object.get("GDS").toString();
		// UUID generation
		String updatedBookingDate = BduUtils.getInstance().dateParser(object.get("BookingDateTime").toString().trim());
		String gdsval = gdsValue.get(gds.trim());
		if (gdsval == null) {
			gdsval = "Other";
		}
		String pnr_id = BDUPnrIDGeneration.getInstance().createUUIDForPnr_ID(recordLocator, updatedBookingDate, gdsval);

		object.put("PNR_ID", pnr_id);
		// 64 Bits PnrID
		String pnrid = BDUPnrIDGeneration.getInstance().create64BitPnrID(recordLocator, updatedBookingDate, gdsval);
		object.put("PNRid", pnrid);
		object.put("BookingDateTime", updatedBookingDate);
		object.put("GDS", gdsval);
		object.put("DL_XmlFileName", filename);

		filename = filename.replaceAll("xml", "json");
		exchange.getIn().setHeader("DBFileName", filename);
		String jsonobj = object.toString().replaceAll("\"null\"", "null");
		exchange.getIn().setBody(jsonobj);
	}

}
