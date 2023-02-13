package Selenium.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponents;

public class Cart extends AbstractComponents {

	WebDriver driver;
	String text;

	public Cart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class='product-title']")
	private List<WebElement> cartProducts;

	@FindBy(css = "[class='product-title']")
	private WebElement cartProduct;

	@FindBy(css = "[data-test='proceed-1']")
	private WebElement checkoutEle;

	@FindBy(xpath = "//div[text()='Cart']")
	private WebElement stepIndicatorOne;

	By productTitleCart = By.cssSelector("[class='product-title']");

	// Action Methods:

	public String verifyProductName_Cart() {

		for (WebElement choose : cartProducts) {
			text = choose.getText();
			break;
		}

		return text;
	}

	public void goToCheckout() {
		checkoutEle.click();
	}

	public void goToStepOne() {

		waitForWebElementToAppear(stepIndicatorOne);
		stepIndicatorOne.click();
	}

}
