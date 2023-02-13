package Selenium.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.PageObjects.BillingAddress;
import Selenium.PageObjects.Cart;
import Selenium.PageObjects.Payment;
import Selenium.PageObjects.Pdp;
import Selenium.PageObjects.SignIn;
import Selenium.testComponents.Base;

public class endToEnd extends Base {
	
	
	@Test(dataProvider="getData")
	//Each method represents one test case
	public void emailValidation(String email,String password,String product)  
	{
		landingPage.goToUrl();
		landingPage.getProductByName(product);
		
		Pdp pdp=new Pdp(driver);
		Cart c=new Cart(driver);
		SignIn si=new SignIn(driver);
		BillingAddress ba=new BillingAddress(driver);
		Payment p=new Payment(driver);
		
		//Add product to Cart and verify that product added is correct
		pdp.addProductToCart();
		Assert.assertEquals(product, c.verifyProductName_Cart());
		
		//Go to checkout page
		c.goToCheckout();
		
		//Login and go to checkout
		si.goToSignIn(email, password);
		si.proceedCheckout2();
		
		//Verify user can enter all fields and proceed to Checkout
		ba.enterAddress();
		ba.enterCity();
		ba.enterState();
		ba.enterCountry();
		ba.enterPostcode();
		ba.prodCheckout3();
		
		//Enter payment method and confirm payment
		p.choosePayment();
		p.enterAccountName();
		p.enterAccountNumber();
		p.confirmPayment();			
		//Verify successful payment message appear
		Assert.assertEquals("Payment was successful", p.paymentSucessAlert()); 
	}
	
	@DataProvider
	public Object[][] getData()  
							    
	{
		return new Object[][] {{"customer@practicesoftwaretesting.com","welcome01","Hammer"}};   				
		
	}
	
	
	
	


}
