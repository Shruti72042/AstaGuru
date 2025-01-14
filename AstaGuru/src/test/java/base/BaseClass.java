/*
package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	 public void initializeDriver() throws IOException {
		 
		 Properties prop= new Properties();
		 FileInputStream fis=new FileInputStream("src/test/resources/TestData/GlobalData");
		 prop.load(fis);
		 
		 String browserName=prop.getProperty("browser");
		 
		 if (browserName.equalsIgnoreCase("chrome")){
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 
		 }
		 
		 else if (browserName.equalsIgnoreCase("firefox"))
		 {
			 System.setProperty("webdriver.gechko.driver", "firebox.exe");
			driver= new FirefoxDriver();
		 }
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 driver.manage().window().maximize();
		 //return driver;
	 }

	
}
*/


