package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import java.io.ByteArrayInputStream;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Base;
import utils.TestDataUtil;

public class Hooks {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static boolean isLoggedIn = false; // Track login status
    private static Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) throws InterruptedException {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting scenario: {}", scenario.getName());

        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            
            // ✅ Add ChromeOptions to avoid the DevToolsActivePort issue
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox"); // Required for Linux environments
            options.addArguments("--disable-dev-shm-usage"); // Prevents crashes in Docker/Linux
            options.addArguments("--remote-allow-origins=*"); // Fix remote debugging issue
            options.addArguments("--disable-gpu"); // Helps with headless execution
            options.addArguments("--headless=new"); // Run in headless mode (new version)

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            Base.setDriver(driver);
        }

        // Check for the @SkipLogin tag
        if (scenario.getSourceTagNames().contains("@SkipLogin")) {
            logger.info("Skipping login for scenario: {}", scenario.getName());
            return; // Skip the login logic
        }

        if (!isLoggedIn) {
            loginPage = new LoginPage(driver);
            driver.get("https://development.d2mp9949i73scy.amplifyapp.com/create-account?returnUrl=/");

            Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
            Map<String, Object> validLogin = (Map<String, Object>) loginData.get("validLogin");
            String mobileNo = (String) validLogin.get("mobileNo");
            loginPage.getMobileNumberField().sendKeys(mobileNo);
            loginPage.getTermsAndConditionsCheckbox().click();
            loginPage.getOtpButton().click();

            // Wait for manual OTP entry
            Thread.sleep(25000);

            loginPage.ClickGetStartedBtn().click();
            Thread.sleep(10000);

            isLoggedIn = true; // Mark login as done
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            // Capture screenshot on failure
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        // Quit the driver only after all scenarios
        if (scenario.getSourceTagNames().contains("@LastScenario")) {
            if (driver != null) {
                driver.quit();
                driver = null;
                Base.setDriver(null);
                isLoggedIn = false; // Reset login status for next run
            }
        }
    }
}



/*
package stepDefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import pageObjects.LoginPage;
import java.io.ByteArrayInputStream;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Base;
import utils.TestDataUtil;

public class Hooks {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static boolean isLoggedIn = false; // Track login status
    private static  Logger logger = LogManager.getLogger(Hooks.class);
    @Before
    public void setUp(Scenario scenario) throws InterruptedException {
    	logger=LogManager.getLogger(this.getClass());
    	
    	 logger.info("Starting scenario: {}", scenario.getName());
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Base.setDriver(driver);
        }
        
     // Check for the @SkipLogin tag
        if (scenario.getSourceTagNames().contains("@SkipLogin")) {
        	  logger.info("Skipping login for scenario: {}", scenario.getName());
            return; // Skip the login logic
        }

        if (!isLoggedIn) {
            loginPage = new LoginPage(driver);
            driver.get("https://development.d2mp9949i73scy.amplifyapp.com/create-account?returnUrl=/");

            Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
            Map<String, Object> validLogin = (Map<String, Object>) loginData.get("validLogin");
            String mobileNo = (String) validLogin.get("mobileNo");
            loginPage.getMobileNumberField().sendKeys(mobileNo);
            loginPage.getTermsAndConditionsCheckbox().click();
            loginPage.getOtpButton().click();

            // Wait for manual OTP entry
            Thread.sleep(25000);

            loginPage.ClickGetStartedBtn().click();
            Thread.sleep(10000);

            isLoggedIn = true; // Mark login as done
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            // Capture screenshot on failure
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        // Quit the driver only after all scenarios
        if (scenario.getSourceTagNames().contains("@LastScenario")) {
            if (driver != null) {
                driver.quit();
                driver = null;
                Base.setDriver(null);
                isLoggedIn = false; // Reset login status for next run
            }
        }
    }
}
*/

/*
public class Hooks {
	
	public static  WebDriver driver;
	//WebDriver driver; 
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Base.setDriver(driver);
       
    }

    @After
    public void tearDown(Scenario scenario) {
    	WebDriver driver = Base.getDriver();

        if (driver != null) {
            // Capture screenshot if the scenario fails
            if (scenario.isFailed()) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
                } catch (Exception e) {
                    System.out.println("Failed to capture screenshot: " + e.getMessage());
                }
            }

            // Quit the driver
            driver.quit();
            Base.setDriver(null);
        }
    }
}
*/
