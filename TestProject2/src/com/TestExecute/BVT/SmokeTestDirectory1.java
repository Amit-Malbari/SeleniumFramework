package com.TestExecute.BVT;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TestComponent.BVT.BVTComponent;
// added comment line1
// added commet line2
public class SmokeTestDirectory1 extends BVTComponent{
	
	String datafile="C:\\Users\\amalbari\\eclipse-workspace\\IntegragtedProject\\src\\com\\TestData\\BVT\\BVT1.xlsx";
	WebDriver driver;
	
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
		Assert.fail();
	}
	
	@AfterClass
	public void after() {
		driver.close();
	}

}
