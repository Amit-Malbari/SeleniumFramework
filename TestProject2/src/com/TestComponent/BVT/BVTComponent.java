package com.TestComponent.BVT;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Infra.Infra;

public class BVTComponent extends Infra{
	
	public String[][] compBVT(String datafile, WebDriver driver) throws InvalidFormatException, IOException, InterruptedException {
		String [][] data=readExcel(datafile);
		System.out.println(data[0][0]);
		driver.findElement(By.id("menu_directory_viewDirectory")).click();
		selectDropdownByVisibleText(driver, data[0][0], data[0][1]);
		driver.findElement(By.id("searchBtn")).click();
		String[][] result=new String[1][2];
		result[0][0]=data[1][1];
		result[0][1]=driver.findElement(By.xpath("//table[@id='resultTable']//tr[2]/td[2]//li[1]")).getText().trim();
		return result;

	}

}
