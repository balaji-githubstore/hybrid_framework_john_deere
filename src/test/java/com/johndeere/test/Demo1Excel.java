package com.johndeere.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo1Excel {

	public static void main(String[] args) throws IOException {
		
		FileInputStream file=new FileInputStream("test_data/open_emr_test_data.xlsx"); 
		
		XSSFWorkbook book=new XSSFWorkbook(file); 
		XSSFSheet sheet= book.getSheet("invalidCredentialTest");
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		int cellCount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] main=new Object[rowCount-1][cellCount];
		
		DataFormatter format=new DataFormatter();
		
		for(int r=1;r<rowCount;r++)
		{
			for(int c=0;c<cellCount;c++)
			{
				main[r-1][c]=format.formatCellValue(sheet.getRow(r).getCell(c));
			}
		}
		 
		System.out.println();
	
	}

}
