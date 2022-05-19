package com.johndeere.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Demo1 {

	public static void main(String[] args) {
		
		//BeforeSuite
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		extent.attachReporter(spark); 
		
		//@BeforeMethod
		ExtentTest test= extent.createTest("MyFirstTest");   
		//@AfterMethod
		test.log(Status.FAIL, "This is a logging event for MyFirstTest, and it passed!"); 
		
		//AfterSuite
		extent.flush();
	}
}
