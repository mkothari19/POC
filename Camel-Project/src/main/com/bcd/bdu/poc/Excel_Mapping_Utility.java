package com.bcd.bdu.poc;

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/*import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

//import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

 

public class Excel_Mapping_Utility {

 
/*
                Workbook RDWorkbook;

 

                public List<String> readExcel(String filePath, String fileName, String sheetName, int colIndex) throws IOException, InvalidFormatException {

                                List<String> myList = new ArrayList<>();

                                // Create an object of File class to open xlsx file

                               

 

                                File XLfile = new File(filePath + "\\" + fileName);

 

                                // Create an object of FileInputStream class to read excel file

 

                                FileInputStream inputStream = new FileInputStream(XLfile);

 

                                // Workbook RDWorkbook = null;

 

                                Workbook RDWorkbook = new XSSFWorkbook(inputStream);

 

                                // Read sheet inside the workbook by its name

 

                                Sheet XLSheet = RDWorkbook.getSheetAt(1); // (sheetName);

 

                                // Find number of rows in excel file

 

                                int rowCount = (XLSheet.getLastRowNum() - XLSheet.getFirstRowNum());

                               

                               

                                // Create a loop over all the rows of excel file to read it

 

                                for (int i = 1; i < rowCount + 1; i++) {

                                                               

                                                Row row = XLSheet.getRow(i);

                                                if (row.getCell(colIndex).getStringCellValue() != null && row.getCell(colIndex).getStringCellValue().length() != 0 ) {

                                                               

                                                                String Value = row.getCell(colIndex).getStringCellValue();

                                                                if (!Value.equals("NULL") ){

                                                                myList.add(Value);

                                                                //System.out.println(Value);

                                                                }                              } }

                                return myList;

                                }

 

                // Main function is calling readExcel function to read data from excel file

 

                public static void main(String... strings) throws IOException {

 

                                // Create an object of Excel_Mapping_Utility class

 

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

 

                }*/

 

}