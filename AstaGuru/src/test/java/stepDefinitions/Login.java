package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;


import utils.TestDataUtil;
public class Login {

	
	    WebDriver driver;
	    LoginPage loginPage;
	    Properties properties;
	  
	    @Given("Launch chrome browser")
	    public void userLaunchesChromeBrowser() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://development.d2mp9949i73scy.amplifyapp.com/create-account?returnUrl=/");
	        loginPage = new LoginPage(driver);
	    }
	    
	    @When("User enters valid mobile number for valid login")
	    public void userEntersValidMobileNoForValidLogin() {
	        Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
	        Map<String, Object> validLogin = (Map<String, Object>) loginData.get("validLogin");
	        String mobileNo = (String) validLogin.get("mobileNo");
	        loginPage.getMobileNumberField().sendKeys(mobileNo);
	    }
	    @When("User enters invalid mobile number for invalid login")
	    public void userEntersInvalidMobileNoForInvalidLogin() {
	    	 Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
		        Map<String, Object> invalidLogin = (Map<String, Object>) loginData.get("invalidLogin");
		        String mobileNo = (String) invalidLogin.get("mobileNo");
		        loginPage.getMobileNumberField().sendKeys(mobileNo);
	    }

	    @And("Clicks on Terms and Condition CheckBox")
	    public void clicksOnTermsAndConditionCheckBox() throws InterruptedException {
	        loginPage.getTermsAndConditionsCheckbox().click();
	        Thread.sleep(5000);
	    }

	    @And("Clicks on Get OTP and enter OTP Manually")
	    public void clicksOnGetOTP() throws InterruptedException {
	        loginPage.getOtpButton().click();
	      Thread.sleep(25000);
	    }

	    @And("Users click on Get Started")
	    public void usersClickOnGetStarted() throws InterruptedException {
	    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25)); // Adjust wait time as needed
    	    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("getStartedButton"))));
	    	
	    	
	        loginPage.ClickGetStartedBtn().click();
	        Thread.sleep(5000);
	    }

	    @Then("User navigates to the home page for valid login")
	    public void userNavigatesToTheHomePageForValidLogin() {
	        //String expectedUrl = (String) ((Map<String, Object>) testData.get("validLogin")).get("expectedUrl");
	    	Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
	    	Map<String, Object> validLogin = (Map<String, Object>) loginData.get("validLogin");
	    	 String expectedUrl = (String) validLogin.get("expectedUrl");
	        String actualUrl = driver.getCurrentUrl();
	        System.out.println("ActualUrl:" + actualUrl);
	        System.out.println("Expected  Url:" + expectedUrl);
	        Assert.assertEquals(actualUrl, expectedUrl);
	        driver.quit();
	    }
	   
	    @Then("User sees error message for invalid otp")
	    public void userSeesErrorMessageForInvalidLogin() {
	    	Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
	    	Map<String, Object> invalidLogin = (Map<String, Object>) loginData.get("invalidLogin");
	    	 String expectedError = (String) invalidLogin.get("expectedError");
	        String actualErrorMessage = loginPage.getInvalidOTPErrorMessage().getText();
	        
	        System.out.println("ActualError Message:" + actualErrorMessage);
	        System.out.println("Expected  Message:" + expectedError);
	        Assert.assertEquals(actualErrorMessage, expectedError);
	        driver.quit();
	    } 
	       

	    @When("User clicks on Login with Email button")
	    public void userClicksOnLoginWithEmailButton() {
	        loginPage.clickOnLoginWithMail().click();
	    }

	    @And("User enters valid email")
	    public void userEntersValidEmail() throws InterruptedException {
	    	 Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
		        Map<String, Object> emailLogin = (Map<String, Object>) loginData.get("emailLogin");
		        String email = (String) emailLogin.get("email");
		        loginPage.enterEmaill().sendKeys(email);
		        Thread.sleep(8000);
	    
	    }

	    @Then("User navigates to the auctions page")
	    public void userNavigatesToTheAuctionsPage() {
	    	Map<String, Object> loginData = TestDataUtil.getTestData("login");
	    	Map<String, Object> 	emailLogin=(Map<String, Object>) loginData.get("emailLogin");
	    	 String expectedUrl = (String) emailLogin.get("expectedUrl");
	    	 String actualUrl= driver.getCurrentUrl();
	    	  System.out.println("ActualError url:" + actualUrl);
	        Assert.assertEquals(actualUrl, expectedUrl);
	        driver.quit();
	    }
	    @Then("Error Message for login without entering OTP")
	    public void error_message_for_login_without_entering_otp() {
	    	Map<String, Object> loginData = TestDataUtil.getTestData("login");
	    	Map<String, Object>loginWithoutOTP  = (Map<String, Object>) loginData.get("loginWithoutOTP");
	    	 String expectedErrorMessage = (String) loginWithoutOTP.get("expectedErrorMessage");
	    	 String actualErrorMessage= loginPage.withoutOTPErrorMsg().getText();
	    	 System.out.println("ActualError Error Message:" + actualErrorMessage);
	    	 System.out.println("Expected Error Message url:" + expectedErrorMessage);
		        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		      
	    }
	   
	    @Given("Left blank Mobile No field")
	    public void left_blank_mobile_no_field() {
	    	 Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
		        Map<String, Object> loginWithoutMobileNo = (Map<String, Object>) loginData.get("loginWithoutMobileNo");
		        String mobileNo = (String) loginWithoutMobileNo.get("mobileNo");
		        loginPage.getMobileNumberField().sendKeys(mobileNo);
	    
	    }
	    @Then("Validate the error message for login witout Mobile No")
	    public void validate_the_error_message_for_login_witout_mobile_no() {  
	    	Map<String, Object> loginData = TestDataUtil.getTestData("login");
	    	Map<String, Object>loginWithoutMobileNo  = (Map<String, Object>) loginData.get("loginWithoutMobileNo");
	    	 String expectedErrorMessage = (String) loginWithoutMobileNo.get("expectedErrorMessage");
	    	 String actualErrorMessage= loginPage.withoutMobileNoErrorMessage().getText();
	    	 System.out.println("ActualError Error Message:" + actualErrorMessage);
	    	 System.out.println("Expected Error Message :" + expectedErrorMessage);
		        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	    }
	    @And("Closes the browser")
	    public void closesTheBrowser() {
	        driver.quit();
	    }
	}




