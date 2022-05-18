package com.johndeere.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Demo1Test {
	
	@DataProvider
	public String[][] validData()
	{
		String[][] main=new String[3][2]; 
		//i-->number of test case
		//j-->number of parameter 
		main[0][0]="john";
		main[0][1]="john123";
		
		main[1][0]="peter";
		main[1][1]="peter123";
		
		main[2][0]="mark";
		main[2][1]="mark123";
		
		return main;
	}
	
	@Test(dataProvider = "validData")
	public void validTest(String username,String password)
	{
		System.out.println("valid"+username+password);
	}

}
