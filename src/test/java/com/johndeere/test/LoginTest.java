package com.johndeere.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	@Test
	public void validCredentialTest()
	{
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get("http://demo.openemr.io/b/openemr");
		
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLan=new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText("English (Indian)");	
		driver.findElement(By.cssSelector("#login-button")).click();
		
		//wait until navigate to openemr dashboard
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "OpenEMR");
	}
	
	@Test
	public void invalidCredentialTest()
	{
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get("http://demo.openemr.io/b/openemr");
		
		driver.findElement(By.id("authUser")).sendKeys("admin123");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLan=new Select(driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText("English (Indian)");	
		driver.findElement(By.cssSelector("#login-button")).click();
		
		String actualError= driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText();
		
		Assert.assertEquals(actualError, "Invalid username or password");
	}
}








