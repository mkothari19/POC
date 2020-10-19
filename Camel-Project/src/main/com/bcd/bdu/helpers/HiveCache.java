package com.bcd.bdu.helpers;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bcd.bdu.util.BduUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import volt.bcdtravel.credential.SecretManager;

public class HiveCache {

	private static HiveCache instance;
	private static final String dbUrl = "bdu.hiveDBurl";
	private static final String dbUser = "bdu.hiveuser";
	private static final String dbPass = "bdu.hivepassword";
	private static final String dbMaxPool = "db.hiveMaxPool";
	private static final String dbMinIdle = "db.minIdle";
	private static final String dbConnectionTimeOut = "db.connectionTimeout";
	private Map<String,String>citymap=new HashMap<String,String>();
	Map<String,String> agemcyidmap=new HashMap<String,String>();
	Map<String,String> gdsmap=new HashMap<String,String>();
	 private static final Logger LOGGER = Logger.getLogger(HiveCache.class);
	 private static Properties properties=null;
	 private static HikariDataSource datasource;
	 private static JSONObject jsonobject=null;
	 
	 
	private HiveCache() throws IOException {
		loadFromProperties();
		getDataSource();
		
		
	}
	
	public HiveCache(Map<String,String> citymap) {
		this.citymap=citymap;
	}
	
	 public static HiveCache getInstance() throws IOException{
	        if (instance == null) {
	        	  synchronized (HiveCache.class) {
	        		  if(instance == null){
	            instance = new HiveCache();
	        		  }
	        	  }
	        } 

	        return instance;
	    }
	 private HikariDataSource getDataSource() {
			if (datasource == null) {
				jsonobject=	BduUtils.getInstance().initializeCredentials(properties.getProperty("aws.hive.secret"),properties.getProperty("aws.region"),properties.getProperty("aws.endpoint"),properties.getProperty("aws.volt.attempt"));
				
				HikariConfig config = new HikariConfig();
				config.setJdbcUrl(properties.getProperty(dbUrl));
				config.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
				config.setUsername(jsonobject.getString("username"));
				config.setPassword(jsonobject.getString("password"));
				config.setConnectionTimeout(Integer.parseInt(properties.getProperty(dbConnectionTimeOut)));
				config.setMaximumPoolSize(Integer.parseInt(properties.getProperty(dbMaxPool)));
				config.setMinimumIdle(Integer.parseInt(properties.getProperty(dbMinIdle)));
				config.addDataSourceProperty("socketTimeout",0);
				datasource = new HikariDataSource(config);
			}
			return (HikariDataSource) datasource;
		}
	 
	 public  Connection getConnection() throws SQLException {
			Connection con =null;
			try {
				con= datasource.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				LOGGER.error("Exception to execute getConnection: "+e);
				System.exit(1);
			}
			return con;
		}
	 
	 public Map<String,String> getCityCache() throws SQLException {
		 Connection con =null;
		 Statement statement = null;
		 ResultSet res=null;
		 try {
		 con=getConnection();
		 statement = con.createStatement();
		 String sql = ("select city_air.citycd,city_air.citynm,city_air.countrycd from trst_ref_pub_master.city_air");
		 res = statement.executeQuery(sql);
		 while (res.next()) {
			 citymap.put(res.getString(1).toString().toLowerCase(), res.getString(2).toString()+"="+res.getString(3).toString());
			}
		 }catch(SQLException ex) {
			 LOGGER.error("Exception to execute getCityCache: "+ex);
		 }finally {
			 res.close();
			 statement.close();
			 con.close();
		 }
		 return citymap;
	 }
	 
	 public  Map<String,String> getGlobalClientIds() throws SQLException {
			 Connection con =null;
			 Statement statement = null;
			 ResultSet res=null;
			try {
				 con=getConnection();
				 statement = con.createStatement();
				res = statement.executeQuery("select sourceid,instance from trst_ref_pub_master.sourceidentifier");
				
				while (res.next()) {
					agemcyidmap.put(res.getString(1).toString(), res.getString(2).toString());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e);
			}finally{
				if(res!=null) {
					 res.close();
					 statement.close();
					 con.close();
				}
			}
			return agemcyidmap;
			
		}
	 
	 public Map<String,String>  getGDS() throws SQLException {
			 Connection con =null;
			 Statement statement = null;
			 ResultSet res=null;
			
			try {
				 con=getConnection();
				 statement = con.createStatement();
				res = statement.executeQuery("select sourcegdscode,maptogdscode,description from trst_ref_pub_master.gdsmapping");
				
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
					 statement.close();
					 con.close();
				}
			}
			return gdsmap;
		}
		
	 
	 private static Properties loadFromProperties() throws IOException  {
			FileReader reader=null;
			
			try {
				reader = new FileReader("/landing/data/hs/tsnt/froff/bdu/conf/application.properties");
				properties=new Properties();  
				properties.load(reader);  
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Error to execute loadFromProperties "+e);
			} finally {
				
					if(reader!=null)
					reader.close();
				
			}
			   
			   return properties;
		}
	 
	
}
