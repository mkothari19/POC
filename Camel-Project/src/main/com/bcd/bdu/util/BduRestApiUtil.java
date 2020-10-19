package com.bcd.bdu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest;

public abstract class BduRestApiUtil {

	private static final Logger LOGGER = Logger.getLogger(BduRestApiUtil.class);

	public void createRequest(PNRListRequest request, String path, int timeinterval, String pastdate,
			String lastrundate, String logenable, String filename) throws Exception {
		final PNRListRequest.DateRanges ranges = new PNRListRequest.DateRanges();
		DateTimeFormatter pastdateFormater = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		SimpleDateFormat formatterold = new SimpleDateFormat("yyyyMMddHHmmss");
		formatterold.setTimeZone(TimeZone.getTimeZone("UTC"));
		String currentd = formatterold.format(new Date());
		String endDate = null;
		String startDate = null;
		LocalDateTime aLDT = null;
		ZonedDateTime zdt = null;
	
		boolean iscurrentDate = false;
		if (lastrundate != null) {
			aLDT = LocalDateTime.parse(lastrundate.trim(), pastdateFormater);
			zdt = aLDT.atZone(ZoneId.of("UTC"));
			startDate = pastdateFormater.format(zdt);
			ZonedDateTime minPrev = zdt.plusMinutes(Long.valueOf(timeinterval));
			endDate = pastdateFormater.format(minPrev);
			Date currentdate = formatterold.parse(currentd);
			Date endD = formatterold.parse(endDate);
			
			if (currentdate.compareTo(endD) <= 0) {
				iscurrentDate = true;
			}

			if (iscurrentDate) {
				Date startD = formatterold.parse(startDate);
				long diffInMillies = Math.abs(startD.getTime() - currentdate.getTime());
				long diffminute = diffInMillies / 60000;
				ZonedDateTime minPrevnew = zdt.plusMinutes(Long.valueOf(diffminute));
				endDate = pastdateFormater.format(minPrevnew);
			} else {
				endDate = pastdateFormater.format(minPrev);
			}

		} else {
			aLDT = LocalDateTime.parse(pastdate.trim(), pastdateFormater);
			zdt = aLDT.atZone(ZoneId.of("UTC"));
			startDate = pastdateFormater.format(zdt);
			ZonedDateTime minPrev = zdt.plusMinutes(Long.valueOf(timeinterval));
			endDate = pastdateFormater.format(minPrev);
		}

		ranges.setStartDate(startDate);
		ranges.setEndDate(endDate);
		request.setDateRanges(ranges);
		/*
		 * Debug date range logs
		 */

		if ("true".equals(logenable)) {

			String data = filename + "  > " + " startdate " + startDate + " endDate " + endDate + " lastrundate "
					+ lastrundate + " pastdate " + pastdate;
			writedate(data, path + "/daterangelog.txt");

		}

	}

	public String getLastEndDateForApiCall(String path, String filename) throws Exception {
		String apistartdate = null;
		File tempFile = null;
		tempFile = new File(path + "/" + filename);

		boolean exists = tempFile.exists();
		if (exists) {
			BufferedReader br = new BufferedReader(new FileReader(tempFile));

			String st;
			while ((st = br.readLine()) != null) {
				apistartdate = st;
			}

		}
		return apistartdate;
	}

	public void createBduApiStartDate(String path, String filename, String enddate) throws IOException {
		/*
		 * Storing job stop time i.e Below implementation is required if we down the
		 * application for deployment so we need to make start date for api from job
		 * stop time
		 */

		File jobstopdatefile = new File(path + "/" + filename);
		jobstopdatefile.createNewFile(); // if file already exists will do nothing
		FileOutputStream oFile = new FileOutputStream(jobstopdatefile, false);
		byte b[] = enddate.getBytes();// converting string into byte array
		oFile.write(b);
		oFile.close();
	}
	/*
	 *  Store startdate and enddate into log file to track start and end date of api
	 */
	public static void writedate(String fileContent, String path) throws Exception {
 
		File writedata = new File(path);
		FileWriter writer = new FileWriter(writedata, true);
		writer.write(fileContent.toString() + "\n");
		writer.close();

	}
	
	/*
	 * Rich and Raw enrichment
	 */
	
	private  Properties getEnricherKeyValue(String enricherTagsFile) {
		Properties properties = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream(enricherTagsFile)) {
			properties.load(fileInputStream);
		} catch (IOException e) {
			LOGGER.error("Exception Raised while reading file :" + enricherTagsFile);
		}
		return properties;
	}
	
	public  JSONObject getEnrichedJson(JSONObject baseJson, JSONObject enricherJson, String enricherTagsFilePath,boolean replaceTag) {
		Properties properties = getEnricherKeyValue(enricherTagsFilePath);
		JSONObject enrichedJson = baseJson;
		for (String tagPath : properties.stringPropertyNames()) {
			enrichedJson = enrichJson(enrichedJson, enricherJson, tagPath, properties.getProperty(tagPath),replaceTag);
		}
		return enrichedJson;
	}
	
	private  JSONObject enrichJson(JSONObject baseJson, JSONObject enricherJson, String enricherAbsTag,
			String enrichTag,boolean replaceTag) {

		List<String> enricherTagPathTrace = Arrays.asList(enricherAbsTag.split("/"));

		JSONObject enricherNodeValue = enricherJson;
		Object enricherNodeObjectValue = null;
		for (String s : enricherTagPathTrace) {
			if (enricherNodeValue.has(s)) {
				if (enricherNodeValue.get(s) == null) {
					enricherNodeValue = null;
					break;
				} else {
					if(enricherNodeValue.get(s) instanceof  JSONObject) {
					enricherNodeValue = (JSONObject) enricherNodeValue.getJSONObject(s);
					}
					else {
						enricherNodeObjectValue = enricherNodeValue.get(s);
						enricherNodeValue=null;
					}
				}
			} else {
				//enricherNodeValue = new JSONObject();
				enricherNodeValue = null;
				break;
			}
		}
		
		//System.out.println("enricherNodeObjectValue :"+enricherNodeObjectValue);
		// String last = enricherTag.get(enricherTag.size() - 1);
		/*
		 * JSONObject parentNodeValue;
		 * 
		 * if (baseJson.has(parentTag)) { parentNodeValue = (JSONObject)
		 * baseJson.get(parentTag); parentNodeValue.put(last, enricherNodeValue);
		 * baseJson.put(parentTag, parentNodeValue); } else { JSONObject enricherNode =
		 * new JSONObject(); enricherNode.put(last, enricherNodeValue);
		 * baseJson.put(parentTag, enricherNode); }
		 */
		if(!baseJson.has(enrichTag)) {
			if (enricherNodeValue != null) {
				baseJson.put(enrichTag, enricherNodeValue);
			} else {
				if(enricherNodeObjectValue!=null )
					baseJson.put(enrichTag,enricherNodeObjectValue);
				else
				baseJson.put(enrichTag, JSONObject.NULL);
			}
		}
		else if(replaceTag == true) {
		if (enricherNodeValue != null) {
			baseJson.put(enrichTag, enricherNodeValue);
		} else {
			if(enricherNodeObjectValue!=null )
				baseJson.put(enrichTag,enricherNodeObjectValue);
			else
			baseJson.put(enrichTag, JSONObject.NULL);
		}
		}
		return baseJson;
	}

}
