package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
    WebDriver driver;
    public OrdersPage(WebDriver driver) {
    	super(driver);
    	this.driver= driver;
    	PageFactory.initElements(driver, this);
    	
    }
    @FindBy (css="tr td:nth-child(3)")
    List<WebElement> ordersList;
    
    public boolean isProductInOrdersList(String prod) {
		goToMyOrders();
		return ordersList.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase("ZARA COAT 3"));

	}
}
