package Selenium.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
		
		ExtentReports extent;

	public static ExtentReports getReportObject() //We make it static so that we can access this method without having to declare the object of thi

	{		
		String path =System.getProperty("user.dir")+"\\reports\\index.html";  
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);     		
		reporter.config().setReportName("Web Automation Results");				
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent= new ExtentReports();  // This the class that is responsible to execute the Extent Reports // Remove ExtentReports data type when  you have declared the ExtentReportClass globally
		extent.attachReporter(reporter);  //give/attach the "reporter variable" where the configurations are present in it
		extent.setSystemInfo("Tester", "Muhammad Nasarudin Bin Mohd Razali");  // give the tester a name
		return extent; //return so that we can get use the extent object in the Listener
	}
	
	
	
}
