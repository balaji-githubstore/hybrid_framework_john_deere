package com.johndeere.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
	
public class DataUtils {
	@DataProvider
	public Object[][] commonDataProvider(Method method) throws IOException {
		
		String currentTestMethodName=method.getName();
		Object[][] main= ExcelUtils.getSheetIntoTwoDimArray("test_data/open_emr_test_data.xlsx", currentTestMethodName);
		return main;
	}
	
//	@DataProvider
//	public Object[][] invalidCredentialData() throws IOException
//	{
//		Object[][] main=ExcelUtils.getSheetIntoTwoDimArray("test_data/open_emr_test_data.xlsx", "invalidCredentialTest");
//		return main;
//	}
//
//	@DataProvider
//	public Object[][] validCredentialData() throws IOException {
//		Object[][] main= ExcelUtils.getSheetIntoTwoDimArray("test_data/open_emr_test_data.xlsx", "validCredentialTest");
//		return main;
//	}
	
	
//	@DataProvider
//	public Object[][] validCredentialData() {
//		Object[][] main = new Object[3][4];
//
//		main[0][0] = "admin";
//		main[0][1] = "pass";
//		main[0][2] = "English (Indian)";
//		main[0][3] = "OpenEMR";
//
//		main[1][0] = "accountant";
//		main[1][1] = "accountant";
//		main[1][2] = "Danish";
//		main[1][3] = "OpenEMR";
//
//		main[2][0] = "physician";
//		main[2][1] = "physician";
//		main[2][2] = "English (Indian)";
//		main[2][3] = "OpenEMR";
//
//		return main;
//	}



}






