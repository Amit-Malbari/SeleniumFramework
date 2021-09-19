package com.TestExecute.BVT;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TestComponent.BVT.BVTComponent;

public class SmokeTestDirectory2 extends BVTComponent{
	WebDriver driver;
	String datafile="C:\\Users\\amalbari\\eclipse-workspace\\IntegragtedProject\\src\\com\\TestData\\BVT\\BVT2.xlsx";
	@BeforeClass
	public void before() throws IOException {
		driver=getDriver();
		Login();
	}
	
	
	@Test
	public void test1() throws InvalidFormatException, IOException, InterruptedException {
		String[][] result=compBVT(datafile,driver);
		Assert.assertEquals(result[0][0],result[0][1] );
		System.out.println("Smoke Test 1 executed");
	}
	
	@AfterClass
	public void after() {
		driver.close();
	}

}
