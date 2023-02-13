package Selenium.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Selenium.AbstractComponents.AbstractComponents;

public class Payment extends AbstractComponents {
	
	WebDriver driver;
	
	public Payment(WebDriver driver)
	{	
		super(driver); //Bagi driver life kat parent that is AbstractComponents
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = "[data-test='payment-method']")
	private WebElement payment_method;
	
	@FindBy(css = "[data-test='account-name']")
	private WebElement account_name;
	
	@FindBy(css = "[data-test='account-number']")
	private WebElement account_number;

	@FindBy(css = "[data-test='finish']")
	private WebElement confirm;

	@FindBy(css = "[class='alert alert-success']")
	private WebElement payment_sucess_alert;

	
	public void choosePayment() {
		Select s=new Select(payment_method);
		s.selectByVisibleText("Cash on Delivery");
	}
	
	public void enterAccountName() {
		account_name.sendKeys("John Abraham");
	}
	
	public void enterAccountNumber() {
		account_number.sendKeys("3488923492389");
	}
	
	public void confirmPayment() {
		waitForWebElementToAppear(confirm);
		confirm.click();
	}
	
	public String paymentSucessAlert() {
		String alert=payment_sucess_alert.getText();
		return alert;
	}
	
		
		
		
		
		
		
}
