package Selenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.PageObjects.Cart;
import Selenium.PageObjects.Pdp;
import Selenium.PageObjects.SignIn;
import Selenium.testComponents.Base;

public class EmailValidationTesttJson extends Base {

	@Test(dataProvider = "getData", groups = { "Product and Email" }, retryAnalyzer = RetryAnalyzer.class) 
	public void emailValidation(HashMap<String, String> input) 
																
	{

		landingPage.goToUrl();
		landingPage.getProductByName(input.get("product"));

		Pdp pdp = new Pdp(driver);
		Cart c = new Cart(driver);
		SignIn si = new SignIn(driver);

		pdp.addProductToCart();
		Assert.assertEquals(input.get("product"), c.verifyProductName_Cart());
		
		c.goToCheckout();

		si.goToSignIn(input.get("email"), input.get("password"));
		Assert.assertEquals("Invalid email or passwor", si.getErrorMessage()); 
	}

	@DataProvider
	public Object[][] getData() throws IOException 
	{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
