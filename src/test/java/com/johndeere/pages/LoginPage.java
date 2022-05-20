package com.johndeere.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.johndeere.base.WebDriverKeywords;

public class LoginPage extends WebDriverKeywords {
	private By usernameLocator = By.id("authUser");
	private By passwordLocator = By.id("clearPass");
	private By languageLocator = By.xpath("//select[@name='languageChoice']");
	private By loginLocator = By.cssSelector("#login-button");
	private By errorLocator = By.xpath("//*[contains(text(),'Invalid')]");
	private By ackLicCertLocator = By.partialLinkText("Certification");

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterUsername(String username) {
		enterUsingLocator(usernameLocator, username);
	}

	public void enterPassword(String password) {
		enterUsingLocator(passwordLocator, password);
	}

	public void selectLanguageByText(String language) {
		Select selectLan = new Select(driver.findElement(languageLocator));
		selectLan.selectByVisibleText(language);
	}

	public void clickOnLogin() {
		clickUsingLocator(loginLocator);
	}

	public String getInvalidErrorMessage() {
		return getTextUsingLocator(errorLocator);
	}

	public void clickOnAckLicCert() {
		clickUsingLocator(ackLicCertLocator);
	}

}
