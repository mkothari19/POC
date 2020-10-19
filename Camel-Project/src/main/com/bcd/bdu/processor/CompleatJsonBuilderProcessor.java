package com.bcd.bdu.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;

import com.bcd.bdu.helpers.BDUPnrIDGeneration;
import com.bcd.bdu.util.BduUtils;

public class CompleatJsonBuilderProcessor implements Processor {

	private Map<String, String> gdsMap = null;

	public CompleatJsonBuilderProcessor(Map<String, String> gdsMap) {
		this.gdsMap = gdsMap;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
		f.setTimeZone(TimeZone.getTimeZone("UTC"));
		String ts = f.format(new Date());
		String filename = exchange.getIn().getHeader("CamelFileName", String.class);

		String fileinsertts[] = filename.split("landingts");
		String filearr[] = fileinsertts[1].split(".zip");
		String zipfilename = filearr[0];
		filename = filearr[1].replaceAll(".xml", "");
		String fileNameTS = filename.substring(0, 19);
		String body = exchange.getIn().getBody(String.class);

		JSONObject obj = XML.toJSONObject(body, true);
		JSONObject pnr = obj.getJSONObject("pnr");
		JSONTokener tokener = new JSONTokener(pnr.toString());
		JSONObject object = new JSONObject(tokener);
		object.put("DL_XmlFileNameTS", fileNameTS);
		object.put("DL_XmlFileName", filename + ".xml");
		object.put("DL_XmlInsertTS", fileinsertts[0]);
		object.put("DL_ZipFileName", zipfilename + ".zip");
		object.put("Last_Update_DT", ts);
		String recordLocator = object.get("recordLocator").toString();
		String spnrid = object.get("sourcePNRId").toString();
		exchange.getIn().setHeader("sourcePNRId", spnrid);
		String gds = object.get("GDS").toString();

		// UUID generation
		String updatedBookingDate = BduUtils.getInstance().dateParser(object.get("BookingDateTime").toString().trim());
		String gdsval = gdsMap.get(gds.trim());
		if (gdsval == null) {
			gdsval = gds.trim();
		}
		object.put("GDS", gdsval);

		String pnr_id = BDUPnrIDGeneration.getInstance().createUUIDForPnr_ID(recordLocator, updatedBookingDate, gdsval);
		object.put("PNR_ID", pnr_id);
		String pnrid = BDUPnrIDGeneration.getInstance().create64BitPnrID(recordLocator, updatedBookingDate, gdsval);
		object.put("PNRid", pnrid);
		object.put("BookingDateTime", updatedBookingDate);

		if (zipfilename.contains("EMEA")) {
			object.put("sourceChannel", "ECOMPLEAT");
			object.put("instance", "EMEA");
		} else {
			object.put("sourceChannel", "GCOMPLEAT");
			object.put("instance", "US");
		}

		filename = filename.replaceAll(" ", "-");
		exchange.getIn().setHeader("CompleatFileName", filename);
		String jsonobj = object.toString().replaceAll("\"null\"", "null");
		exchange.getIn().setBody(jsonobj);
	}

}
