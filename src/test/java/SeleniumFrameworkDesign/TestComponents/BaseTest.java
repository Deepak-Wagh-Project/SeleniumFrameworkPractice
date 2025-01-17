package SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\wwwde\\OneDrive\\Desktop\\selenium_Project_Workspace\\SeleniumFrameworkDesign\\src\\test\\java\\SeleniumFrameworkDesign\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null?
				System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			
			ChromeOptions options= new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if (browserName.contains("headless")) {
		options.addArguments("headless");}
	driver = new ChromeDriver(options);	
	driver.manage().window().setSize(new Dimension(1440, 900));
	

		} else if (browserName.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"//Drivers//msedgedriver.exe");
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return  driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.landingPageGoTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void  tearDown() {
		driver.close();
	}

	    public  List<HashMap<String,String>> getJsonToMap(String filePath) throws IOException {
	    String jsonContent=	FileUtils.readFileToString(new File(filePath),
	    		StandardCharsets.UTF_8);
	     ObjectMapper mapper= new ObjectMapper();
	     List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<  List<HashMap<String,String>>>() {
		});
	     return data;
	    }
	    
	    public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
	    	TakesScreenshot ts= (TakesScreenshot)driver;
	    	File source=ts.getScreenshotAs(OutputType.FILE);
	    	File file = new File(System.getProperty("user.dir")+"//screenshots//"+testcaseName+".png");
	        FileUtils.copyFile(source, file);
	        return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	        
	    }
}
