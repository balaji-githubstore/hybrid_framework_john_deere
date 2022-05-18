package com.johndeere.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataUtils {
	
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
	
	@DataProvider
	public Object[][] commonDataProvider(Method method) throws IOException {
		
		String testMethodName=method.getName();
		Object[][] main= ExcelUtils.getSheetIntoTwoDimArray("test_data/open_emr_test_data.xlsx", testMethodName);
		return main;
	}

}






