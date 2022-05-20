package com.johndeere.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.johndeere.base.WebDriverWrapper;
import com.johndeere.pages.DashboardPage;
import com.johndeere.pages.LoginPage;
import com.johndeere.utilities.DataUtils;

public class LoginTest extends WebDriverWrapper {

	@Test(dataProviderClass = DataUtils.class, dataProvider = "commonDataProvider", groups = { "login", "high" })
	public void validCredentialTest(String username, String password, String language, String expectedTitle) {

		LoginPage login = new LoginPage(driver);

		login.enterUsername(username);
		test.log(Status.INFO, "Entered Username :" + username);

		login.enterPassword(password);
		test.log(Status.INFO, "Entered Password :" + password);

		login.selectLanguageByText(language);
		test.log(Status.INFO, "Selected language:" + language);

		login.clickOnLogin();
		test.log(Status.INFO, "Clicked on Login:");

		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.waitForPresenceOfPatientMenu();
		test.log(Status.INFO, "Xpath is present - //div[text()='Patient'] ");

		String actualTitle = dashboard.getDashboardPageTitle();
		test.log(Status.INFO, "Actual Title:" + actualTitle);

		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(dataProviderClass = DataUtils.class, dataProvider = "commonDataProvider", groups = { "login", "low" })
	public void invalidCredentialTest(String username, String password, String language, String expectedError) {
		LoginPage login = new LoginPage(driver);
		login.enterUsername(username);
		login.enterPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();

		String actualError = login.getInvalidErrorMessage();

		Assert.assertEquals(actualError, expectedError);
	}
}
