package com.johndeere.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientDashboardPage {

	private By closeHBDLocator = By.xpath("//div[@class='closeDlgIframe']");
	private By patFrameLocator = By.xpath("//iframe[@name='pat']");
	private By patientDetailLocator = By.xpath("//h2[contains(text(),'Record')]");

	private WebDriver driver;

	public PatientDashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTextAndHandleAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.alertIsPresent());

		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		return alertText;
	}

	public void clickOnCloseHappyBirthday() {
		if (driver.findElements(closeHBDLocator).size() > 0) {
			driver.findElement(closeHBDLocator).click();
		}
	}

	public String getAddedPatientDetail() {
		driver.switchTo().frame(driver.findElement(patFrameLocator));
		return driver.findElement(patientDetailLocator).getText().trim();
	}
}
