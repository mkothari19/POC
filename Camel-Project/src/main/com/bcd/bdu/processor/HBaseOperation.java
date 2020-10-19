package com.bcd.bdu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
/*import org.apache.camel.component.hbase.HBaseAttribute;
import org.apache.camel.component.hbase.HBaseConstants;*/

public class HBaseOperation implements Processor {

	public void process(Exchange exchange) {
        String pnr = (String) exchange.getIn().getBody();
        String rowkey = (String) exchange.getIn().getHeader("rowkey");
        String qualifier = (String) exchange.getIn().getHeader("qualifier");

     /*   exchange.getIn().setHeader(HBaseAttribute.HBASE_ROW_ID.asHeader(), rowkey);
        exchange.getIn().setHeader(HBaseAttribute.HBASE_FAMILY.asHeader(), "bookingdata");
        exchange.getIn().setHeader(HBaseAttribute.HBASE_QUALIFIER.asHeader(), qualifier);
        exchange.getIn().setHeader(HBaseAttribute.HBASE_VALUE.asHeader(), pnr);
        exchange.getIn().setHeader(HBaseConstants.OPERATION, HBaseConstants.PUT);*/

	}

}
