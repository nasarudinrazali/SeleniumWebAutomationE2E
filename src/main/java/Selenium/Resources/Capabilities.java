package Selenium.Resources;

import org.openqa.selenium.MutableCapabilities;

public class Capabilities {
	
		MutableCapabilities caps;
	
		//Insert your BrowerStack username
		static String username="";

		//Insert your BrowserStack accessKey
		static String accessKey="";
		
		public static String url="https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub";
		
		
		public static MutableCapabilities capabilities()
		{
		
		MutableCapabilities caps=new MutableCapabilities();
		
		caps.setCapability("build", "alpha_0.1.7");
		
		caps.setCapability("os", "Windows");
		
		caps.setCapability("os_version", "10");
		
		caps.setCapability("browser", "chrome");
		
		caps.setCapability("browser_version", "80");
		
		caps.setCapability("name", "Integration");
		
		
		return caps;
		
		}
		
}
