package com.bcd.bdu.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.google.common.collect.Sets;

public class SchemaEvaluationProcessorSMC implements Processor  {

	 Set<String> schema = Sets.newHashSet();
	
	 public SchemaEvaluationProcessorSMC(Set<String> schema) {
		 this.schema=schema;
		
	 }
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		try {
			
		  String camelfilenamepath=exchange.getIn().getHeader("CamelFileAbsolutePath",String.class);
		   ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", "xmlstarlet el"+" "+camelfilenamepath);
		  Process process = processBuilder.start();
		  Set<String> xml = Sets.newHashSet();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				xml.add(line);
			}
			
			int exitVal = process.waitFor();
			Set<String> diff=null;
			if (exitVal == 0) {
			
				 diff = Sets.difference(xml,schema); 
				
			} 
			StringBuffer strbuilder=new StringBuffer();
			for(String diifstr:diff) {
				strbuilder.append(diifstr+"\n");
				
			}
			
			if(strbuilder.length()>0) {
				 Set<String> schemanewtag = Sets.newHashSet();
				  String basetemlate =  exchange.getContext().resolvePropertyPlaceholders("[(newtags.repository)]");
				  basetemlate=basetemlate+"/smc_newtags.txt";
				  String newtagline = null;
				
				   FileReader fileReader = 
		              new FileReader(basetemlate);

		          BufferedReader bufferedReader = 
		              new BufferedReader(fileReader);

		          while((newtagline = bufferedReader.readLine()) != null) {
		        	  schemanewtag.add(newtagline.trim());
		           //  System.out.println(line);
		          }   
		          fileReader.close();
		          bufferedReader.close();
		          
		          Set<String> diffnewtag=null;
					if (exitVal == 0) {
					
						diffnewtag = Sets.difference(diff,schemanewtag); 
						
					} 
					StringBuffer strbuildernewtag=new StringBuffer();
					for(String diifstr:diffnewtag) {
						strbuildernewtag.append(diifstr+"\n");
						
					}
		         if(strbuildernewtag.length()>0) { 
			exchange.getIn().setHeader("newxmltags", strbuildernewtag);
			exchange.getIn().setHeader("isnewtag", "yes");
		         }else {
					 exchange.getIn().setHeader("isnewtag", "no");
					}
			
		}
			
			process.destroy();
			
		}catch(Exception ex) {
			 exchange.getIn().setHeader("isnewtag", "no");
			 //exchange.getIn().setHeader("isnewtag", "no");
			 
		//	 System.out.println("Exception ex "+ex);
		}
	   //exchange.getIn().setHeader("isnewtag", "yes");
	}


}
