package SeleniumFrameworkDesign.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;   

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
  @Test
    
  public void loginErrorValidation() {
	  landingPage.loginMethod("affafsffs@gmail.com", "webElement");
	  Assert.assertEquals(landingPage.errorInLogin(), "Incorrect email or password.");
  }
  @Test(groups= {"ErrorHandling"})
  public void productErrorValidation() throws IOException, InterruptedException{
		String userName = "deeps2398wagh@gmail.com";
		String password = "Deepak@123";
		String prodName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		landingPage.loginMethod(userName, password);
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> prodList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(prodName);

		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Assert.assertFalse(cartPage.isProductInCart("TARA MOAT 333"));
		
	}
}
