package Selenium.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Selenium.Resources.ExtentReport;
import Selenium.testComponents.Base;

public class Listeners extends Base implements ITestListener { 

	ExtentReports extent = ExtentReport.getReportObject(); 
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) { 
		
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result); 

		test = extent.createTest(result.getMethod().getMethodName());

		extentTest.set(test); 
	}

	@Override
	public void onTestSuccess(ITestResult result) { 
		// TODO Auto-generated method stub
		
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "Test is successful"); 
	}

	@Override
	public void onTestFailure(ITestResult result) { 
		
		ITestListener.super.onTestFailure(result);
		extentTest.get().fail(result.getThrowable()); // give a message about test status if test fails in the report


		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String filePath = null; 
								
		try {

			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		try {
			
			extentTest.get().addScreenCaptureFromPath(filePath,
					getScreenshot(result.getMethod().getMethodName(), driver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);

		extent.flush(); 
	}

}
