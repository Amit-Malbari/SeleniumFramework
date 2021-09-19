package com.TestExecute.Workflow;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TestComponent.Workflow.WorkflowComponent;

public class WorkFlowLeave2 extends WorkflowComponent{
	
	String datafile="C:\\Users\\amalbari\\eclipse-workspace\\IntegragtedProject\\src\\com\\TestData\\WorkFlow\\Workflow2.xlsx";
	WebDriver driver;
	
	@BeforeClass
	public void before() throws IOException {
		driver=getDriver();
		Login();
	}
	
	@Test
	public void test1() throws InvalidFormatException, IOException, InterruptedException {
		String[][] result=compWorkflow(datafile,driver);
		Assert.assertEquals(result[0][0],result[0][1] );
		System.out.println("Workflow 2 executed");
	}
	
	@AfterClass
	public void after() {
		driver.close();
	}


}
