package com.bcd.bdu.poc;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class TokenCreation  {
  public  static void main(String args[]) throws Exception {        
    String consumer_key = "DataServicesBDURaw101";
    String secret_key ="73D15512-A8F8-46A5-9809-589216025B38";
    String enc_key = "+VYq+anihPobAsUfTHD0HW+IWmW68WdYzdhfOZSdTMo=";

	 /* String consumer_key = "DataServicesBDU201";
	    String secret_key ="4711843A-5EE0-4A74-83CD-B28EE0DC978A";
	    String enc_key = "EChFMfYltMXe9U9PAG2XPsl71IGKOC4r0QAE80LdkTk=";*/
	
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
		System.out.println("Token "+cipherText_str_btoa);
   
  }
}
