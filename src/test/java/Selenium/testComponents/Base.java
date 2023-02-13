package Selenium.testComponents;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.PageObjects.LandingPage;
import Selenium.Resources.Capabilities;


public class Base {
	
	//We will write codes for browser configuration ((maximize the browser,screenshot)) and global properties (if you set chrome, chrome browser will be executed, if you set firefox, firefox browser will be executed)here
	
	public WebDriver driver; 
	public LandingPage landingPage; 
	
	
	public WebDriver initializeDriver() throws IOException
	{
		
	Properties prop= new Properties();	
	
	//Read properties file on local system
	FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\testComponents\\GlobalData.properties");   //This class converts file into input stream object, put the location of file as an argument
	
	prop.load(fis);
	
	//Read browser property on terminal										
	String browserName= System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
	
	if(browserName.contains("Chrome"))
	{
		ChromeOptions options= new ChromeOptions();		
		
		 if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		 driver=new ChromeDriver(options);  
		 
		 //Maximize Screen in headless mode
		 driver.manage().window().setSize(new Dimension(1440,900)); 
		
	}
	
	
	//BrowserStack Cloud Tests
	else if(browserName.equalsIgnoreCase("cloudtest"))
	{										
		driver=new RemoteWebDriver(new URL(Capabilities.url), Capabilities.capabilities());;
	}
	
	
	else if(browserName.equalsIgnoreCase("Firefox"))
	{
		                                                 //Give the the path of your firefox driver here
		System.setProperty("webdriver.firefox.driver", "C:\\Users\\dean razali\\Desktop\\Chrome.Gecko,Edge drivers\\chromedriver_win32\\geckodriver.exe");
		driver=new FirefoxDriver(); //Firefox Driver  //You dont have to write "WebDriver" return type because we have declared WebDriver Driver globally 
	}
	
	else if(browserName.equalsIgnoreCase("Edge"))
	{													//Give the the path of your Edge driver here
		System.setProperty("webdriver.edge.driver","C:\\Users\\dean razali\\Desktop\\Chrome.Gecko,Edge drivers\\edgedriver\\msedgedriver.exe");
		driver=new EdgeDriver();  
	}
	
	//Implicit Wait:
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
	}
	
	

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{	
		driver=initializeDriver();  //We assign the initializeDriver() to driver global variable (WebDriver driver)

		landingPage=new LandingPage(driver);
		landingPage.goToUrl();
		return landingPage; 	
	}
	
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	//Read test data in JSON file and convert it into a list of Strings in HashMap
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws   //Read the JSON file, convert into into String, then convert into List of Hashmap, then return the list of HashMap
	IOException
		{
			String jsonContent= FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8); 
			 
			ObjectMapper mapper=new ObjectMapper(); 
			
			List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {}); //Create HashMap out of JSON and put it into List
				
			return data;
		}
									
	//Take Screenshot
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver; 
		File source=ts.getScreenshotAs(OutputType.FILE); 
		File file=new File(System.getProperty("user.dir") + "//reports//"+ testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//"+ testCaseName +".png";
	}
	
	


	

}
