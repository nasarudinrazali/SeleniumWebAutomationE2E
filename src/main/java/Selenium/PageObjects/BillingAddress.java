package Selenium.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponents;

public class BillingAddress extends AbstractComponents {
	
	WebDriver driver;
	
	public BillingAddress(WebDriver driver)
	{	
		super(driver); //Bagi driver life kat parent that is AbstractComponents
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[data-test='address']")
	private WebElement address;
	
	@FindBy(css = "[data-test='city']")
	private WebElement city;
	
	@FindBy(css = "[data-test='state']")
	private WebElement state;

	@FindBy(css = "[data-test='country']")
	private WebElement country;

	@FindBy(css = "[data-test='postcode']")
	private WebElement postcode;
	
	@FindBy(css = "[data-test='proceed-3']")
	private WebElement prodCheckout;
	
	
	public void enterAddress() {
		address.sendKeys("testaddress");
	}
	
	public void enterCity() {
		city.sendKeys("testcity");
	}
	
	public void enterState() {
		state.sendKeys("teststate");
	}
	
	public void enterCountry() {
		country.sendKeys("testcountry");
	}
	
	public void enterPostcode() {
		postcode.sendKeys("34889");
	}
	
	public void prodCheckout3() {
		waitForWebElementToAppear(prodCheckout);
		prodCheckout.click();
	}
	






}
