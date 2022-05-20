package com.johndeere.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	private By patientLocator = By.xpath("//div[text()='Patient']");
	private By newSearchLocator = By.xpath("//div[text()='New/Search']");

	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForPresenceOfPatientMenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.presenceOfElementLocated(patientLocator));
	}

	public String getDashboardPageTitle() {
		return driver.getTitle();
	}

	public void clickOnPatient() {
		driver.findElement(patientLocator).click();
	}

	public void clickOnNewSearch() {
		driver.findElement(newSearchLocator).click();
	}

}
