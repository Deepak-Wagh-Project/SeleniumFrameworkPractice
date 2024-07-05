package SeleniumFrameworkDesign.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.LandingPage;
import SeleniumFrameworkDesign.pageObjects.OrdersPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	@Test(dataProvider= "userDataSet", groups= {"PurchaseOrder"})
	public void submitOrder(HashMap<String,String> map) throws IOException, InterruptedException {

		String confirmationMessage = "THANKYOU FOR THE ORDER.";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		ProductCatalogue productCatalogue=landingPage.loginMethod(map.get("email"), map.get("password"));
		List<WebElement> prodList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(map.get("product"));

		
		CartPage cartPage=productCatalogue.goToCartPage();
		assertTrue(cartPage.isProductInCart(map.get("product")));
		CheckOutPage checkOutPage=cartPage.checkOutMethod();

		checkOutPage.enterUserDetails(map.get("email"), map.get("country"));
		ConfirmationPage confirmationPage=checkOutPage.submitTheOrder();

		
		Assert.assertEquals(confirmationMessage, confirmationPage.getConfirmationMessage());

	}

	@DataProvider
	 public Object[][] userDataSet() throws IOException{
		
		List<HashMap<String,String>> map= getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFramework\\Data\\PurchaseOrder.json");
//		HashMap <String,String> map= new HashMap<String,String>();
//		map.put("email", "deeps2398wagh@gmail.com");
//		map.put("password", "Deepak@123");
//		map.put("product","ZARA COAT 3");
//		map.put("country", "India");
	//	return new Object[][] {{"deeps2398wagh@gmail.com","Deepak@123","ZARA COAT 3","India"}};
		return new Object[][] {{map.get(0)}};
		}
//	@Test(dependsOnMethods = { "submitOrder" })
//	public void verifyOrderTest()  {
//		landingPage.loginMethod(userName, password);
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
//		productCatalogue.goToMyOrders();
//		
//		//OrdersPage ordersPage = new OrdersPage(driver);
//		//Assert.assertTrue(ordersPage.isProductInOrdersList(prodName));
//	}
}