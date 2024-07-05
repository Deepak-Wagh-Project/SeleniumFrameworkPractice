package SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
            WebDriver driver;
            public CheckOutPage(WebDriver driver) {
            	super(driver);
            	this.driver= driver;
            	PageFactory.initElements(driver, this);
            }
            
            @FindBy(css=".details__user input")
            WebElement emailInput;
            
            @FindBy(xpath = "//input[@placeholder='Select Country']")
            WebElement countryInput;
            
            @FindBy(css = "button.list-group-item:nth-of-type(2)")
            WebElement countryToBeSelected;
            
            @FindBy(xpath = "//*[contains(text(),\"Place Order\")]")
            WebElement placeOrder;
            
            public void enterUserDetails(String email,String countryName) {
            	emailInput.sendKeys(email);
            	countryInput.sendKeys(countryName);
            	countryToBeSelected.click();
            	
            	
            }
            public ConfirmationPage submitTheOrder() {
            	placeOrder.click();
            	return new ConfirmationPage(driver);
            }
            
}
