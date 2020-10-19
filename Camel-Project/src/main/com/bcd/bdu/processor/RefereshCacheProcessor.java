package com.bcd.bdu.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.bcd.bdu.helpers.HiveCache;

public class RefereshCacheProcessor implements Processor {
	Map<String,String> citymap=new HashMap<String,String>();
	 private static final Logger LOGGER = Logger.getLogger(RefereshCacheProcessor.class);
	public RefereshCacheProcessor(Map<String,String> citymap) {
		this.citymap=citymap;
	}
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		citymap=HiveCache.getInstance().getCityCache();
		
		LOGGER.info("***************CACHE UPDATED************* " + citymap.size());
		}

	
}
