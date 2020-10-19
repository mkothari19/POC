package com.bcd.bdu.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bcd.bdu.helpers.HiveDBConnection;
import com.google.common.collect.Sets;

import volt.bcdtravel.credential.SecretManager;
/*
 * Utility class have common methods
 */
public class BduUtils {
	 private static final Logger LOGGER = Logger.getLogger(BduUtils.class);
	 private static BduUtils instance;
	private BduUtils() {
		
	}
	 public static BduUtils getInstance(){
	        if (instance == null) {
	        	  synchronized (BduUtils.class) {
	        		  if(instance == null){
	            instance = new BduUtils();
	        		  }
	        	  }
	        } 

	        return instance;
	    }

	 /*
	  * Standardize date 
	  */
	public  String dateParser(String dateval)throws DateTimeParseException {

		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss:SS][MM/dd/yyyy hh:mm:ss a][M/dd/yyyy hh:mm:ss a]"
                + "[M/dd/yyyy h:mm:ss a][MM/d/yyyy hh:mm:ss a]"
                + "[MM/d/yyyy hh:mm:ss a][M/d/yyyy h:mm:ss a][dd/MM/yy HH:mm:ss]"
                + "[dd/MM/yy HH:mm][yyyy-MM-dd HH:mm:ss][yyyy-MM-dd HH:mm:ss:SSS]"
                );
        LocalDateTime datetime = LocalDateTime.parse(dateval, formatter);
        return datetime.format(newPattern);

}
	/*
	 * Read from base template for schema evoluation
	 */
	public  Set<String> getBaseTemplate(String dir,String template) throws Exception {
		 Set<String> schema = Sets.newHashSet();
		  String basetemlate=dir+template;
		  String line = null;
		
		   FileReader fileReader = 
            new FileReader(basetemlate);

        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
        	schema.add(line.trim());
        }   
        fileReader.close();
        bufferedReader.close();
		 
		 return schema;
	}

	public Map<String,String> getGDS() throws SQLException, IOException{
		
		return HiveDBConnection.getInstance().getGds();
}
	
	public String getUTCDateAndTime(String formatter) {
		 final SimpleDateFormat f1 = new SimpleDateFormat(formatter);
			f1.setTimeZone(TimeZone.getTimeZone("UTC"));
			String timestamp = f1.format(new Date()); 
			
			return timestamp;
	}
	
	 public  JSONObject  initializeCredentials(String secret,String region,String endpoint,String attempt )  {

	        String hiveSecret=null;
			try {
				hiveSecret = new SecretManager().getSecret(secret, region, endpoint, Integer.parseInt(attempt));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.exit(1);
			}
	        return new JSONObject(hiveSecret);

	 

	    }
}