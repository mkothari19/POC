package com.bcd.bdu.poc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;
public class XMLToJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		String str=	readFileAsString("D:\\landing\\data\\hs\\tsnt\\froff\\bdu\\compleatcamel\\transform\\2018-09-06 15-52-24Z-ATCRNZ.xml");
		//converting xml to json
				JSONObject obj = XML.toJSONObject(str);
				JSONObject pnr=obj.getJSONObject("pnr");
				
				System.out.println(obj.getJSONObject("pnr").toString());
					} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}
