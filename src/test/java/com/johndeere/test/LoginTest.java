package com.johndeere.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.johndeere.base.WebDriverWrapper;
public class LoginTest extends WebDriverWrapper {

	@Test
	public void validCredentialTest() {

		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLan = new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText("English (Indian)");
		driver.findElement(By.cssSelector("#login-button")).click();

		// wait until navigate to openemr dashboard
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "OpenEMR");
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
