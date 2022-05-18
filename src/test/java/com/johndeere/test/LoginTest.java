package com.johndeere.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.johndeere.base.WebDriverWrapper;
import com.johndeere.utilities.DataUtils;

public class LoginTest extends WebDriverWrapper {
	
	@Test(dataProviderClass = DataUtils.class,dataProvider = "validCredentialData")
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

	@Test(dataProviderClass = DataUtils.class,dataProvider = "invalidCredentialData")
	public void invalidCredentialTest(String username, String password, String language, String expectedError) {
		driver.findElement(By.id("authUser")).sendKeys(username);
		driver.findElement(By.id("clearPass")).sendKeys(password);
		Select selectLan = new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText(language);
		driver.findElement(By.cssSelector("#login-button")).click();

		String actualError = driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText();

		Assert.assertEquals(actualError, expectedError);
	}
}
