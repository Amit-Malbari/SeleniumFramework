package com.Infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;


public class HATF_properties {
	
	private static String configFile = "hataf.properties";
	public static String testNG = "testng.xml";
	private static Properties _automation_properties = new Properties();

	public final static String BASEURL = "BASEURL";
	public final static String BROWSER = "BROWSER";
	public final static String CHROMEVERSION = "CHROMEVERSION";
	public final static String Exestartmail = "Exestartmail";
	public final static String Exemail = "Exemail";
	//public final static String projPATH = "projPATH";

	public static final String USERNAME = "USERNAME";
	public static final String PASSWORD = "PASSWORD";
	private static HATF_properties instance;
	public static String RunningBrowser = "Chrome";
	
	public static void setTestNG(String testNGFile) {
		testNG = testNGFile;
	}

	public static String getTestNGFilePath(){
		File testNGFile = new File(testNG);
		if (testNGFile.exists()) {
			return testNGFile.getAbsolutePath();
		}
		//Driver.getLogger().info(System.getProperty("user.dir") + "/" + testNG);
		return System.getProperty("user.dir") + "/" + testNG;
	}

	public static String getHostName() {
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			return addr.getHostName();
		} catch (UnknownHostException ex) {
			System.out.println("Hostname can not be resolved");
		}
		return "UnKnown";
	}

	// Call this method only to change the file from which the properties have
	// to be loaded.
	private HATF_properties() {
		System.out.println("Loading automation properties from the file" + configFile);
		loadProperties();
	}

	private static void createInstance() {
		instance = new HATF_properties();
	}

	public static void createInstance(String configFile) {
		HATF_properties.configFile = configFile;
		if (instance == null) {
			createInstance();
		} else {
			System.out.println("Reloading automation properties from the file " + configFile);
			loadProperties();
		}
	}

	public static String getConfigFile() {
		return configFile;
	}

	private static void loadProperties() {
		try {
			_automation_properties.clear();
			_automation_properties.load(new FileInputStream("Config/" + configFile));
		} catch (IOException e) {
			throw new RuntimeException("Failed to load the properties:"+e);
		}

		assert !_automation_properties.isEmpty();
	}

	// Always use the getInstance method, you can't create your own object, only replace the config file using
	// createInstance(configFile).
	public static HATF_properties getInstance() {

		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	/**
	 * returns the value of the property denoted by key
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(final String key) {
		String property = _automation_properties.getProperty(key);
		return property != null ? property.trim() : property;
	}

	public void setProperty(String key, String value) {
		_automation_properties.setProperty(key, value);
	}

	public boolean isProperty(final String key) {
		String property = _automation_properties.getProperty(key);
		if (property != null)
			return true;
		else
			return false;
	}

	public int getNumOfProperties() {
		return _automation_properties.size();
	}


}
