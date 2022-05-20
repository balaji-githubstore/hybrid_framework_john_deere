package com.johndeere.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverKeywords {

	private WebDriver driver;

	public WebDriverKeywords(WebDriver driver) {
		this.driver = driver;
	}

	public void clickUsingLocator(By locator) {
		driver.findElement(locator).click();
	}

	public void enterUsingLocator(By locator,String text)
	{
		driver.findElement(locator).sendKeys(text);
	}
	
	public String getTextUsingLocator(By locator) {
		return driver.findElement(locator).getText();
	}
}
