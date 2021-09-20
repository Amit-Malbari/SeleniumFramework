package com.Infra;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Infra {
	
WebDriver driver;
	
	Properties p;
	WebDriver driver1;
	public void entertext(WebDriver driver, String key, String value) throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver.findElement(By.xpath("//label[text()='"+key+"']/following-sibling::input")).clear();
		driver.findElement(By.xpath("//label[text()='"+key+"']/following-sibling::input")).sendKeys(value);	
	}
	
	public void selectDropdownByVisibleText(WebDriver driver, String key, String value) throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		Select sel= new Select(driver.findElement(By.xpath("//label[text()='"+key+"']/following-sibling::select")));
		sel.selectByVisibleText(value);	
	}
	
	public void Login() throws IOException {
		driver.get(property().getProperty("URL"));
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
	}
	
	public  Properties property() throws IOException {
		FileReader reader= new FileReader(System.getProperty("user.dir")+"\\Config\\hataf.properties");
		p=new Properties();
		p.load(reader);
		return p;	
	}
		
	public WebDriver getDriver() throws IOException {
		if(property().getProperty("browser").equals("Chrome")) {
			String chromePath=property().getProperty("chromepath");
			System.setProperty("webdriver.chrome.driver", chromePath);
			this.driver= new ChromeDriver();
			return driver;
		}
		else if(property().getProperty("browser").equals("Firefox")) {
			String firefoxPath=property().getProperty("firefoxpath");
			System.setProperty("webdriver.chrome.driver", firefoxPath);
			this.driver= new FirefoxDriver();
			return driver;
		}
		else {
			String iePath=property().getProperty("Internetexplorerpath");
			System.setProperty("webdriver.chrome.driver", iePath);
			this.driver= new InternetExplorerDriver();
			return driver;
		}
	}

	
	/*
	 * public static WebDriver getDriver1() throws IOException {
	 * if(property().getProperty("browser").equals("Chrome")) { String
	 * chromePath=property().getProperty("chromepath");
	 * System.setProperty("webdriver.chrome.driver", chromePath); driver1= new
	 * ChromeDriver(); return driver1; } else
	 * if(property().getProperty("browser").equals("Firefox")) { String
	 * firefoxPath=property().getProperty("firefoxpath");
	 * System.setProperty("webdriver.chrome.driver", firefoxPath); driver1= new
	 * FirefoxDriver(); return driver1; } else { String
	 * iePath=property().getProperty("internetexplorerpath");
	 * System.setProperty("webdriver.chrome.driver", iePath); driver1= new
	 * FirefoxDriver(); return driver1; } }
	 */
	public String[][] readExcel(String path) throws IOException, InvalidFormatException {
		
		File file=new File(path);
		  XSSFWorkbook wb=new XSSFWorkbook(file);
		  XSSFSheet sheet=(XSSFSheet) wb.getSheetAt(0);
		   int row= sheet.getPhysicalNumberOfRows();//5
		  int col= sheet.getRow(0).getPhysicalNumberOfCells();//3
		  System.out.println("Row "+row);
		  System.out.println("Col "+col);
		  
		  String [][]a= new String [row-1][col];//
		  
		  for(int i=1;i<=row-1;i++)
		  {
			  
			  for (int j=0;j<col;j++) {
				 a[i-1][j]=sheet.getRow(i).getCell(j).toString();
				 System.out.println(i + " " +j +" "+ a[i-1][j]);
			  }
		  }
		return a;
	}
	
	public Connection connection() throws ClassNotFoundException, SQLException {
		String Claimant_id="10000";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection connection=DriverManager.getConnection("jdbc.sqlserver://");
		String sql="Select Name,ID,AddressLine1 from ui_claim where claimant_id ="+Claimant_id+";";
		java.sql.Statement statement=connection.createStatement();
		ResultSet rs=statement.getResultSet();
		java.sql.ResultSetMetaData rsmd=rs.getMetaData();
		rsmd.getColumnCount();
		
		rs.last();
		
		rs.getRow();
		rs.first();
		return connection;
	}

}
