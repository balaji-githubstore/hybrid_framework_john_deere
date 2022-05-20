package com.johndeere.base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWrapper {

	protected WebDriver driver;

	private static ExtentReports extent;
	protected static ExtentTest test;

	@BeforeSuite(alwaysRun = true)
	public void init() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		extent.attachReporter(spark);
	}

	@AfterSuite(alwaysRun = true)
	public void end() {
		extent.flush();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser","system" })
	public void setup(@Optional("ch") String browserName,@Optional("local") String system, Method method) throws MalformedURLException {

		test = extent.createTest(method.getName());


		if (system.equalsIgnoreCase("cloud")) {
			
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("Windows 10");
			browserOptions.setBrowserVersion("latest");

			Map<String, Object> sauceOptions = new HashMap<String, Object>();
			sauceOptions.put("name", method.getName());

			browserOptions.setCapability("sauce:options", sauceOptions);

			URL url = new URL(
					"https://oauth-balaji0017-cb7f4:4e5bcec0-9b8b-46e3-99ed-b2e14db45bd3@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			driver = new RemoteWebDriver(url, browserOptions);
		} else {
			if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browserName.equalsIgnoreCase("ff")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://demo.openemr.io/b/openemr");
	}

	@AfterMethod(alwaysRun = true)
	@Parameters({ "system" })
	public void teardown(ITestResult result,@Optional("local") String system) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}

		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64String = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(base64String, result.getName());
		
		if(system.equalsIgnoreCase("cloud"))
		{
			//sauce lab result
	        String status = result.isSuccess() ? "passed" : "failed";
	        JavascriptExecutor js=(JavascriptExecutor) driver;
	        js.executeScript("sauce:job-result=" + status);
		}

        
		driver.quit();
	}

}
