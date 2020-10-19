package com.bcd.bdu.processor;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

public class TravelFusionJsonBuilder implements Processor {

	Map<String, String> gdsMap = new HashMap<String, String>();

	public TravelFusionJsonBuilder(Map<String, String> gdsMap) {
		this.gdsMap = gdsMap;
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
		object.put("Last_Update_DT", ts);
		object.put("PNR_ID_OS", pnr_id_os);
		String recordLocator = object.get("recordLocator").toString();
		String spnrid = object.get("sourcePNRId").toString();
		exchange.getIn().setHeader("sourcePNRId", spnrid);
		String status = object.get("status").toString();
		String vendoperator = object.get("PNRid").toString();
		String updatedBookingDate = BduUtils.getInstance().dateParser(object.get("BookingDateTime").toString().trim());

		if (("failed".equals(status.toLowerCase()) || "unconfirmed".equals(status.toLowerCase()))) {
			exchange.getIn().setHeader("skip", "true");
		} else {

			String gds = object.get("GDS").toString();
			String gdsval = gdsMap.get(gds.trim());
			if (gdsval == null) {
				gdsval = gds.trim();
			}
			object.put("GDS", gdsval);
			// UUID generation

			String pnr_id = BDUPnrIDGeneration.getInstance().createUUIDForPnr_ID(recordLocator, updatedBookingDate,
					vendoperator);
			object.put("PNR_ID", pnr_id);
			// 64 Bits PnrID
			String pnrid = BDUPnrIDGeneration.getInstance().create64BitPnrID(recordLocator, updatedBookingDate,
					vendoperator);
			object.put("PNRid", pnrid);

			// Format booking date in format yyyy-MM-dd HH:mm:ss
			object.put("BookingDateTime", updatedBookingDate);
			object.put("DL_XmlFileName", filename);
			exchange.getIn().setHeader("TFFileName", filename.replaceAll("xml", "json"));

			Object segjsonobject = new JSONParser().parse(object.get("Segments").toString());
			org.json.simple.JSONObject segjo = (org.json.simple.JSONObject) segjsonobject;
			object.put("Segments", new JSONObject(getSegmentNumber(segjo)));
			Object travellerjsonobject = new JSONParser().parse(object.get("Travelers").toString());
			org.json.simple.JSONObject travelerjo = (org.json.simple.JSONObject) travellerjsonobject;
			object.put("Travelers", new JSONObject(getSegmentNumberForSeat(travelerjo)));

			String updatejson = updateStoredFarePassenger(object.toString());
			String jsonobj = updatejson.replaceAll("\"null\"", "null");

			exchange.getIn().setBody(jsonobj);
			exchange.getIn().setHeader("skip", "false");
		}
	}

	/*
	 * Get Segment number from startdate
	 */
	private String getSegmentNumber(org.json.simple.JSONObject obj) throws ParseException {
		Map<String, Object> segments = (Map<String, Object>) obj;
		JSONArray segment = (JSONArray) segments.get("Segment");
		TreeSet<Date> tempobj = new TreeSet<Date>();
		for (int i = 0; i < segment.size(); i++) {
			Map<String, String> seg = (Map<String, String>) segment.get(i);
			if (seg.containsKey("segType") && seg.get("segType").equals("Air")) {

				tempobj.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(seg.get("startDateTime")));

			}

		}
		for (int i = 0; i < segment.size(); i++) {
			Map<String, String> seg = (Map<String, String>) segment.get(i);
			if (seg.containsKey("segType") && seg.get("segType").equals("Air")) {

				seg.put("segmentNumber",
						Integer.toString(tempobj
								.headSet(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(seg.get("startDateTime")))
								.size() + 1));
			}

		}
		String updatedseg = "{" + "\"Segment\"" + ":" + segment + "}";

		return updatedseg;
	}

	/*
	 * Get segmentnumber with in seat
	 */
	private String getSegmentNumberForSeat(org.json.simple.JSONObject obj) throws ParseException {
		Map<String, Object> travelers = (Map<String, Object>) obj;
		Map<String, Object> traveler = (Map<String, Object>) travelers.get("Traveler");
		Map<String, Object> seats = (Map<String, Object>) traveler.get("Seats");
		Map<String, String> seg = new HashMap<String, String>();
		int count = 0;
		if (seats.get("Seat") instanceof JSONArray) {
			JSONArray seat = (JSONArray) seats.get("Seat");
			for (int i = 0; i < seat.size(); i++) {
				seg = (Map<String, String>) seat.get(i);
				if (seg.containsKey("segmentNumber")) {
					seg.put("segmentNumber", Integer.toString(count + 1));
					count++;
				}
			}
		} else {
			org.json.simple.JSONObject seat = (org.json.simple.JSONObject) seats.get("Seat");
			seg = (Map<String, String>) seat;
			seg.put("segmentNumber", "1");
		}

		String updatedseg = "{" + "\"Traveler\"" + ":" + traveler + "}";

		return updatedseg;
	}

	// method use to Update StoredFare Passenger
	private static String updateStoredFarePassenger(String jsonstring)
			throws ParseException, org.json.simple.parser.ParseException {
		final String delimeter = ",";
		List<String> refundTaxBreakdownAmount = new ArrayList<String>();
		float refundTaxTotal = 0;
		List<String> refundTaxBreakdownCode = new ArrayList<String>();
		Set<String> refundTaxBreakdownCurrency = new HashSet<String>();
		List<String> refundTaxBreakdownDescription = new ArrayList<String>();
		Object obj = new JSONParser().parse(jsonstring);
		org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
		Map<String, Object> storedFares = (Map<String, Object>) jo.get("StoredFares");
		Map<String, Object> storedFare = (Map<String, Object>) storedFares.get("StoredFare");
		Map<String, Object> storedFarePassengers = (Map<String, Object>) storedFare.get("StoredFarePassengers");
		Map<String, Object> storedFarePassenger = (Map<String, Object>) storedFarePassengers.get("StoredFarePassenger");
		DecimalFormat df = new DecimalFormat("#.##");
		if ((storedFarePassenger.get("RefundTaxBreakdownDescription").equals("")
				|| storedFarePassenger.get("RefundTaxBreakdownDescription") == null)
				&& storedFarePassenger.containsKey("RefundTaxBreakdownDescription"))
			return jo.toString();

		for (String str : storedFarePassenger.get("RefundTaxBreakdownDescription").toString().split(";")) {
			String[] array = str.trim().split(":");
			String tempRefundTaxBreakdownAmount = array[2].trim().split(" ")[0];
			refundTaxTotal = refundTaxTotal + Float.parseFloat(tempRefundTaxBreakdownAmount);
			refundTaxBreakdownAmount.add(tempRefundTaxBreakdownAmount);
			refundTaxBreakdownCode.add(array[0]);
			refundTaxBreakdownCurrency.add(array[2].trim().split(" ")[1]);
			refundTaxBreakdownDescription.add(array[1]);

		}
		storedFarePassenger.put("RefundTaxTotal", Float.valueOf(df.format(refundTaxTotal)));
		storedFarePassenger.put("RefundTaxBreakdownCode", String.join(delimeter, refundTaxBreakdownCode));
		storedFarePassenger.put("RefundTaxBreakdownDescription", String.join(delimeter, refundTaxBreakdownDescription));
		storedFarePassenger.put("RefundTaxBreakdownAmount", String.join(delimeter, refundTaxBreakdownAmount));
		storedFarePassenger.put("RefundTaxBreakdownCurrency", String.join(delimeter, refundTaxBreakdownCurrency));
		return jo.toString();
	}

}
