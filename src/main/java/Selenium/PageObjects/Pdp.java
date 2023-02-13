package Selenium.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponents;

public class Pdp extends AbstractComponents {

	WebDriver driver;

	public Pdp(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[data-test='add-to-cart']")
	private WebElement addToCart;

	@FindBy(css = "[class='card-title']")
	private List<WebElement> relatedProducts;

	@FindBy(css = "[data-test='add-to-cart']")
	private WebElement goToCart;

	By cart = By.cssSelector("[data-test='add-to-cart']");
	By byRelatedProducts = By.cssSelector("[class='card-title']");

	// Add To Cart Button

	public void addProductToCart() {
		waitForByElementToAppear(cart);
		addToCart.click();

		goToCart();
	}

	// GetRelatedProducts

	public void getRelatedProducts(String relatedProductName) {
		waitForByElementToAppear(byRelatedProducts);

		JavascriptExecutor js = ((JavascriptExecutor) driver); // Use Javascript Executor to fix Elements not clickable
																// issues

		for (WebElement choose : relatedProducts) {
			if (choose.getText().equalsIgnoreCase(relatedProductName)) {
				js.executeScript("arguments[0].click();", choose);
				break;
			}
		}
	}

}
