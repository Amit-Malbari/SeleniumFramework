package com.TestComponent.Workflow;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Infra.Infra;

public class WorkflowComponent extends Infra{
	
	public String[][] compWorkflow(String datafile, WebDriver driver) throws InvalidFormatException, IOException, InterruptedException {
		String [][] data=readExcel(datafile);
		System.out.println(data[0][0]);
		driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
		selectDropdownByVisibleText(driver, data[0][0], data[0][1]);
		driver.findElement(By.id("btnSearch")).click();
		String[][] result=new String[1][2];
		result[0][0]=data[1][1];
		result[0][1]=driver.findElement(By.xpath("//table[@id='resultTable']//tr[1]/td[2]")).getText().trim();
		return result;
	}

}
