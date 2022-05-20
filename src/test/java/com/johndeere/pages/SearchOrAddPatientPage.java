package com.johndeere.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchOrAddPatientPage {

	private By patFrameLocator = By.xpath("//iframe[@name='pat']");
	private By firstnameLocator = By.id("form_fname");
	private By lastnameLocator = By.id("form_lname");
	private By createLocator = By.id("create");
	private By confirmCreateLocator = By.xpath("//input[@value='Confirm Create New Patient']");
	private By modalFrameLocator = By.xpath("//iframe[@id='modalframe']");

	private WebDriver driver;

	public SearchOrAddPatientPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterFirstname(String firstname) {
		driver.switchTo().frame(driver.findElement(patFrameLocator));
		driver.findElement(firstnameLocator).sendKeys(firstname);
	}

	public void enterLastname(String lastname) {
		driver.findElement(lastnameLocator).sendKeys(lastname);
	}

	public void clickOnCreateNewPatient() {
		driver.findElement(createLocator).click();
		driver.switchTo().defaultContent();
	}

	public void clickOnConfirmCreateNewPatient() {
		driver.switchTo().frame(driver.findElement(modalFrameLocator));
		driver.findElement(confirmCreateLocator).click();
		driver.switchTo().defaultContent();
	}

}
