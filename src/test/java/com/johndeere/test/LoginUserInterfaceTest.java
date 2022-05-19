package com.johndeere.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.johndeere.base.WebDriverWrapper;

public class LoginUserInterfaceTest extends WebDriverWrapper {
	
	@Test(groups = {"UI","low"})
	public void validateTitleTest()
	{
		Assert.assertEquals(driver.getTitle(), "OpenEMR Login");
	}

}
	