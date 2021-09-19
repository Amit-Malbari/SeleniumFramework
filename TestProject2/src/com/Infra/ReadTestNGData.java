package com.Infra;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadTestNGData {
	Document doc;

	ReadTestNGData(String path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(path);
		doc.getDocumentElement().normalize();
	}
	
	private HashMap<String, String> getAttributes(String tagName, String attributeName) 
	{
		HashMap<String, String> values = new HashMap<String, String>();
		NodeList nList = doc.getElementsByTagName(tagName);
		NodeList nameTagList = doc.getElementsByTagName("test");
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				if(tagName.equals("class"))
					{
//					values.put(eElement.getAttribute("name").split("\\.")[eElement.getAttribute("name").split("\\.").length-1],eElement.getAttribute(attributeName));
					values.put(((Element) nameTagList.item(i)).getAttribute("name"),eElement.getAttribute(attributeName));
					
					}
				else
					values.put(eElement.getAttribute("name"),eElement.getAttribute(attributeName));
			}
		}
		//Driver.getLogger().info("Returning hashmap result for expected tagname as "+tagName+" and attributeName as "+attributeName);
		return values;
	}
	
	public String getTestCaseID(String tcName) {
		HashMap<String, String> tcIDs = getAttributes("test", "testid");
		//Driver.getLogger().info("Returning test case ID for tcName: "+tcName);
		return tcIDs.get(tcName);
	}
	
	public HashMap<String, String> getAllTestCaseIDs() throws Exception{
		HashMap<String, String> tcIDs = getAttributes("test", "testid");
		//Driver.getLogger().info("Returning all test case IDs");
		return tcIDs;
	}
	
	public HashMap<String, String> getAllTestDescriptions() throws Exception{
		HashMap<String, String> tcDescs = getAttributes("test", "description");
		//Driver.getLogger().info("Returning all test case Descriptions");
		return tcDescs;
	}
	
	public HashMap<String, String> getAllModules() throws Exception{
		HashMap<String, String> modules = getAttributes("class", "name");
		Set<String> keys = modules.keySet();
		for(String key: keys){
			modules.put(key, modules.get(key).split("\\.")[2]);
		}
		//Driver.getLogger().info("Returning all test case modules");
		return modules;
	}
	
	public HashMap<String, String> getAllPriorities() throws Exception{
		HashMap<String, String> priorities = getAttributes("test", "priority");
		//Driver.getLogger().info("Returning all test case priorities");
		return priorities;
	}

}
