package Selenium.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Selenium.AbstractComponents.AbstractComponents;

public class SignIn extends AbstractComponents {

	WebDriver driver;

	public SignIn(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	WebDriver dirver;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(css = "[type='submit']")
	private WebElement login;

	@FindBy(css = "[class='alert alert-danger']")
	private WebElement errorMessage;
	
	@FindBy(css = "[data-test='proceed-2']")
	private WebElement proceedCheckout;

	
	public void goToSignIn(String name, String pass) {
		email.sendKeys(name);
		password.sendKeys(pass);
		login.click();

	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		String er = errorMessage.getText();
		return er;
	}
	
	public void proceedCheckout2(){
		proceedCheckout.click();
	}

}
