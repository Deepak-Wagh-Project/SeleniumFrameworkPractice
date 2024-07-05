package SeleniumFramework.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.LandingPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionimpl extends BaseTest{
	ProductCatalogue productCatalogue;
	LandingPage landingPage;
	CartPage cartPage;
	ConfirmationPage confirmationPage;
	
 @Given("I landed on ecommerce page")
 public void I_Landed_on_Ecommerce_page() throws IOException {
	 landingPage= launchApplication();
 }
 @Given("^Logged in with username (.+) and password (.+)$")
 public void logged_In_to_application(String username,String password) throws IOException {
	 productCatalogue= landingPage.loginMethod(username, password);
 }
 @When("^I add product (.+) to the cart$")
 public void add_To_Cart(String productName) throws InterruptedException {
	 List<WebElement> prodList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
         cartPage=productCatalogue.goToCartPage();
 }
 @And ("^Checkout (.+) and submit the order$")
 public void  checkout_And_Submit_the_Order(String productName) {
	 CheckOutPage checkOutPage=cartPage.checkOutMethod();

		checkOutPage.enterUserDetails("deeps2398wagh@gmail.com", "india");
		 confirmationPage=checkOutPage.submitTheOrder();
 }
 @Then("{string} message is displayed on the Confirmation Page.")
 public void check_Confirmation_Message_Is_Displayed(String message) {
	 Assert.assertEquals(message, confirmationPage.getConfirmationMessage()); 
	 driver.close();
 }
 
}
