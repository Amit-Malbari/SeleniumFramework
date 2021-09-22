package com.TestExecute.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class MainLauncher {

	Properties props; 
	private static final String failedTestNGFile= "test-output\\testng-failed.xml"; 
	
	@Parameter(names = { "--area" }, description = "Specify a custom config file")
	String area = "BVT";
	@Parameter(names = { "--config" }, description = "Specify a custom config file")
	String configFile = "hataf.properties";
	@Parameter(names = { "--testngXml" }, description = "Specify a custom config file")
	String testngFileName = "testng.xml";
	@Parameter(names = { "--summaryFlag" }, description = "Specify a custom config file")
	int summaryFlag = 1;
	@Parameter(names = { "--url" }, description = "Specify a url to be placed in hataf.properties")
	String url = null;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MainLauncher main=new MainLauncher();
		JCommander jct =  JCommander.newBuilder().addObject(main).build();
		jct.parse(args);
		main.run();
	}
	
	private void run() throws Exception {
		/*	testngFileName="TestNG/ExtBVT/TestNG_ExtBVT.xml";
			configFileName="ExtBVT.properties";*/
			//CopyRequiredFiles();
			//prepareTestNGAndProperties();
			//HA.TestAutomation.HATF_properties.createInstance(configFileName);
			//check if users are active, we are not going to check for local environments 
			System.out.println(testngFileName);
			updatePropsFiles();
			ITestNGListener listener = new TestListenerAdapter();
			TestNG testng = new TestNG();
			List<String> suites = Lists.newArrayList();
			suites.add(testngFileName);
			testng.setTestSuites(suites);
			testng.addListener(listener);
			testng.run();
			checkAndRerunSuite();
				//BackupUtils.runRegressionBackup();
				//cleanUpTestDataAndconfig();
			
		}
		
		private void updatePropsFiles(){
			try{
				if (url != null) {
					FileInputStream in = new FileInputStream("Config/" + configFile);
					props = new Properties();
					props.load(in);
					props.setProperty("BASEURL", url);
					props.setProperty("browser", "Chrome");
					FileOutputStream out = new FileOutputStream("Config/" + configFile);				
					props.store(out, null);
					in.close();
					out.close();
				}
			}catch(Exception e){
				//Driver.getLogger().info("Error while saving the hataf.properties file",e);
			}
			
		}
		
		private void checkAndRerunSuite() throws IOException {
			FileInputStream in = new FileInputStream("Config/" + configFile);
			props = new Properties();
			props.load(in);
			if(!(props.getProperty("rerun").equals("true"))) {
				return;
			}
			String failedTestNGAsString = "";
			if (new File(failedTestNGFile).exists()) {
				try (FileInputStream inStream = new FileInputStream(failedTestNGFile)) {

					failedTestNGAsString = new BufferedReader(new InputStreamReader(inStream)).lines()
							.collect(Collectors.joining("\n"));
				} catch (Exception e) {

				}
			}
			boolean matches = failedTestNGAsString.contains("<test ");
			// rerun suite
			if (matches) {
				TestNG testng = new TestNG();
				List<String> suites = Lists.newArrayList();
				suites.add(failedTestNGFile);
				testng.setTestSuites(suites);
				testng.run();
			}
		}
}
