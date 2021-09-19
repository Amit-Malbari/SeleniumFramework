package com.Infra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.testng.ITestResult;



public class HTMLPreparation {
	
	public static String mailTemplatePath=System.getProperty("user.dir")+"/MailTemplates/";
	public static String htmlContent;
	public static String module;
	public static ITestResult test;
	
	
	public static String generateMail(String mailType) throws Exception {
		String filePath;
		switch(mailType)
		{
		case "start" :
		{
			filePath=mailTemplatePath+"ExecutionStartMail.html";
			MailGeneration.to=HATF_properties.getInstance().getProperty("Exemail");  // get from properties file
			htmlContent= parseHTMLfile(filePath).replace("#Browser#", "Chrome");
			fillCommonDetails();
			MailTestSuite.generateStartMail();
			return prepmail("Execution_StartMail");
		}
				}
		return null;
	}
	
	public static String parseHTMLfile(String filePath)
	{
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
	
	public static void fillCommonDetails() throws UnknownHostException
	{
		//ReadTestNGXMLFile readTestNGObj = new ReadTestNGXMLFile(HA.TestAutomation.HATF_properties.getTestNGFilePath());
		htmlContent=htmlContent.replace("#URL#",HATF_properties.getInstance().getProperty("URL"))
							   .replace("#Tenant Details#","Amit")
							   .replace("#OS#",System.getProperty("os.name"))
							   .replace("#ExecutionMachine#",InetAddress.getLocalHost().getHostName())
							   .replace("#SuiteName#","Read TestNG and get data");
	}

	public static String prepmail(String fileName) throws Exception{

		FileWriter fWriter = null;
		BufferedWriter writer = null;
		//String strDate = HTML.GetDateTime();
		String sFilename = fileName+ ".html";
		String htmlreportpath =System.getProperty("user.dir") + "/test-output/" + File.separator + sFilename;
		try {

			fWriter = new FileWriter(htmlreportpath);
			writer = new BufferedWriter(fWriter);
			writer.write(htmlContent);;
			writer.close();
			System.out.println("MAil Body prepared");
		} catch (Exception e) {
			e.printStackTrace();
			//mail.logger.error(e);
		}
		return htmlreportpath;
	}



}
