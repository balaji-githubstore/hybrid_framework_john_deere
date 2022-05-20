package com.johndeere.labs.needtodelete;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Example of running a TestNG test without using Sauce Bindings.
 */
public class Demo1 {
    protected RemoteWebDriver driver;

//    @BeforeMethod
//    public void setup(Method method) throws MalformedURLException {
//        MutableCapabilities sauceOptions = new MutableCapabilities();
//        sauceOptions.setCapability("username", "oauth-balaji0017-cb7f4");
//        sauceOptions.setCapability("accessKey", "4e5bcec0-9b8b-46e3-99ed-b2e14db45bd3");
//        sauceOptions.setCapability("name", method.getName());
//        sauceOptions.setCapability("browserVersion", "latest");
//
//        ChromeOptions options = new ChromeOptions();
//        options.setCapability("sauce:options", sauceOptions);
//        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
//        driver = new RemoteWebDriver(url, options);
//    }
    @BeforeMethod
    public void setup(Method method) throws MalformedURLException
    {
    	ChromeOptions browserOptions = new ChromeOptions();
    	browserOptions.setPlatformName("Windows 10");
    	browserOptions.setBrowserVersion("latest");
    	
    	Map<String, Object> sauceOptions = new HashMap<String, Object>();
    	sauceOptions.put("name", method.getName());
    	
    	browserOptions.setCapability("sauce:options", sauceOptions);
    	
    	URL url = new URL("https://oauth-balaji0017-cb7f4:4e5bcec0-9b8b-46e3-99ed-b2e14db45bd3@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
    	driver = new RemoteWebDriver(url, browserOptions);
    	   driver.navigate().to("https://www.Google.com");
    }
    
    @Test
    public void validateTitle() {
     
        Assert.assertEquals( driver.getTitle(),"Google");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        driver.executeScript("sauce:job-result=" + status);
    }
}