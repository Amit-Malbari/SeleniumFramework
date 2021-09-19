package com.Infra;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class MailTestSuite {
	public static int rowID;

	
	public static void generateStartMail() throws Exception {
		File fXmlFile = new File(HATF_properties.getTestNGFilePath());

		StringBuilder contentBuilder1 = new StringBuilder();
		contentBuilder1.append(
				"<tr><td align='middle' style='background:#f3f3f; border:1px solid #b6b6b6; font:normal 15px Calibri;'>#TestCaseID#</td>");
		contentBuilder1.append(
				"<td align='center' valign='middle' style='background:#f3f3f; border:1px solid #b6b6b6; font:normal 15px Calibri;'>#Priority#</td>");
		contentBuilder1.append(
				"<td align='center' valign='middle' style='background:#f3f3f; border:1px solid #b6b6b6; font:normal 15px Calibri;'>#Module#</td>");
		contentBuilder1.append(
				"<td valign='middle' style='background:#f3f3f; border:1px solid #b6b6b6; font:normal 15px Calibri;'>#TestCaseName#</td>");
		contentBuilder1.append(
				"<td align='center' valign='middle' style='background:#f3f3f; border:1px solid #b6b6b6; font:normal 15px Calibri;'>#Description#</td></tr>");
		String row = contentBuilder1.toString();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("test");
		String module = "";
		String dynamicRow;
		System.out.println("LIST SIZE" + nList.getLength());
		for (int temp = 0; temp < nList.getLength(); temp++) {
			dynamicRow = row;
			System.out.println("Value of temp: " + temp);
			dynamicRow = dynamicRow.replace("#TestCaseID#", "test1ds").replace("#Priority#", "priority")
					.replace("#Module#", module).replace("#Description#", "testdesc")
					.replace("#TestCaseName#", "className");
			HTMLPreparation.htmlContent = HTMLPreparation.htmlContent.replace("#Row#", dynamicRow + "#Row#");
		}

		HTMLPreparation.htmlContent = HTMLPreparation.htmlContent.replace("#HorizonStatus#", "");
		MailGeneration.subject += ": " + module + " Module Start Mail";
		HTMLPreparation.htmlContent = HTMLPreparation.htmlContent.replace("#Row#", "");

	}

}

class Row {
	String OS, Browser, Module, Tenant, ExecutionMachine, ExecutionTime, Total_TC, Passed_TC, Percentage, Environment;
}