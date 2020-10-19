package com.bcd.bdu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bcd.bdu.helpers.HiveCache;
import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest;
import com.bcd.bdu.model.bookingdata.list.req.PNRListRequest.DateRanges;
import com.bcd.bdu.route.AirBnbSchemaEvaluationRoute;
import com.bcd.bdu.route.BDUApiFilesDataRoute;
import com.bcd.bdu.route.BookingDataRoute;
import com.bcd.bdu.route.CLIDirectBookingRoute;
import com.bcd.bdu.route.CompleatSchemaEvaluationRoute;
import com.bcd.bdu.route.CompleatTransformRoute;
import com.bcd.bdu.route.CompleatXMLToJson;
import com.bcd.bdu.route.DBXMLToJSON;
import com.bcd.bdu.route.DataEnrichmentRoute;
import com.bcd.bdu.route.DirectBookingFileTransferRoute;
import com.bcd.bdu.route.FLFileTransferRoute;
import com.bcd.bdu.route.FLSchemaEvaluationRoute;
import com.bcd.bdu.route.FareLogixDataRoute;
import com.bcd.bdu.route.FareLogixXmlToJson;
import com.bcd.bdu.route.NotificationRoute;
import com.bcd.bdu.route.ParseFLPNRListRoute;
import com.bcd.bdu.route.ParsePNRListRoute;
import com.bcd.bdu.route.ParseTFPNRListRoute;
import com.bcd.bdu.route.PollingDataRoute;
import com.bcd.bdu.route.SMCSchemaEvaluation;
import com.bcd.bdu.route.SMCStreamingRoute;
import com.bcd.bdu.route.SMCXMLToJsonRoute;
import com.bcd.bdu.route.TFFileTransferRoute;
import com.bcd.bdu.route.TFSchemaEvaluationRoute;
import com.bcd.bdu.route.TravelFusionDataRoute;
import com.bcd.bdu.route.TravelFusionXmlToJson;
import com.bcd.bdu.route.ZipFileRoute;
import com.bcd.bdu.util.BduUtils;



/*
 * Entry point of Application
 */

public class BDUAPIClient {
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static final Logger LOGGER = Logger.getLogger(BDUAPIClient.class);

	public static void main(String[] args) throws Exception {
		
		CommandLineParser parser = new DefaultParser();
		CamelContext apiClient = new DefaultCamelContext();
		//registerRoutes(apiClient);
		ProducerTemplate template = apiClient.createProducerTemplate();
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();
	
        PropertiesComponent prop = apiClient.getComponent("properties", PropertiesComponent.class);
        prop.setPrefixToken("[(");
        prop.setSuffixToken(")]");
     //  prop.setLocation("application.properties");
      /*Changes for to make configurable properties from out side the project*/
        prop.setLocation("file:/landing/data/hs/tsnt/froff/bdu/conf/application.properties");
		initArgsCLI(options);

		try {
			CommandLine cmd = parser.parse(options,args);
		//	if(cmd.hasOption("m")) {
			if(true) {	
			String mode ="poll"; 
			
				switch(mode){
					case "poll":
		                registerRoutes(apiClient);
						if(cmd.getOptionValue("c") != null) prop.setLocation("file:" + cmd.getOptionValue("c"));
		                apiClient.start();
		                if(Boolean.valueOf(apiClient.resolvePropertyPlaceholders("[(airbnb.api.enabled)]"))) {
		                template.sendBody("controlbus:route?routeId=DirectBookingPoll&action=start",null);
		                }
		                
		                /*
		                 * Read configuration parameter for travel fusion api ,according switch on/off 
		                 * the functionality of pulling the api of travel fusion
		                 */
		                if(Boolean.valueOf(apiClient.resolvePropertyPlaceholders("[(travelfusion.api.enabled)]"))) {
		                template.sendBody("controlbus:route?routeId=TFDirectBookingPoll&action=start",null);
		                }
		                /*
		                 * Read configuration parameter for FareLogix  api ,according switch on/off 
		                 * the functionality of pulling the api of farelogix 
		                 */
		                if(Boolean.valueOf(apiClient.resolvePropertyPlaceholders("[(farelogix.api.enabled)]"))) {
			                template.sendBody("controlbus:route?routeId=FLDirectBookingPoll&action=start",null);
			                }
		                /*
		                 * SchemaEvaluation email notification
		                 */
		                template.sendBody("controlbus:route?routeId=CompleatEmailNotification&action=start",null);
		                template.sendBody("controlbus:route?routeId=DBEmailNotification&action=start",null);
		                template.sendBody("controlbus:route?routeId=SMCEmailNotification&action=start",null);
		                template.sendBody("controlbus:route?routeId=TFEmailNotification&action=start",null);
		                template.sendBody("controlbus:route?routeId=FLEmailNotification&action=start",null);
		                template.sendBody("controlbus:route?routeId=UpdateCityCache&action=start",null);
		                
		                break;
                   
					case "get-direct-booking":
						if(cmd.getOptionValue("s") == null) 
							throw new ParseException("Missing required option: -s | --start");
						if(cmd.getOptionValue("e") == null) 
							throw new ParseException("Missing required option: -e | --end");
						if(cmd.getOptionValue("c") != null) prop.setLocation("file:" + cmd.getOptionValue("c"));
		                apiClient.addRoutes(new CLIDirectBookingRoute(latch));
		                apiClient.start();
						String start = cmd.getOptionValue("s"); 
						String end = cmd.getOptionValue("e");
						PNRListRequest request = new PNRListRequest();
						DateRanges ranges = new DateRanges();
						ranges.setStartDate(start); ranges.setEndDate(end);	
						request.setDateRanges(ranges);
						template.sendBody("direct:booking_data_sa",request);
						latch.await(100, TimeUnit.SECONDS);
						break;                        
                   
					default:
						throw new ParseException("unknown mode " + mode);		
				}
			}
		} catch (ParseException pex) {
		
			formatter.printHelp("BDUAPIClient", options);
			apiClient.stop();
		}finally {
			System.out.println("HERER");
		}
	}

	public static void registerRoutes(CamelContext apiClient) throws Exception{
		Map<String,String> citymap=new HashMap<String,String>();
		Map<String,String> agencyid=new HashMap<String,String>();
		Map<String,String> gds=new HashMap<String,String>();
		agencyid=HiveCache.getInstance().getGlobalClientIds();
	    citymap=HiveCache.getInstance().getCityCache();
	    gds= HiveCache.getInstance().getGDS();
		apiClient.resolvePropertyPlaceholders("[(aws.rich.secret)]");
		apiClient.resolvePropertyPlaceholders("[(aws.region)]");
		apiClient.resolvePropertyPlaceholders("[(aws.volt.attempt)]");
	    JSONObject richjson=BduUtils.getInstance().initializeCredentials(apiClient.resolvePropertyPlaceholders("[(aws.rich.secret)]"),apiClient.resolvePropertyPlaceholders("[(aws.region)]"),apiClient.resolvePropertyPlaceholders("[(aws.endpoint)]"),apiClient.resolvePropertyPlaceholders("[(aws.volt.attempt)]"));
		 JSONObject rawjson=BduUtils.getInstance().initializeCredentials(apiClient.resolvePropertyPlaceholders("[(aws.raw.secret)]"),apiClient.resolvePropertyPlaceholders("[(aws.region)]"),apiClient.resolvePropertyPlaceholders("[(aws.endpoint)]"),apiClient.resolvePropertyPlaceholders("[(aws.volt.attempt)]"));
		
	    
	 //  citymap.put("1333","Test");
	   apiClient.addRoutes(new NotificationRoute());
	   apiClient.addRoutes(new PollingDataRoute(getAirBnbStartDateForApiCall(apiClient),getTFStartDateForApiCall(apiClient),getFLStartDateForApiCall(apiClient),citymap));
	   
	   /*Route use to  process records for DB*/
	   apiClient.addRoutes(new BDUAPIClientRoute());
	   apiClient.addRoutes(new BookingDataRoute(richjson));
	   apiClient.addRoutes(new ParsePNRListRoute());
	   
	   apiClient.addRoutes(new CompleatTransformRoute());
	   apiClient.addRoutes(new AirBnbSchemaEvaluationRoute(BduUtils.getInstance().getBaseTemplate( apiClient.resolvePropertyPlaceholders("[(schema.evaluation.template)]"),apiClient.resolvePropertyPlaceholders("[(email.airbnb.basetemplate)]"))));
	   apiClient.addRoutes(new DirectBookingFileTransferRoute());
	   apiClient.addRoutes(new DBXMLToJSON(gds));
	  
	  
	  /*Route use to process records for SMC*/
	   apiClient.addRoutes(new SMCStreamingRoute(apiClient.resolvePropertyPlaceholders("[(parallel.thread.raw.xml)]")));
	   apiClient.addRoutes(new SMCSchemaEvaluation(BduUtils.getInstance().getBaseTemplate(apiClient.resolvePropertyPlaceholders("[(schema.evaluation.template)]"),apiClient.resolvePropertyPlaceholders("[(email.smc.basetemplate)]"))));
	   apiClient.addRoutes(new SMCXMLToJsonRoute(agencyid,apiClient.resolvePropertyPlaceholders("[(parallel.thread.raw.xml)]"),gds));
	   
	  
	   /*Route use to fetch travel fusion records*/
	  // apiClient.addRoutes(new BDUTFAPIClientRoute());
	   apiClient.addRoutes(new ParseTFPNRListRoute());
	   apiClient.addRoutes(new TravelFusionDataRoute(rawjson));
	   apiClient.addRoutes(new TFFileTransferRoute());
	   apiClient.addRoutes(new TFSchemaEvaluationRoute(BduUtils.getInstance().getBaseTemplate( apiClient.resolvePropertyPlaceholders("[(schema.evaluation.template)]"),apiClient.resolvePropertyPlaceholders("[(email.tf.basetemplate)]"))));
	   apiClient.addRoutes(new TravelFusionXmlToJson(gds));
	 
	   /*Route use to fetch Fare Logix records*/
	   apiClient.addRoutes(new ParseFLPNRListRoute());
	   apiClient.addRoutes(new FareLogixDataRoute(rawjson));
	   apiClient.addRoutes(new FLFileTransferRoute());
	   apiClient.addRoutes(new FLSchemaEvaluationRoute(BduUtils.getInstance().getBaseTemplate( apiClient.resolvePropertyPlaceholders("[(schema.evaluation.template)]"),apiClient.resolvePropertyPlaceholders("[(email.fl.basetemplate)]"))));
	   apiClient.addRoutes(new FareLogixXmlToJson(citymap,gds));
	   /*Route use to process records for compleat*/
	  apiClient.addRoutes(new ZipFileRoute());
	  apiClient.addRoutes(new CompleatSchemaEvaluationRoute(BduUtils.getInstance().getBaseTemplate(apiClient.resolvePropertyPlaceholders("[(schema.evaluation.template)]"),apiClient.resolvePropertyPlaceholders("[(email.compleat.basetemplate)]"))));
	  apiClient.addRoutes(new CompleatXMLToJson(gds));
	
	  /*Raw Xmls files processing from S3*/
	  apiClient.addRoutes(new BDUApiFilesDataRoute());
	   
	 /*Raw Data Enrichment */
	   apiClient.addRoutes(new DataEnrichmentRoute(richjson,rawjson));
	   
	 
	}

	public static void initArgsCLI(Options options){
        options.addOption(Option.builder("m").required(true)
        		.hasArg()
        		.desc("api client command mode ( poll | stream-publish )")
        		.longOpt("mode").build());
        options.addOption(Option.builder("c").required(false)
        		.hasArg()
        		.desc("configuration file")
        		.longOpt("config").build());
        options.addOption(Option.builder("s").required(false)
        		.hasArg()
        		.desc("start date to query from")
        		.longOpt("start").build());
        options.addOption(Option.builder("e").required(false)
        		.hasArg()
        		.desc("end date to query from")
        		.longOpt("end").build());
        options.addOption(Option.builder("t").required(false)
        		.hasArg()
        		.desc("kafka topic")
        		.longOpt("topic").build());
        options.addOption(Option.builder("ks").required(false)
        		.hasArg()
        		.desc("kafka server")
        		.longOpt("kafka-server").build());
        options.addOption(Option.builder("h").required(false)
        		.hasArg()
        		.desc("commands")
        		.longOpt("help").build());
        options.addOption(Option.builder("msg").required(false)
        		.hasArg()
        		.desc("message")
        		.longOpt("message").build());
        options.addOption(Option.builder("dir").required(false)
        		.hasArg()
        		.desc("directory")
        		.longOpt("dir").build());
    
	}
	
	
	private static String getAirBnbStartDateForApiCall(CamelContext apiClient) throws Exception {
		SimpleDateFormat formatterold=new SimpleDateFormat("yyyyMMddHHmmss");  
		 formatterold.setTimeZone(TimeZone.getTimeZone("UTC"));
		 DateFormat destDf = new SimpleDateFormat("yyyyMMdd");
		 destDf.setTimeZone(TimeZone.getTimeZone("UTC"));
		 String  currentdatestr=destDf.format(new Date());
		 Date currentdate=destDf.parse(currentdatestr);
		 
			
		 String pastdate= apiClient.resolvePropertyPlaceholders("[(airbnb.past.date.range)]");
		Date date = formatterold.parse(pastdate);
		  String lastrundaten = destDf.format(date);
		
		  Date lastrundated=destDf.parse(lastrundaten);
			 if(currentdate.compareTo(lastrundated)<0) {
				 lastrundaten="20190601000000";
				 LOGGER.warn("AirBnb Past date can not greater to current date so provide future date 20190601000000");
			 }else {
				  lastrundaten=lastrundaten+"000000"; 
			 }
		String jobstopdate= apiClient.resolvePropertyPlaceholders("[(api.job.stop.date.path)]");
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

		}else {
			apistartdate=lastrundaten;
		}
		
		
		return apistartdate;
	}
	
	private static String getTFStartDateForApiCall(CamelContext apiClient) throws Exception {
		SimpleDateFormat formatterold=new SimpleDateFormat("yyyyMMddHHmmss");  
		 formatterold.setTimeZone(TimeZone.getTimeZone("UTC"));
		 DateFormat destDf = new SimpleDateFormat("yyyyMMdd");
		 destDf.setTimeZone(TimeZone.getTimeZone("UTC"));
		 String  currentdatestr=destDf.format(new Date());
		 Date currentdate=destDf.parse(currentdatestr);
		 
		String pastdate= apiClient.resolvePropertyPlaceholders("[(tf.past.date.range)]");
		Date date = formatterold.parse(pastdate);
		  String lastrundaten = destDf.format(date);
		
		  Date lastrundated=destDf.parse(lastrundaten);
			 if(currentdate.compareTo(lastrundated)<0) {
				 lastrundaten="20190601000000";
				 LOGGER.warn("Travel Fusion Past date can not greater from current date so provide future date 20190601000000");
			 }else {
				  lastrundaten=lastrundaten+"000000"; 
			 }
		String jobstopdate= apiClient.resolvePropertyPlaceholders("[(api.job.stop.date.path)]");
		String apistartdate=null;
		File tempFile = new File(jobstopdate+"/tfjobstopdate.txt");
		boolean exists = tempFile.exists();
		if(exists) {
			 BufferedReader br = new BufferedReader(new FileReader(tempFile)); 
			  
			  String st; 
			  while ((st = br.readLine()) != null) {
				  apistartdate=st;
			  } 
			  br.close();

		}else {
			apistartdate=lastrundaten;
		}
		
		
		return apistartdate;
	}
	
	private static String getFLStartDateForApiCall(CamelContext apiClient) throws Exception {
		SimpleDateFormat formatterold=new SimpleDateFormat("yyyyMMddHHmmss");  
		 formatterold.setTimeZone(TimeZone.getTimeZone("UTC"));
		 DateFormat destDf = new SimpleDateFormat("yyyyMMdd");
		 destDf.setTimeZone(TimeZone.getTimeZone("UTC"));
		 String  currentdatestr=destDf.format(new Date());
		 Date currentdate=destDf.parse(currentdatestr);
		 
		String pastdate= apiClient.resolvePropertyPlaceholders("[(fl.past.date.range)]");
		Date date = formatterold.parse(pastdate);
		  String lastrundaten = destDf.format(date);
		
		  Date lastrundated=destDf.parse(lastrundaten);
			 if(currentdate.compareTo(lastrundated)<0) {
				 lastrundaten="20190601000000";
				 LOGGER.warn("Travel Fusion Past date can not greater from current date so provide future date 20190601000000");
			 }else {
				  lastrundaten=lastrundaten+"000000"; 
			 }
		String jobstopdate= apiClient.resolvePropertyPlaceholders("[(api.job.stop.date.path)]");
		String apistartdate=null;
		File tempFile = new File(jobstopdate+"/fljobstopdate.txt");
		boolean exists = tempFile.exists();
		if(exists) {
			 BufferedReader br = new BufferedReader(new FileReader(tempFile)); 
			  
			  String st; 
			  while ((st = br.readLine()) != null) {
				  apistartdate=st;
			  } 
			  br.close();

		}else {
			apistartdate=lastrundaten;
		}
		
		
		return apistartdate;
	}
	
	
}
