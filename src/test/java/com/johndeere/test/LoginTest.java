package com.johndeere.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.johndeere.base.WebDriverWrapper;

public class LoginTest extends WebDriverWrapper {

	@DataProvider
	public Object[][] validCredentialData() {
		Object[][] main = new Object[3][4];

		main[0][0] = "admin";
		main[0][1] = "pass";
		main[0][2] = "English (Indian)";
		main[0][3] = "OpenEMR";

		main[1][0] = "accountant";
		main[1][1] = "accountant";
		main[1][2] = "Danish";
		main[1][3] = "OpenEMR";

		main[2][0] = "physician";
		main[2][1] = "physician";
		main[2][2] = "English (Indian)";
		main[2][3] = "OpenEMR";

		return main;
	}

	@Test(dataProvider = "validCredentialData")
	public void validCredentialTest(String username, String password, String language, String expectedTitle) {

		driver.findElement(By.id("authUser")).sendKeys(username);
		driver.findElement(By.id("clearPass")).sendKeys(password);
		Select selectLan = new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText(language);
		driver.findElement(By.cssSelector("#login-button")).click();

		// wait until navigate to openemr dashboard
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test
	public void invalidCredentialTest() {
		driver.findElement(By.id("authUser")).sendKeys("admin123");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLan = new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText("English (Indian)");
		driver.findElement(By.cssSelector("#login-button")).click();

		String actualError = driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText();

		Assert.assertEquals(actualError, "Invalid username or password");
	}
}
