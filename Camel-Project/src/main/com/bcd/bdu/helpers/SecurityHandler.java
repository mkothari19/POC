package com.bcd.bdu.helpers;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.concurrent.atomic.AtomicLong;

public class SecurityHandler {
	private static final AtomicLong LT_MICRO_SEC = new AtomicLong();
	private static final long FNV1_64_INIT = 0xcbf29ce484222325L;
	private static final long FNV1_PRIME_64 = 839381L;

	public static long hashFNV1a(byte[] data){
		long hash = FNV1_64_INIT;
		for(int i=0; i < data.length; i++){
			hash *= (data[i] & 0xff);
			hash *= FNV1_PRIME_64;
		}
		return hash;
	}

	public static long utcAtomicTimestamp(){
		Instant instant = Instant.now(Clock.systemUTC());
		long now = instant.get(ChronoField.MICRO_OF_SECOND); //1M ID per seconds
    	while(true) {
      		long lastTime = LT_MICRO_SEC.get();
      		if (lastTime >= now) now = lastTime+1;
      		if (LT_MICRO_SEC.compareAndSet(lastTime, now)) return now;
    	}		
	}
	public static String fileNaming(String resourceL){
		  DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    	  String timestamp = format.format(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));
    	  String filename=timestamp+"Z-"+resourceL;
    	  return filename;
	}
}
