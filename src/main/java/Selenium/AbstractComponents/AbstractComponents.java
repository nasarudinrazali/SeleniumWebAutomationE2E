package Selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
public WebDriver driver; 
	


	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Home, Categories , Contact, SIgn in Headers are all common to all pages, so we do not store them in Page Objects.
	//So, we put them in Abstact Components because they are something which can be used by all Page Objects.
		
	
	@FindBy(css="[data-test='nav-home']")
	
	WebElement home;
	
	@FindBy(css="[data-test='nav-categories']")
	WebElement categories;
	
	@FindBy(css="[data-test='nav-contact']")
	WebElement contact;
	
	@FindBy(css="[data-test='nav-sign-in']")
	WebElement signIn;
	
	@FindBy(css="[data-test='nav-cart']")
	WebElement cartHeader;
	

	//Action methods:
	
	public void goToCart()
	{
		cartHeader.click();
	}
	
	//You can use By locator or WebElement
	
	public void waitForByElementToAppear(By findBy)//expects By Locator. The return type of By locator is "By"
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy) //This time we use a WebElement not a By locator because we expect "driver.findElement"
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	
	

}
