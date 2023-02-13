package Selenium.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Selenium.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Page Factory Design Pattern

	@FindBy(xpath = "a[aria-label='Next page']")
	private WebElement nextPage;

	@FindBy(css = "input[formcontrolname='query']")
	private WebElement searchBox;

	@FindBy(css = "[type='submit']")
	private WebElement searchButton;

	@FindBy(css = "[type='number']")
	private WebElement quantityBox;

	@FindBy(css = "[data-test='product-name']")
	private List<WebElement> products;

	@FindBy(xpath = "//h5[text()=' Hammer ']")
	private WebElement hammer;

	@FindBy(xpath = "//h5[text()=' Wood Saw ']")
	private WebElement wood;

	@FindBy(css = "[data-test='sort']")
	private WebElement sortDropDown;

//Actions methods:

	public void goToUrl() {
		driver.get("https://practicesoftwaretesting.com/#/");
	}

	public void productName_SearchBox(String productName) {
		searchBox.sendKeys(productName);
		searchButton.click();
	}

	public void enterProductQuantity(String number) {
		quantityBox.clear(); // clear any quantity
		quantityBox.sendKeys(number);
	}

	By productsBy = By.cssSelector("[data-test='product-name']");

	public List<WebElement> getProductList() {
		waitForByElementToAppear(productsBy);
		return products;
	}

	// getProductName method
	public void getProductByName(String productName) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		for (WebElement choose : products) {
			if (choose.getText().equalsIgnoreCase(productName)) {
				js.executeScript("arguments[0].click();", choose);
				break;

			}
		}

	}

	public WebElement selectSortDropdown(String name) {
		Select s = new Select(sortDropDown);
		s.selectByVisibleText(name);
		return sortDropDown;
	}

}
