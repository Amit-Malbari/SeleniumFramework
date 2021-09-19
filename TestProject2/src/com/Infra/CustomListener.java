package com.Infra;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.List;

import org.testng.IExecutionListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

public class CustomListener implements IReporter,IExecutionListener{
	static int  retryCount=1;
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		rerunFailedTests();
	}
	
	private static void rerunFailedTests() {
		File failedTestNg = FileSystems.getDefault()
				.getPath(System.getProperty("user.dir"), "test-output", "testng-failed.xml").toFile();
		if (failedTestNg.exists() && retryCount > 0) {
			retryCount=retryCount-2;
			//LOGGER.info("testng-failed.xml exists, rerunning the failed test cases");
			ITestNGListener listener = new TestListenerAdapter();
			TestNG testng = new TestNG();
			List<String> suites = Lists.newArrayList();
			suites.add("test-output/testng-failed.xml");
			testng.setTestSuites(suites);
			testng.addListener(listener);
			testng.run();
			
			
		}
	}
	
	
	
	

}
