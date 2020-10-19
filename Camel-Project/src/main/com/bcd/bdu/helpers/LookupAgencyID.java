package com.bcd.bdu.helpers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class LookupAgencyID {

	private static LookupAgencyID instance;
	private Map<String,String> sourceMap =new HashMap<String,String>();
	

	private LookupAgencyID() {
		
	}
	
	public static LookupAgencyID getInstance() {
        if (instance == null) {
            instance = new LookupAgencyID();
        } 

        return instance;
    }
	
	public Map<String,String> getSourceFromAgencyID() throws SQLException, IOException {
	
		
		/*sourceMap.put("1087","AFS");
		sourceMap.put("1083","AIRBNB");
		sourceMap.put("200001","BALBOA");
		sourceMap.put("1088","BCDBSIUK");
		sourceMap.put("1089","BCDJP");
		sourceMap.put("1082","CHINA");
		sourceMap.put("200000","COMPLEAT");
		sourceMap.put("1084","CONLIN");
		sourceMap.put("1090","Ovation");
		sourceMap.put("1090","AMEXSP");*/
		return HiveDBConnection.getInstance().getAgenecyid();
	}

}
