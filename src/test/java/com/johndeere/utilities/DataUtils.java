package com.johndeere.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataUtils {
	
	@DataProvider
	public Object[][] invalidCredentialData() throws IOException
	{
		Object[][] main=ExcelUtils.getSheetIntoTwoDimArray("test_data/open_emr_test_data.xlsx", "invalidCredentialTest");
		return main;
	}

	@DataProvider
	public Object[][] validCredentialData() throws IOException {
		Object[][] main= ExcelUtils.getSheetIntoTwoDimArray("test_data/open_emr_test_data.xlsx", "validCredentialTest");
		return main;
	}

}
