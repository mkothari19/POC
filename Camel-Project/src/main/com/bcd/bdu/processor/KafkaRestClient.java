package com.bcd.bdu.processor;

import java.net.URL;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
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
int partition=0;
private   int count=0;
 public KafkaRestClient(int count) {
	 this.count=count;
 }

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		 int batchsize=exchange.getProperty("CamelBatchSize", Integer.class);
		 if(count==batchsize) {
		String data=exchange.getIn().getBody(String.class);
		System.out.println(data);
		 String bduusername = exchange.getContext().resolvePropertyPlaceholders("[(bdu.username)]");
		 String bdupassword = exchange.getContext().resolvePropertyPlaceholders("[(bdu.password)]");
		 String topic = exchange.getContext().resolvePropertyPlaceholders("[(compeat.topic)]");
		 JSONObject value = new JSONObject();
		
		
		if(partition==5) {
			partition=0;
		}
		
		
		
			
		
		
		String postUrl = topic+"/partitions/"+partition;
		partition++;
	
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(bduusername.trim(), bdupassword.trim()));
		HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplateget = new RestTemplate(requestFactory);
		try{
			System.out.println("---------------END of strart producer---------------");
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/vnd.kafka.json.v1+json");
			System.out.println("------------------------------");			
			HttpEntity<String> entity = new HttpEntity<String>(data,headers);
			HttpEntity<String> response = restTemplateget.exchange(
					new URL(postUrl).toURI(), 
			        HttpMethod.POST, 
			        entity, 
			        String.class);		
			
			String testV=new JSONObject(response.getBody()).toString();

			System.out.println("response body"+testV);
			count=0;
			
		}catch(Exception e){
			count=0;
			e.printStackTrace();
			throw new  Exception();
			
		}	
	
		}
		 count++;
	}

}
