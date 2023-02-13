package Selenium.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.PageObjects.Cart;
import Selenium.PageObjects.Pdp;
import Selenium.PageObjects.SignIn;
import Selenium.testComponents.Base;
import org.apache.logging.log4j.*;


public class CartTestValidation extends Base{

	String productName = "Pliers";
	String email = "dodo@gmail.com";
	String password = "jomla778";
	
	private static Logger log= LogManager.getLogger(CartTestValidation.class.getName());

	@Test(groups = { "ProductNameValidation" }, retryAnalyzer = RetryAnalyzer.class) 
	public void productNameVerification() {

		
		
		landingPage.goToUrl();
		log.info("landed on Home page");

		landingPage.getProductByName(productName);
		log.info("Choose a product");
		
		Pdp pdp = new Pdp(driver);
		Cart c = new Cart(driver);		
					
		pdp.addProductToCart();
		log.info("Product Added to cart");

		Assert.assertEquals("Pliers", c.verifyProductName_Cart());
		log.info("Assert product name");
		
		c.goToCheckout();
		log.info("Go to checkout");

	}

	
	@Test(dependsOnMethods = { "productNameVerification" }, retryAnalyzer = RetryAnalyzer.class) 


	public void orderHistoryTest() 
	{

		landingPage.goToUrl();

		landingPage.getProductByName(productName);

		Pdp pdp = new Pdp(driver);
		Cart c = new Cart(driver);
		SignIn si = new SignIn(driver);
		
		pdp.addProductToCart();
		
		c.goToCheckout();

		si.goToSignIn(email, password);

		Assert.assertEquals("Invalid email or password", si.getErrorMessage()); 

		// Go back to the cart to see order history
		c.goToStepOne();
		Assert.assertEquals("Pliers", c.verifyProductName_Cart());
	}

}
