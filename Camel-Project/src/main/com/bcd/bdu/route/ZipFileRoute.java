package com.bcd.bdu.route;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipFile;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;

import com.bcd.bdu.processor.GetExceptionMessage;
/*
 * ZipFileRoute extract zip files and provide input to SchemaEval and Standardization route. 
 * This route will stop the processing once system level issue will be occurred like "disk full"
 */
public class ZipFileRoute extends RouteBuilder {
	ZipFile zipfile=null;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		onException(Exception.class)
    	.process(new GetExceptionMessage())
    	.handled(true)
    	.choice()
    		.when()
    		/*
    		 * Invalid zip files tracking.If zip file is invalid then move to badzip folder inside error-logs folder
		       Logs are available in badzip_logs_.log file inside error_logs folder
    		 */
    			.simple("${header.DL_ZipFileName} != null && ${header.compleatfilename} == null")
    			.to("file://[(api.complete.compleat.badzip)]?fileName=${header.DL_ZipFileName}")
    			.setBody(simple("${date:now:yyyyMMddHHmmss}: ZipFileRoute : ${header.DL_ZipFileName} : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.complete.compleat.log)]?fileName=badzip_error_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    			.otherwise()
    			/*
    			 * System error like disk full will available in file impacted_zip_files_.log  inside /error_logs/system_env_error directory
    			 */
    		    .setBody(simple("${date:now:yyyyMMddHHmmss}: ZipFileRoute : NA : ${header.BduExceptionMessage}"))
    			.transform(body().append("\n"))
    			.to("file://[(api.complete.compleat.environmenterror.directory)]?fileName=impacted_zip_files_${date:now:MM-dd-yyyy}.log&fileExist=Append")
    			.process(new Processor() {
    				@Override
    				public void process(Exchange exchange) throws Exception {
    					// TODO Auto-generated method stub
    					String message=exchange.getIn().getHeader("BduExceptionMessage",String.class);
    					
    					if(message.contains("Cannot store file")) {
    						System.out.println("Job throwing "+message +" Please check  disk space immediately");
    						
    						System.exit(1);
    					}
    					
    					throw new Exception();
    				}
    				 
    			 })
    			.endChoice(); 
		
	
		ExecutorService executor = Executors.newFixedThreadPool(50);
	
		from("file://[(api.endpoint.compleat.pnr.landing.dir)]?include=.*zip&sortBy=file:modified&consumer.delay=10000&?idempotent=[(duplicate.file.nerver.process)]&delete=true&scheduler=quartz2&scheduler.cron=[(compleat.zip.cron)]")
		
		.to("file://[(api.complete.compleat.archive.directory)]")
		 .process(new Processor() {
		        public void process(Exchange exchange) throws Exception {
		        String zipfilename=	exchange.getIn().getHeader("CamelFileName",String.class);
		        exchange.getIn().setHeader("DL_ZipFileName", zipfilename);
		       final File file = exchange.getIn().getBody(File.class);
		 
		   //  Check zip file in valid or not.If invalid move to badzip folder inside error-logs folder
		     //Logs are available in badzip_logs file inside error_logs folder
		       
		       zipfile  = new ZipFile(file);
		        try {
		            if (zipfile != null) {
		                zipfile.close();
		                zipfile = null;
		              
		            }
		        } catch (IOException e) {
		        }
		        
		       }})
		 //Zip files are extracting and move to schemvaEval and raw directory for processing.
		 .routeId("ExtractFromZipRoute")
			.multicast()
		 .parallelProcessing().executorService(executor)
		 .split(new ZipSplitter())
		 
		  .streaming()
		  .process(new Processor() {
		        public void process(Exchange exchange) throws Exception {
		        	 final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			 	        f.setTimeZone(TimeZone.getTimeZone("UTC"));
			 	       	String ts=f.format(new Date());
		        	
		        	String xmlfilename=	exchange.getIn().getHeader("CamelFileName",String.class);
		        xmlfilename=xmlfilename.replaceAll(" ", "-");
		        String zipfilename=	exchange.getIn().getHeader("DL_ZipFileName",String.class);
		        String filename=ts+"landingts"+zipfilename+xmlfilename;
		        /*Note:XML file name created like 
		       " 2019-11-13-12-36-05.249landingtsBCD_20190531_131433_297.zip2019-05-31-13-08-14Z-QN6FCG.xml"
		       Below is the description
		        1. Here 2019-11-13-12-36-05.249landingts mean time of xml landing time(zip extraction)
		        2. BCD_20190531_131433_297.zip : Zip filename(require because we need zip file name with in final records output and save into MapRDB)
		        3.2019-05-31-13-08-14Z-QN6FCG.xml : XML file name with in zip
		        */
		        exchange.getIn().setHeader("compleatfilename", filename);
		         }})
		  .convertBodyTo(String.class)
		  //.to("file://[(api.complete.raw.directory)]?fileName=${header.compleatfilename}")
		   .to("file://[(api.endpoint.compleat.pnr.schemaeval)]?fileName=${header.compleatfilename}")
		   .process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					String xmldata=exchange.getIn().getBody(String.class);
					xmldata=xmldata.replaceAll("&", "");
					exchange.getIn().setBody(xmldata);
				}
				 
			 })
		   .convertBodyTo(String.class)
		 .doTry()
		 .to("xslt:file://[(xslt.path)]/[(api.compleat.xslt)]")
		 .to("file://[(api.complete.compleat.directory)]?fileName=${header.compleatfilename}")
		 .doCatch(Exception.class)
		 .process(new GetExceptionMessage())
		 .to("file://[(api.complete.compleat.standardization.directory)]?fileName=${header.compleatfilename}")
		 .process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					String filename=exchange.getIn().getHeader("compleatfilename",String.class);
					String fileinsertts[]=filename.split("landingts");
			        String filearr[]=fileinsertts[1].split(".zip");  
			        String zipfilename=filearr[0]+".zip";
			        filename= filearr[1];
			        String errorfilename=filename+" with in  "+zipfilename+" file";
			        exchange.getIn().setHeader("FileName", errorfilename);
				}
				 
			 })
		 .setBody(simple("<br><b>>></b><b>Date</b>: ${date:now:yyyy-MM-dd-HH:mm}: <b>FileName</b>: '${header.FileName}': <b>Error</b> : ${header.BduExceptionMessage}"))
			 .transform(body().append("</br>"))
		 .to("file://[(api.complete.compleat.log)]?fileName=standardization_error_${date:now:yyyy-MM-dd}.log&fileExist=Append")
		 .doFinally()
		  .end();
	}
	

}
