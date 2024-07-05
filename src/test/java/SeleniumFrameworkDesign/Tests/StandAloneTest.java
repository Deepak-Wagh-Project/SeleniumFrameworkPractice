package SeleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
   @Test
	public  void myMethod() {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		driver.findElement(By.id("userEmail")).sendKeys("deeps2398wagh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Deepak@123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".mb-3"))));
		
		List <WebElement> products=  driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(products.size());
		WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		System.out.println(2);
		prod.findElement(By.xpath("//button[contains(text(),'Add To Cart')]")).click();
 
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		
	Boolean isProductThere=	cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase("ZARA COAT 3"));
	
	Assert.assertTrue(isProductThere);
	driver.quit();
	
	}

}
