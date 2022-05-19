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
import com.johndeere.utilities.DataUtils;

public class PatientTest extends WebDriverWrapper{
	
	@Test(dataProviderClass = DataUtils.class,dataProvider = "commonDataProvider",groups = {"patient","high"})
	public void addPaitentTest(String username,String password,String language,String firstname,String lastname,String dob,String gender,String expectedAlert,String expectedPatient)
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
		new Select(driver.findElement(By.id("form_sex"))).selectByVisibleText(gender);
		driver.findElement(By.id("create")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		driver.switchTo().defaultContent();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.alertIsPresent());
		
		String actualAlertText=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		
		//presence of element
		if(driver.findElements(By.xpath("//div[@class='closeDlgIframe']")).size()>0)
		{
			driver.findElement(By.xpath("//div[@class='closeDlgIframe']")).click();
		}
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='pat']")));
		
		String actualValue=driver.findElement(By.xpath("//h2[contains(text(),'Record')]")).getText().trim();
		
		Reporter.log("Alert text: "+actualAlertText);
		Reporter.log("Added Patient Name: "+actualValue);
		
		Assert.assertTrue(actualAlertText.contains(expectedAlert)); //expect true
		Assert.assertEquals(actualValue, expectedPatient);

	}

}
