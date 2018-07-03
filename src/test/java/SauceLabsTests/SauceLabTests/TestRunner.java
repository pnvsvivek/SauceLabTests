package SauceLabsTests.SauceLabTests;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {
	
	public static void main(String args[])
	{
		TestNG tng = new TestNG();
		List<String> listOfXMLs = new ArrayList<String>();
		listOfXMLs.add("testng.xml");
		tng.setTestSuites(listOfXMLs);
		tng.run();
	}
}
