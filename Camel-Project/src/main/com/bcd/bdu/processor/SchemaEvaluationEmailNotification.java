package com.bcd.bdu.processor;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SchemaEvaluationEmailNotification implements Processor {

	String source=null;
	public SchemaEvaluationEmailNotification(String source) {
		this.source=source;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String isntofication = exchange.getContext().resolvePropertyPlaceholders("[(email.enable)]");
		String emailtemplate = exchange.getContext().resolvePropertyPlaceholders("[(email.recipients)]");
		String subjectnewtag = exchange.getContext().resolvePropertyPlaceholders("[(email.subject.tags.available)]");
		
		if("Compleat".equals(source)) {
			StringBuffer schemanewtag=new StringBuffer();
			  String basetemlate =  exchange.getContext().resolvePropertyPlaceholders("[(newtags.repository)]");
			  basetemlate=basetemlate+"/compleat_newtags.txt";
			  String newtagline = null;
			
			   FileReader fileReader = 
	              new FileReader(basetemlate);

	          BufferedReader bufferedReader = 
	              new BufferedReader(fileReader);

	          while((newtagline = bufferedReader.readLine()) != null) {
	        	  schemanewtag.append(newtagline+ "\n");
	           // System.out.println(line);
	          } 
	          
	        //  System.out.println(schemanewtag);
	          fileReader.close();
	          bufferedReader.close();
	          exchange.getIn().setHeader("compleatnewtags","");
	          if(schemanewtag.length()>0) {
	        	  if("true".equals(isntofication.trim())) {
	  				String subject="'BDU "+ source+" "+subjectnewtag+"\"'";
	  				String emailcmd="echo "+ "'"+schemanewtag.toString().trim()+"'"+" | "+"mail -s " +subject+" "+emailtemplate;
	  				//System.out.println(emailcmd);
	  				 ProcessBuilder processBuilderemail = new ProcessBuilder();
	  				  processBuilderemail.command("bash", "-c", emailcmd);
	  				  Process processemail = processBuilderemail.start();
	  				  int exitValemail = processemail.waitFor();
	  					
	  					if (exitValemail == 0) {
	  				
	  						exchange.getIn().setHeader("compleatnewtags","");
	  						//System.out.println("Email sent");
	  						
	  					}
	  					processemail.destroy();
	  				
	  					
	  				}
	          }
		}
		
		
		
		if("DirectBooking".equals(source)) {
			StringBuffer schemanewtag=new StringBuffer();
			  String basetemlate =  exchange.getContext().resolvePropertyPlaceholders("[(newtags.repository)]");
			  basetemlate=basetemlate+"/directbooking_newtags.txt";
			  String newtagline = null;
			
			   FileReader fileReader = 
	              new FileReader(basetemlate);

	          BufferedReader bufferedReader = 
	              new BufferedReader(fileReader);

	          while((newtagline = bufferedReader.readLine()) != null) {
	        	  schemanewtag.append(newtagline+"\n");
	           // System.out.println(line);
	          } 
	          
	        //  System.out.println(schemanewtag);
	          fileReader.close();
	          bufferedReader.close();
	          exchange.getIn().setHeader("dbnewtags","");
	          if(schemanewtag.length()>0) {
	        	  if("true".equals(isntofication.trim())) {
	        		  String subject="'BDU "+ source+" "+subjectnewtag+"\"'";
	  				String emailcmd="echo "+ "'"+schemanewtag.toString().trim()+"'"+" | "+"mail -s " +subject+" "+emailtemplate;
	  				//System.out.println(emailcmd);
	  				 ProcessBuilder processBuilderemail = new ProcessBuilder();
	  				  processBuilderemail.command("bash", "-c", emailcmd);
	  				  Process processemail = processBuilderemail.start();
	  				  int exitValemail = processemail.waitFor();
	  					
	  					if (exitValemail == 0) {
	  				
	  						exchange.getIn().setHeader("dbnewtags","");
	  						//System.out.println("Email sent");
	  						
	  					}
	  					processemail.destroy();
	  				
	  					
	  				}
	          }
		}
		
		if("SMC".equals(source)) {
			StringBuffer schemanewtag=new StringBuffer();
			  String basetemlate =  exchange.getContext().resolvePropertyPlaceholders("[(newtags.repository)]");
			  basetemlate=basetemlate+"/smc_newtags.txt";
			  String newtagline = null;
			
			   FileReader fileReader = 
	              new FileReader(basetemlate);

	          BufferedReader bufferedReader = 
	              new BufferedReader(fileReader);

	          while((newtagline = bufferedReader.readLine()) != null) {
	        	  schemanewtag.append(newtagline+"\n");
	           // System.out.println(line);
	          } 
	          
	        //  System.out.println(schemanewtag);
	          fileReader.close();
	          bufferedReader.close();
	          exchange.getIn().setHeader("smcnewtags","");
	          
	          if(schemanewtag.length()>0) {
	        	  if("true".equals(isntofication.trim())) {
	        		  String subject="'BDU "+ source+" "+subjectnewtag+"\"'";
	  				String emailcmd="echo "+ "'"+schemanewtag.toString().trim()+"'"+" | "+"mail -s " +subject+" "+emailtemplate;
	  				//System.out.println(emailcmd);
	  				 ProcessBuilder processBuilderemail = new ProcessBuilder();
	  				  processBuilderemail.command("bash", "-c", emailcmd);
	  				  Process processemail = processBuilderemail.start();
	  				  int exitValemail = processemail.waitFor();
	  					
	  					if (exitValemail == 0) {
	  				
	  						exchange.getIn().setHeader("smcnewtags","");
	  						//System.out.println("Email sent");
	  						
	  					}
	  					processemail.destroy();
	  				
	  					
	  				}
	          }
		}
		
		if("Travelfusion".equals(source)) {
			StringBuffer schemanewtag=new StringBuffer();
			  String basetemlate =  exchange.getContext().resolvePropertyPlaceholders("[(newtags.repository)]");
			  basetemlate=basetemlate+"/tf_newtags.txt";
			  String newtagline = null;
			
			   FileReader fileReader = 
	              new FileReader(basetemlate);

	          BufferedReader bufferedReader = 
	              new BufferedReader(fileReader);

	          while((newtagline = bufferedReader.readLine()) != null) {
	        	  schemanewtag.append(newtagline+"\n");
	           // System.out.println(line);
	          } 
	          
	        //  System.out.println(schemanewtag);
	          fileReader.close();
	          bufferedReader.close();
	          exchange.getIn().setHeader("tfnewtags","");
	          if(schemanewtag.length()>0) {
	        	  if("true".equals(isntofication.trim())) {
	        		  String subject="'BDU "+ source+" "+subjectnewtag+"\"'";
	  				String emailcmd="echo "+ "'"+schemanewtag.toString().trim()+"'"+" | "+"mail -s " +subject+" "+emailtemplate;
	  				//System.out.println(emailcmd);
	  				 ProcessBuilder processBuilderemail = new ProcessBuilder();
	  				  processBuilderemail.command("bash", "-c", emailcmd);
	  				  Process processemail = processBuilderemail.start();
	  				  int exitValemail = processemail.waitFor();
	  					
	  					if (exitValemail == 0) {
	  				
	  						exchange.getIn().setHeader("tfnewtags","");
	  						//System.out.println("Email sent");
	  						
	  					}
	  					processemail.destroy();
	  				
	  					
	  				}
	          }
		}
		
		if("FareLogix".equals(source)) {
			StringBuffer schemanewtag=new StringBuffer();
			  String basetemlate =  exchange.getContext().resolvePropertyPlaceholders("[(newtags.repository)]");
			  basetemlate=basetemlate+"/fl_newtags.txt";
			  String newtagline = null;
			
			   FileReader fileReader = 
	              new FileReader(basetemlate);

	          BufferedReader bufferedReader = 
	              new BufferedReader(fileReader);

	          while((newtagline = bufferedReader.readLine()) != null) {
	        	  schemanewtag.append(newtagline+"\n");
	           // System.out.println(line);
	          } 
	          
	        //  System.out.println(schemanewtag);
	          fileReader.close();
	          bufferedReader.close();
	          exchange.getIn().setHeader("flnewtags","");
	          if(schemanewtag.length()>0) {
	        	  if("true".equals(isntofication.trim())) {
	        		  String subject="'BDU "+ source+" "+subjectnewtag+"\"'";
	  				String emailcmd="echo "+ "'"+schemanewtag.toString().trim()+"'"+" | "+"mail -s " +subject+" "+emailtemplate;
	  				//System.out.println(emailcmd);
	  				 ProcessBuilder processBuilderemail = new ProcessBuilder();
	  				  processBuilderemail.command("bash", "-c", emailcmd);
	  				  Process processemail = processBuilderemail.start();
	  				  int exitValemail = processemail.waitFor();
	  					
	  					if (exitValemail == 0) {
	  				
	  						exchange.getIn().setHeader("flnewtags","");
	  						//System.out.println("Email sent");
	  						
	  					}
	  					processemail.destroy();
	  				
	  					
	  				}
	          }
		}
		
	}
	
	

}
