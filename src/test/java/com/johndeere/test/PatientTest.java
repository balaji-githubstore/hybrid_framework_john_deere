package com.johndeere.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.johndeere.base.WebDriverWrapper;
import com.johndeere.utilities.DataUtils;

public class PatientTest extends WebDriverWrapper{
	
	@Test(dataProviderClass = DataUtils.class,dataProvider = "commonDataProvider")
	public void addPaitentTest(String username,String password,String language,String firstname,String lastname,String dob,String gender,String expectedValue)
	{
		driver.findElement(By.id("authUser")).sendKeys(username);
		driver.findElement(By.id("clearPass")).sendKeys(password);
		Select selectLan = new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText(language);
		driver.findElement(By.cssSelector("#login-button")).click();
		
		driver.findElement(By.xpath("//div[text()='Patient']")).click();
		driver.findElement(By.xpath("//div[text()='New/Search']")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='pat']")));
		driver.findElement(By.id("form_fname")).sendKeys(firstname);
		driver.findElement(By.id("form_lname")).sendKeys(lastname);
		driver.findElement(By.id("form_DOB")).sendKeys(dob);
		
//		8.	Add the first name, last name
//		9.	Update DOB as today's date driver.findElement(By.id("form_DOB")).sendKeys("2021-12-06");
//		10.	Update the gender
//		11.	Click on the create new patient button above the form
//		12.	Click on confirm create new patient button.
//		13.	Print the text from alert box (if any error before handling alert add 5 sec wait)
//		14.	Handle alert
//		15.	Close the Happy Birthday popup
//		16.	Get the added patient name and print in the console.

	}

}
