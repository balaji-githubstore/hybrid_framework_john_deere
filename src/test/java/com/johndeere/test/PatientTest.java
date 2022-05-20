package com.johndeere.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.johndeere.base.WebDriverWrapper;
import com.johndeere.pages.DashboardPage;
import com.johndeere.pages.LoginPage;
import com.johndeere.pages.PatientDashboardPage;
import com.johndeere.pages.SearchOrAddPatientPage;
import com.johndeere.utilities.DataUtils;

public class PatientTest extends WebDriverWrapper {

	@Test(dataProviderClass = DataUtils.class, dataProvider = "commonDataProvider", groups = { "patient", "high" })
	public void addPaitentTest(String username, String password, String language, String firstname, String lastname,
			String dob, String gender, String expectedAlert, String expectedPatient) {
		LoginPage login = new LoginPage(driver);
		login.enterUsername(username);
		login.enterPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.clickOnPatient();
		dashboard.clickOnNewSearch();

		SearchOrAddPatientPage sadp = new SearchOrAddPatientPage(driver);
		sadp.enterFirstname(firstname);
		sadp.enterLastname(lastname);

		//add it to page object
		driver.findElement(By.id("form_DOB")).sendKeys(dob);
		new Select(driver.findElement(By.id("form_sex"))).selectByVisibleText(gender);
		sadp.clickOnCreateNewPatient();

		sadp.clickOnConfirmCreateNewPatient();

		PatientDashboardPage pdp = new PatientDashboardPage(driver);

		String actualAlertText = pdp.getTextAndHandleAlert();

		pdp.clickOnCloseHappyBirthday();

		String actualValue = pdp.getAddedPatientDetail();
		Reporter.log("Alert text: " + actualAlertText);
		Reporter.log("Added Patient Name: " + actualValue);

		Assert.assertTrue(actualAlertText.contains(expectedAlert)); // expect true
		Assert.assertEquals(actualValue, expectedPatient);

	}

}
