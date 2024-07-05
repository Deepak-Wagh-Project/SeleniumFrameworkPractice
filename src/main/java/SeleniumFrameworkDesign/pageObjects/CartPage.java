package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[contains(text(),\"Checkout\")]")
    WebElement checkOut;
	public boolean isProductInCart(String prod) {
		goToCartPage();
		return cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase("ZARA COAT 3"));

	}
	public CheckOutPage checkOutMethod() {
		checkOut.click();
		return new CheckOutPage(driver);
	}
}
