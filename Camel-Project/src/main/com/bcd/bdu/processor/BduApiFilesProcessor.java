package com.bcd.bdu.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.bcd.bdu.util.BduUtils;

public class BduApiFilesProcessor implements Processor {

	private String instance = null;

	public BduApiFilesProcessor(String instance) {
		this.instance = instance;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		String filename = exchange.getIn().getHeader("CamelFileName", String.class);

		switch (instance) {
		case "farelogix":
			if (filename.contains("farelogix-")) {
				String filearr[] = filename.split("farelogix-");

				String temp = filearr[1];
				String pnr_id_os_date[] = temp.split("_");
				String pnr_id_os = pnr_id_os_date[0];
				String olddate = pnr_id_os_date[1].replaceAll(".xml", "");
				DateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				Date date = sdf.parse(olddate);
				exchange.getIn().setHeader("fl_currentdate", new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date));
				exchange.getIn().setHeader("fl_pnr_id_os", pnr_id_os);
			} else if (filename.contains("fl_")) {
				String filearr[] = filename.split("_");
				exchange.getIn().setHeader("tf_currentdate", filearr[3].replaceAll(".xml", ""));
				exchange.getIn().setHeader("tf_pnr_id_os", filearr[2]);
				exchange.getIn().setHeader("isprocess", "true");

			} else {
				String cdate = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss");
				exchange.getIn().setHeader("tf_currentdate", cdate);
				exchange.getIn().setHeader("tf_pnr_id_os", "NA");

			}
			break;
		case "travelfussion":
			if (filename.contains("travelfussion-")) {
				String filearr[] = filename.split("travelfussion-");

				String temp = filearr[1];
				String pnr_id_os_date[] = temp.split("_");
				String pnr_id_os = pnr_id_os_date[0];
				String olddate = pnr_id_os_date[1].replaceAll(".xml", "");
				DateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				Date date = sdf.parse(olddate);
				exchange.getIn().setHeader("tf_currentdate", new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date));
				exchange.getIn().setHeader("tf_pnr_id_os", pnr_id_os);
				exchange.getIn().setHeader("isprocess", "true");

			} else if (filename.contains("tf_")) {
				String filearr[] = filename.split("_");
				exchange.getIn().setHeader("tf_currentdate", filearr[3].replaceAll(".xml", ""));
				exchange.getIn().setHeader("tf_pnr_id_os", filearr[2]);
				exchange.getIn().setHeader("isprocess", "true");
			} else {
				String cdate = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss");
				exchange.getIn().setHeader("tf_currentdate", cdate);
				exchange.getIn().setHeader("tf_pnr_id_os", "NA");
				exchange.getIn().setHeader("isprocess", "true");

			}
			break;
		case "airbnb":
			if (filename.contains("directbooking-")) {
				String filearr[] = filename.split("directbooking-");
               String id_os=exchange.getIn().getHeader("db_pnr_id_os",String.class);
				String temp = filearr[1];
				String pnr_id_os_date[] = temp.split(id_os+"-");

				String olddate = pnr_id_os_date[1].replaceAll(".xml", "");
				DateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				Date date = sdf.parse(olddate);
				exchange.getIn().setHeader("db_currentdate", new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date));

			} else if (filename.contains("db_")) {
				String filearr[] = filename.split("_");
				exchange.getIn().setHeader("db_currentdate", filearr[3].replaceAll(".xml", ""));
			} else {
				String cdate = BduUtils.getInstance().getUTCDateAndTime("yyyy-MM-dd-HH-mm-ss");
				exchange.getIn().setHeader("db_currentdate", cdate);

			}
			break;
		}

	}
}
