package com.TestExecute.Search;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TestComponent.Search.SearchComponent;

public class SearchmoduleRecruitment2 extends SearchComponent{
	
	String datafile="C:\\Users\\amalbari\\eclipse-workspace\\IntegragtedProject\\src\\com\\TestData\\Search\\Search2.xlsx";
	WebDriver driver;
	
	@BeforeClass
	public void before() throws IOException {
		driver=getDriver();
		Login();
	}
	
	
	@Test
	public void test1() throws InvalidFormatException, IOException, InterruptedException {
		String[][] result=compSearch(datafile,driver);
		Assert.assertEquals(result[0][0],result[0][1] );
		System.out.println("Search Module 2 executed");
	}
	
	@AfterClass
	public void after() {
		driver.close();
	}

}
