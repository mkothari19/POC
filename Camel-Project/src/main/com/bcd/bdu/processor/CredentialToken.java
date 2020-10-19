package com.bcd.bdu.processor;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

public class CredentialToken implements Processor {
	
	
	JSONObject awsjson;
	
	public CredentialToken(JSONObject awsjson) {
		
		this.awsjson=awsjson;
	}
  public void process(Exchange exchange) throws Exception {   
	  String consumer_key =null;
	  String secret_key=null;
	  String enc_key=null;
	
     consumer_key =awsjson.getString("consumer_key");// exchange.getContext().resolvePropertyPlaceholders("[(rich.consumer.key)]");
     secret_key =awsjson.getString("secret_key"); //exchange.getContext().resolvePropertyPlaceholders("[(rich.secret.key)]");
     enc_key = awsjson.getString("encryption_key");//exchange.getContext().resolvePropertyPlaceholders("[(rich.encryption.key)]");
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
    Instant ins = Instant.now(Clock.systemUTC());
    String timestamp = format.format(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));

    byte[] decodedKey = Base64.getDecoder().decode(enc_key);
    String str = consumer_key + "|" + secret_key + "|" + timestamp;

    SecretKeySpec key = new SecretKeySpec(decodedKey, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] cipherText = new byte[cipher.getOutputSize(str.length())];

    int ctLength = cipher.update(str.getBytes(), 0, str.length(), cipherText, 0);
    ctLength += cipher.doFinal(cipherText, ctLength);

    String cipherText_str = Base64.getEncoder().encodeToString(cipherText);
    String cipherText_str_btoa = Base64.getEncoder().encodeToString(cipherText_str.getBytes());
		//System.out.println("Token "+cipherText_str_btoa);
    exchange.getIn().setHeader("Authorization", "Basic " + cipherText_str_btoa);
  }
}
