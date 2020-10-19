package com.bcd.bdu.poc;

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

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaRestClient implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		 String data=exchange.getIn().getBody(String.class);
		 JSONObject value = new JSONObject();
		 JSONObject records = new JSONObject();
		 value.put("value", data);
		 JSONArray ja = new JSONArray();
		 ja.put(value);
		 records.put("records", ja);
		System.out.println(records);
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("bduadmin", "8qGP-^ukv8UH4URb"));
		HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplateget = new RestTemplate(requestFactory);
		String postUrl = "http://uscdc01tlmap005:8082/topics/%2Fdata%2Fhs%2Ftsnt%2Ffroff%2Fbdu%2Fstreams%2Ftsnt_stream_pnrsdiffsrc%3Acompleatpnr";
		try{
			System.out.println("---------------END of strart producer---------------");
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/vnd.kafka.json.v1+json");
			System.out.println("------------------------------");			
			HttpEntity<String> entity = new HttpEntity<String>(records.toString(),headers);
			HttpEntity<String> response = restTemplateget.exchange(
					new URL(postUrl).toURI(), 
			        HttpMethod.POST, 
			        entity, 
			        String.class);		
			
			String testV=new JSONObject(response.getBody()).toString();

			System.out.println("response body"+testV);
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}

}
