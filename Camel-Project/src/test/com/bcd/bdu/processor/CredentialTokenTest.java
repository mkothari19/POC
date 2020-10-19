package com.bcd.bdu.processor;

import org.junit.Test;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class CredentialTokenTest {

  @Test
  public void testBCDTokenCreation() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
        String localtimestamp = format.format(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));

        Instant ins = Instant.now(Clock.systemUTC());
        String timestamp = format.format(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));

        String consumer_key = "MODataServicesBDU";
        String secret_key = "CC5DEFF4-CC57-4E51-A2D4-9C63FBB4D276";
        String enc_key = "LuOMaGt3JcgPw2zLnMjHI3v3Z/ctmImCUZxCR2NERgo=";
        byte[] decodedKey = Base64.getDecoder().decode(enc_key);

        System.out.println(timestamp);
        String str = consumer_key + "|" + secret_key + "|" + timestamp;

        SecretKeySpec key = new SecretKeySpec(decodedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = new byte[cipher.getOutputSize(str.length())];

        int ctLength = cipher.update(str.getBytes(), 0, str.length(), cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        String cipherText_str = Base64.getEncoder().encodeToString(cipherText);
        System.out.println(cipherText_str);
        String cipherText_str_btoa = Base64.getEncoder().encodeToString(cipherText_str.getBytes());
        System.out.println(cipherText_str_btoa);
  }

}
