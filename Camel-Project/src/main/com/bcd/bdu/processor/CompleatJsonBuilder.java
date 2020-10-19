package com.bcd.bdu.processor;

import java.net.URL;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class CompleatJsonBuilder implements Processor {
	int partition=0;
	private  int count=0;
	public CompleatJsonBuilder(int count) {
		this.count=count;;
	}
	JSONObject records = new JSONObject();
	JSONArray ja = new JSONArray();
		@Override
		public void process(Exchange exchange) throws Exception {
			// TODO Auto-generated method stub
			 int batchsize=exchange.getProperty("CamelBatchSize", Integer.class);
			 String bduusername = exchange.getContext().resolvePropertyPlaceholders("[(bdu.username)]");
			 String bdupassword = exchange.getContext().resolvePropertyPlaceholders("[(bdu.password)]");
			 String topic = exchange.getContext().resolvePropertyPlaceholders("[(compeat.topic)]");
			 
			 
			String data=exchange.getIn().getBody(String.class);
			 JSONObject value = new JSONObject();
			 value.put("value", data);
			
			 ja.put(value);
			records.put("records", ja);
			
			if(count==batchsize) {
				/*if(partition==5) {
					partition=0;
				}
				
				
				
					
				
				
				String postUrl = topic+"/partitions/"+partition;
				partition++;*/
			//	System.out.println(records);
			//exchange.getIn().setBody(records.toString());
				String postUrl = topic;//+"/partitions/"+partition;
				//System.out.println(records.toString());
				BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
				credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(bduusername.trim(), bdupassword.trim()));
				HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
				ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
				RestTemplate restTemplateget = new RestTemplate(requestFactory);
				try{
					//System.out.println("---------------END of strart producer---------------");
					HttpHeaders headers = new HttpHeaders();
					headers.set("Content-Type", "application/vnd.kafka.json.v1+json");
					//System.out.println("------------------------------");			
					HttpEntity<String> entity = new HttpEntity<String>(records.toString(),headers);
					HttpEntity<String> response = restTemplateget.exchange(
							new URL(postUrl).toURI(), 
					        HttpMethod.POST, 
					        entity, 
					        String.class);		
					
					String testV=new JSONObject(response.getBody()).toString();

					//System.out.println("response body"+testV);
					
			 records = new JSONObject();
			 ja = new JSONArray();
			 count=0;
			}catch(Exception ex) {
				ex.printStackTrace();
				throw new  Exception();
			}
				/* records = new JSONObject();
				 ja = new JSONArray();
				 count=0;*/	
		}
			count++;
		}

}
