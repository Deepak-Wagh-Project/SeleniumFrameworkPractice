package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.SimplePropertyDescriptor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
WebDriver driver;
public ProductCatalogue(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}

@FindBy(css=".mb-3")
List <WebElement> products;

By prodLocator= By.cssSelector(".mb-3");
By addToCart=By.cssSelector(".card-body button:last-of-type");
By toastMessage= By.cssSelector("#toast-container");
By loader= By.cssSelector(".ng-animating");
public List<WebElement> getProductList() {
  waitElementToAppear(prodLocator);
  return products;
}

public WebElement getProductByName(String prodName) {
	return getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);

}

public void addProductToCart(String prodName) throws InterruptedException {
	getProductByName(prodName).findElement(addToCart).click();
	waitElementToAppear(toastMessage);
	//waitElementToDisappear(loader);
	Thread.sleep(2000);
}
}
