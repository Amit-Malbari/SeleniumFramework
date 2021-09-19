package com.TestComponent.Search;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Infra.Infra;

public class SearchComponent extends Infra{
	
	public String[][] compSearch(String datafile, WebDriver driver) throws InvalidFormatException, IOException, InterruptedException {
		String [][] data=readExcel(datafile);
		System.out.println(data[0][0]);
		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
		selectDropdownByVisibleText(driver, data[0][0], data[0][1]);
		driver.findElement(By.id("btnSrch")).click();
		String[][] result=new String[1][2];
		result[0][0]=data[1][1];
		result[0][1]=driver.findElement(By.xpath("//table[@id='resultTable']//tr[1]/td[3]")).getText().trim();
		return result;
	
	}

}
