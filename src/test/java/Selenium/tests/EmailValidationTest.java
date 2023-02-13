package Selenium.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.PageObjects.Cart;
import Selenium.PageObjects.Pdp;
import Selenium.PageObjects.SignIn;
import Selenium.testComponents.Base;


public class EmailValidationTest extends Base {
		
								
		@Test(dataProvider="getData", groups= {"Product and Email"}, retryAnalyzer=RetryAnalyzer.class) //Make a group to group this test and name the group as you like
		//Each method represents one test case
		public void emailValidation(String email,String password,String product)  
		{
			
			landingPage.goToUrl();
			landingPage.getProductByName(product);
			
			Pdp pdp=new Pdp(driver);
			Cart c=new Cart(driver);
			SignIn si=new SignIn(driver);
			
			pdp.addProductToCart();
			Assert.assertEquals(product, c.verifyProductName_Cart());
			
			c.goToCheckout();
			
			si.goToSignIn(email, password);
			Assert.assertEquals("Invalid email or password",si.getErrorMessage()); 
		}
		
		@DataProvider
		public Object[][] getData()  
								    
		{
			
			return new Object[][] {{"dodo@gmail.com","jomla778","Hammer"}, {"tubatubi89@gmail.com","donnn887","Pliers"} };   				
			
		}
		
		
		
		
	
	
	
}
