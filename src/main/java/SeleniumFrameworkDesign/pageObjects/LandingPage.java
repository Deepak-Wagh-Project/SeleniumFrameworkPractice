package SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
WebDriver driver;
public LandingPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement userPassword;

@FindBy(id="login")
WebElement submit;

@FindBy(css="[class*='flyInOut'")
WebElement errorMessage;

public ProductCatalogue loginMethod(String  email, String password) {
	userEmail.sendKeys(email);
	userPassword.sendKeys(password);
	submit.click();
	return new ProductCatalogue(driver);
}

public void landingPageGoTo() {
	driver.get("https://rahulshettyacademy.com/client");
	
}
public String errorInLogin() {
	waitWebElementToAppear(errorMessage);
	return errorMessage.getText();
}
}
