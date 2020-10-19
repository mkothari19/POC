package com.bcd.bdu.helpers;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HiveDBConnection {
	
	
	 private static HiveDBConnection instance;
	private  Map<String, String> agenecyid = new HashMap<String, String>();
	private  Map<String, String> gds = new HashMap<String, String>();
	 private static final Logger LOGGER = Logger.getLogger(HiveDBConnection.class);
	
	private Statement statement = null;
	private static Properties prop=null;
	 
	
	 private HiveDBConnection() throws SQLException, IOException {
		 lookUp();
	    }
	
	 public static HiveDBConnection getInstance() throws SQLException, IOException {
	        if (instance == null) {
	            instance = new HiveDBConnection();
	        } 

	        return instance;
	    }
	 
	 

	
	private  void lookUp() throws SQLException, IOException {
		Connection connection = null;
		try {
			getGDS();
			 prop=loadFromProperties();
			String hiveURL=prop.getProperty("bdu.hiveURL");
			String hiveuser=prop.getProperty("bdu.hiveuser");
			String hivepassword=prop.getProperty("bdu.hivepassword");
			String driverName = "org.apache.hive.jdbc.HiveDriver";
			//System.out.println(hiveURL +" "+ hiveuser+" "+ hivepassword+" "+driverName);
			Class.forName(driverName);
			
			connection = DriverManager.getConnection(hiveURL, hiveuser, hivepassword);
			statement = connection.createStatement();
			getGlobalClientIds();
		
			
		} catch (SQLException | ClassNotFoundException ex) {
			LOGGER.error(ex);
		} finally {
			if (connection != null)
					connection.close();
		}
	}
	

	private  void getGlobalClientIds() throws SQLException {
		String sql = ("select sourceid,instance from trst_ref_pub_master.sourceidentifier");
		ResultSet res=null;
		Map<String,String> agemcyidmap=new HashMap<String,String>();
	
		try {
			res = statement.executeQuery(sql);
			
			while (res.next()) {
				agemcyidmap.put(res.getString(1).toString(), res.getString(2).toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}finally{
			if(res!=null) {
					res.close();
			}
		}
		setAgenecyid(agemcyidmap);
	}

	private void  getGDS() throws SQLException {
		/*
		 * select sourcegdscode,maptogdscode,description from `trst_ref_pub_master`.`gdsmapping`;
		 */
		String sql = ("select sourcegdscode,maptogdscode,description from trst_ref_pub_master.gdsmapping");
		ResultSet res=null;
		Map<String,String> gdsmap=new HashMap<String,String>();
	
		try {
			res = statement.executeQuery(sql);
			
			while (res.next()) {
				gdsmap.put(res.getString(1).toString(), res.getString(3).toString());
				gdsmap.put(res.getString(2).toString(), res.getString(3).toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}finally{
			if(res!=null) {
					res.close();
			}
		}
		 setGds(gdsmap);
	}
		
		  
		
		private static Properties loadFromProperties() throws IOException  {
		FileReader reader=null;
		Properties prop=null;
		try {
			reader = new FileReader("/landing/data/hs/tsnt/froff/bdu/conf/application.properties");
			  prop=new Properties();  
			   prop.load(reader);  
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		} finally {
			
				if(reader!=null)
				reader.close();
			
		}
		   
		   return prop;
	}

		public Map<String, String> getAgenecyid() {
			return agenecyid;
		}

		private void setAgenecyid(Map<String, String> agenecyid) {
			this.agenecyid = agenecyid;
		}

		public Map<String, String> getGds() {
			return gds;
		}

		public void setGds(Map<String, String> gds) {
			this.gds = gds;
		}
	

}
