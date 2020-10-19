package com.bcd.bdu.poc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class JdbcTest {

	public static void main(String[] args) {
		Connection conn=null;
		pingServer(conn);
	}
	
	public static void pingServer(Connection conn){
	    final Thread serverPing = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                while(!Thread.currentThread().isInterrupted()){
	                    String sql = "/* ping */ SELECT 1";
	                    PreparedStatement st = null;
	                    st = conn.prepareStatement(sql);
	                    st.executeUpdate(sql);
	                    TimeUnit.HOURS.sleep(1);
	                }
	            } catch (SQLException | InterruptedException e) {
	                System.out.println("Unable to continue pinging database server"+ e);
	                try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	            finally{
	            	 try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    });
	    serverPing.setDaemon(true);
	    serverPing.start();
	}
}
