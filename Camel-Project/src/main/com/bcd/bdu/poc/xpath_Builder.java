package com.bcd.bdu.poc;

public class xpath_Builder {
/*
	public void xPathProcessor()
			throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {

		// Create DocumentBuilderFactory for reading xml file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder
				.parse("D:\\Nets-Workspace\\XML_Read\\src\\main\\java\\DataProviders\\XML Files\\2018-09-06 15-52-24Z-BMIVYN.xml");
		
		// Create XPathFactory for creating XPath Object
		XPathFactory xPathFactory = XPathFactory.newInstance();

		// Create XPath object from XPathFactory
		XPath xpath = xPathFactory.newXPath();

		Excel_Mapping_Utility objExcelFile = new Excel_Mapping_Utility();

		// Prepare the path of excel file

		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\DataProviders\\";

		// Call read file method of the class to read data

		try {
			objExcelFile.readExcel(filePath, "Data_Mapping.xlsx", "Complaet_Data_Mapping", 0);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String FP1 = System.getProperty("user.dir") + "\\src\\main\\java\\DataProviders\\";

		// Call read file method of the class to read data

		List<String> arList = new ArrayList<>();
		XPathExpression xPathExpr;
		String xpathexprSTR;
		try {
			arList = new Excel_Mapping_Utility().readExcel(FP1, "Data_Mapping.xlsx", "Complaet_Data_Mapping", 0);

		} // Compile the XPath expression 
		catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < arList.size(); i++) {

			xPathExpr = xpath.compile("/pnr/" + arList.get(i) + "/text()");

			// System.out.println(xPathExpr.toString());

			xpathexprSTR = "/pnr/" + arList.get(i);

			System.out.print(xpathexprSTR + ": ");

			// XPath text example : executing xpath expression in java
			Object result = xPathExpr.evaluate(doc, XPathConstants.NODESET);
			printXpathResult(result);

		}

	}

	public void printXpathResult(Object result) {
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeValue());
			// System.out.println();
		}
	}

	public void Create_xPathExprn(Object result) {
		NodeList nodes = (NodeList) result;
		System.out.println(nodes.getLength());
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeValue());
		}
	}

	public static void main(String[] args)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		xpath_Builder xPathExample = new xpath_Builder();
		xPathExample.xPathProcessor();
	}*/
} 
