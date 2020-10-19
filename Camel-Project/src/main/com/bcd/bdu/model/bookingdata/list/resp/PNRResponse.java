package com.bcd.bdu.model.bookingdata.list.resp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PNRList")
@XmlAccessorType (XmlAccessType.FIELD)
public class PNRResponse {
private List<String> PNRId=new ArrayList<String>();

public List<String> getPNRId() {
	return PNRId;
}

public void setPNRId(List<String> pNRId) {
	PNRId = pNRId;
}
	
}
