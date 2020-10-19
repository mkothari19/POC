package com.bcd.bdu.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest;

public class CreatePnrList implements Processor {
	
	String pastdate=null;
	public CreatePnrList(String pastdate) {
		this.pastdate=pastdate;
	}
public void process(Exchange exchange) throws Exception {
		
		String period = exchange.getContext().resolvePropertyPlaceholders("[(api.polling.period)]");
		String count = exchange.getContext().resolvePropertyPlaceholders("[(api.polling.period.count)]");
		int timeinterval=Integer.parseInt(exchange.getContext().resolvePropertyPlaceholders("[(api.time.interval)]"));
		
		if(pastdate==null) {
			 pastdate=  exchange.getContext().resolvePropertyPlaceholders("[(tf.past.date.range)]");
		}
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
		PNRListRequest request = new PNRListRequest();
		switch(period) {
			case "seconds":
				ZonedDateTime secPrev = zdt.minusSeconds(Long.valueOf(count));
				createRequest(secPrev,request,pastdate,exchange,timeinterval);
				break;
			case "minutes":
				ZonedDateTime minPrev = zdt.minusMinutes(Long.valueOf(count));
				createRequest(minPrev,request,pastdate,exchange,timeinterval);
				break;
			case "hours":
				ZonedDateTime hrsPrev = zdt.minusHours(Long.valueOf(count));
				createRequest(hrsPrev,request,pastdate,exchange,timeinterval);
				break;
			case "days":
				ZonedDateTime daysPrev = zdt.minusDays(Long.valueOf(count));
				createRequest(daysPrev,request,pastdate,exchange,timeinterval);
				break;												
			default:
				break;
		}
		exchange.getIn().setBody(request);
		
	}

	public void createRequest(ZonedDateTime prev, PNRListRequest request,String pastdate,Exchange exchange,int timeinterval) throws Exception{
		
		  final String lastrundate = this.getLastEndDateForApiCall(exchange);
	        final PNRListRequest.DateRanges ranges = new PNRListRequest.DateRanges();
	        final DateTimeFormatter pastdateFormater = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        String endDate = null;
	        String startDate = null;
	        LocalDateTime aLDT = null;
	        ZonedDateTime zdt = null;
	        LocalDateTime now = LocalDateTime.now();
	        if (lastrundate != null) {
	            aLDT = LocalDateTime.parse(lastrundate.trim(), pastdateFormater);
	            zdt = aLDT.atZone(ZoneId.of("UTC"));
	           
            	now.atZone(ZoneId.of("UTC"));
	            final SimpleDateFormat formatterold = new SimpleDateFormat("yyyyMMddHHmmss");
	            formatterold.setTimeZone(TimeZone.getTimeZone("UTC"));
	            final DateFormat destDf = new SimpleDateFormat("yyyyMMdd");
	            destDf.setTimeZone(TimeZone.getTimeZone("UTC"));
	            final String currentdatestr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	            final Date currentdate = destDf.parse(currentdatestr);
	            final Date date = formatterold.parse(lastrundate);
	            final String lastrundaten = destDf.format(date);
	            final Date lastrundated = destDf.parse(lastrundaten);
	            if (currentdate.compareTo(lastrundated) == 0) {
	                final String currentd = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	                final String lastrdate = formatterold.format(date);
	                final Date cdate = formatterold.parse(currentd);
	                final Date lrundated = formatterold.parse(lastrdate);
	                final long diffInMillies = Math.abs(cdate.getTime() - lrundated.getTime());
	                final long diffminute = diffInMillies / 60000L;
	                if (diffminute > 0L) {
	                    timeinterval = (int)diffminute;
	                }
	            }
	          
	            startDate = pastdateFormater.format(zdt);
	            final ZonedDateTime minPrev = zdt.plusMinutes(timeinterval);
	            endDate = pastdateFormater.format(minPrev);
	        }
	        else {
	            aLDT = LocalDateTime.parse(pastdate.trim(), pastdateFormater);
	            zdt = aLDT.atZone(ZoneId.of("UTC"));
	            startDate = pastdateFormater.format(zdt);
	            final ZonedDateTime minPrev2 = zdt.plusMinutes(timeinterval);
	            endDate = pastdateFormater.format(minPrev2);
	        }
	        ranges.setStartDate(startDate);
	        ranges.setEndDate(endDate);
	        request.setDateRanges(ranges);
		
	}
	private  String getLastEndDateForApiCall(Exchange exchange) throws Exception {
		String jobstopdate=exchange.getContext().resolvePropertyPlaceholders("[(api.job.stop.date.path)]");
		String apistartdate=null;
		File tempFile = new File(jobstopdate+"/airbnbjobstopdate.txt");
		boolean exists = tempFile.exists();
		if(exists) {
			 BufferedReader br = new BufferedReader(new FileReader(tempFile)); 
			  
			  String st; 
			  while ((st = br.readLine()) != null) {
				  apistartdate=st;
			  } 
			  br.close();

		}
		
		return apistartdate;
	}


}

