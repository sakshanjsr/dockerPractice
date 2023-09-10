package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v112.browser.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class baseClass {

	public static RemoteWebDriver driver;
	public static Properties props; 
	
    
	public static WebDriver initializeDriver() throws IOException {
		System.out.println("*********************BEFORE SUITE***********************");
//>>>>>>>Get the Environment detail from the MAVEN mvn test
		//String environment = System.getProperty("environment");
		String environment = "Q2";
		System.out.println("Environment Selected : "+environment);


//>>>>>>>>> Load the property file after getting the env details from above
		try {
		props = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\global_"+environment+".properties");
		props.load(file);
		System.out.println("Property file loaded");
		}catch (Exception e) {
			throw new RuntimeException("ENVIRONMENT NOT FOUND");
		}
		
		
//>>>>>>>>>>>>> the driver is ready to be initialized
		String browser = props.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {

//			WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker().enableVnc();
//			driver = wdm.create();
//			URL url = wdm.getDockerNoVncUrl();
//			System.out.println("No VNC URL : "+url );
			
			
//			DesiredCapabilities dc = new DesiredCapabilities();
//			dc.setBrowserName("chrome");
//			dc.setPlatform(Platform.LINUX);
//			URL url = new URL("http://localhost:4444/wd/hub");
//			driver = new RemoteWebDriver(url, dc);
//			System.out.println("CHROME Driver Initialized");
			
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}if(browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName("firefox");
			dc.setPlatform(Platform.LINUX);
			URL url = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(url, dc);
			System.out.println("FIREFOX Driver Initialized");
			
		}else {
		
			System.out.println("No Matched Driver");
		}

//>>>>>>>>>>> Maximize the window
		driver.manage().window().maximize();
		
//>>>>>>>>>>> Provide implicit wait on global level
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		System.out.println("Hello Sonar");
		return driver;
	
	}
	
	
	public static void closeDriver()	{
		System.out.println("*********************AFTER SUITE***********************");
		driver.close();
	}

}




