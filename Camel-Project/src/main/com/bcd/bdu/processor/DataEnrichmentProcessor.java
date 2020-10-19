package com.bcd.bdu.processor;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.XML;

import com.bcd.bdu.util.BduRestApiUtil;

public class DataEnrichmentProcessor extends BduRestApiUtil implements Processor {

	public ConcurrentHashMap<String, String> oldata = null;

	public DataEnrichmentProcessor(ConcurrentHashMap<String, String> oldata) {
		this.oldata = oldata;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
	
		String enricherTagsFilePath = null;
		JSONObject enrichedJson = null;
		JSONObject rawJson =new JSONObject(oldata.get(exchange.getIn().getHeader("pnr_id_os", String.class)));
		String instance = (String) rawJson.get("instance");
		JSONObject richJson = null;
		String body=exchange.getIn().getBody(String.class);

		switch (instance.toLowerCase()) {
		case "farelogix":
			enricherTagsFilePath = exchange.getContext().resolvePropertyPlaceholders("[(fl.enrich.tag.conf.file)]");
			exchange.getIn().setBody(rawJson);
			richJson = XML.toJSONObject(body,true);
			enrichedJson = getEnrichedJson(rawJson, richJson, enricherTagsFilePath, true);
			oldata.remove(exchange.getIn().getHeader("pnr_id_os", String.class));
			exchange.getIn().setBody(enrichedJson.toString());
			break;
		case "travelfusion":
			enricherTagsFilePath = exchange.getContext().resolvePropertyPlaceholders("[(tf.enrich.tag.conf.file)]");
			exchange.getIn().setBody(rawJson);
			richJson = XML.toJSONObject(body,true);
			enrichedJson = getEnrichedJson(rawJson, richJson, enricherTagsFilePath, true);
			oldata.remove(exchange.getIn().getHeader("pnr_id_os", String.class));
			exchange.getIn().setBody(enrichedJson.toString());
			break;
		case "airbnb":
			enricherTagsFilePath = exchange.getContext().resolvePropertyPlaceholders("[(db.enrich.tag.conf.file)]");
			exchange.getIn().setBody(rawJson);
			richJson=new JSONObject(body);
			enrichedJson = getEnrichedJson(rawJson, richJson, enricherTagsFilePath, true);
			oldata.remove(exchange.getIn().getHeader("pnr_id_os", String.class));
			exchange.getIn().setBody(enrichedJson.toString());
			break;
		}

		

	}

}
