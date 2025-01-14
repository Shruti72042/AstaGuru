package stepDefinitions;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.SignUpPage;
import utils.TestDataUtil;

public class SignUp{
	SignUpPage signUp;
	WebDriver driver;

    //private boolean isMobileNoValid = true; // Flag to control the flow

    @Given("Launch Chrome Browser")
    public void user_launches_chrome_browser() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Initialize WebDriver
        driver.manage().window().maximize();

        signUp= new SignUpPage(driver);
        driver.get("https://development.d2mp9949i73scy.amplifyapp.com/create-account?returnUrl=/");
        System.out.println("Chrome Browser launched successfully.");
    }

    @When("Click on create an account button")
    public void user_clicks_on_create_an_account_button() {
        signUp.clickOnCreateAccountButton().click();
        
    }

    @When("Enter Name")
    public void enters_name_with() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> validSignUp = (Map<String, Object>) SignUpData.get("validSignUp");
    	String name = (String) validSignUp.get("name");
    	signUp.entername().sendKeys(name);
        
    }

    @When("Enter Email-ID")
    public void enters_email_id_with() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> validSignUp = (Map<String, Object>) SignUpData.get("validSignUp");
    	String emailId = (String) validSignUp.get("emailId");
    	signUp.enterEmail().sendKeys(emailId);
          }

    @When("Enter Mobile-No")
    public void enters_mobile_no_with() throws InterruptedException {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> validSignUp = (Map<String, Object>) SignUpData.get("validSignUp");
    	String mobileNo = (String) validSignUp.get("mobileNo");
    	signUp.enterMobileNo().sendKeys(mobileNo);
        }
    
    @When("Click on Terms and Condition check box")
    public void clicks_on_terms_and_condition_check_box() {
        //if (!isMobileNoValid) return; // Skip if Mobile-No is invalid
 	   signUp.getTermsAndConditionsCheckbox().click();
    }

    @When("Click on Get OTP button")
    public void clicks_on_get_otp_button() throws InterruptedException {
        //if (!isMobileNoValid) return; 
        signUp.getOtpButton().click();
 	   Thread.sleep(32000);// Skip if Mobile-No is invalid
    }

    @When("Enter OTP and Clicks on Get Started button")
    public void enter_otp_and_clicks_on_get_started_button() throws InterruptedException {
      
        signUp.clickOnGetStarted().click();
 	   Thread.sleep(35000);
        System.out.println("Entered OTP and clicked on Get Started button.");
    }

    @When("Enter OTP received on email and clicks on submit button")
    public void enters_otp_received_on_email_and_clicks_on_submit_button() throws InterruptedException {
        signUp.clickOnSubmit().click();
    	Thread.sleep(10000);
       
    }

    @When("Observe Successful SignUp Message")
    public void user_receives_successful_message() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> validSignUp = (Map<String, Object>) SignUpData.get("validSignUp");
    	String successfulMessage1 = (String) validSignUp.get("successfulMessage");
        String actualMessage= signUp.getSuccessfulMessage().getText();
        System.out.println("actual SuccessfulMessage :" + actualMessage);
        Assert.assertEquals(actualMessage, successfulMessage1);
        System.out.println("User received a successful registration message.");
    }

    @When("click on Skip now button")
    public void user_clicks_on_skip_now_button() throws InterruptedException {
        //if (!isMobileNoValid) return; // Skip if Mobile-No is invalid
        signUp.clickOnSkipNow().click();
		Thread.sleep(5000);
       
    }

    @Then("Navigation to Auctions page")
    public void user_navigates_to_auctions_page_with_and() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> validSignUp = (Map<String, Object>) SignUpData.get("validSignUp");
    	String expectedResult = (String) validSignUp.get("expectedResult");
        String actualURl=driver.getCurrentUrl();
        Assert.assertEquals(actualURl, expectedResult);
        System.out.println("actualResult:"+ actualURl);	
            Assert.assertEquals(actualURl,expectedResult);
            driver.quit();
        } 
        
    @When("Left Blank Mobile No")
    public void left_blank_mobile_no() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> missingMobileNo = (Map<String, Object>) SignUpData.get("missingMobileNo");
    	String mobileNo = (String) missingMobileNo.get("mobileNo");
    	signUp.enterMobileNo().sendKeys(mobileNo);
    	
    }
    
   
    @Then("Observe the error message received for without Mobile No")
    public void observe_the_error_message_with() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> missingMobileNo = (Map<String, Object>) SignUpData.get("missingMobileNo");
    	String errorMessage = (String) missingMobileNo.get("errorMessage");
    	String actualErrorMessage = signUp.withoutMobileNoSignUp().getText();
        System.out.println("actualResult:"+ actualErrorMessage);	
        Assert.assertEquals(actualErrorMessage, errorMessage);
        driver.quit();
}
    @When("Click on Sign In button")
    public void enter_otp_and_clicks_on_sign_in_button() {
    signUp.clickOnSignIn().click();
    //WebDriverWait wait=new WebDriverWait(driver, 10);
     
    }
    
    @Then("observe navigation to Sign In page")
    public void observe_navigation_to_sign_in_page() {
    	Map<String, Object> SignUpData = (Map<String, Object>) TestDataUtil.getTestData("signup");
    	Map<String, Object> signInLink = (Map<String, Object>) SignUpData.get("signInLink");
    	String expectedResult = (String) signInLink.get("expectedResult");
    	String actualErrorMessage = signUp.getSignInText().getText();
        System.out.println("actualResult:"+ actualErrorMessage);	
        Assert.assertEquals(actualErrorMessage, expectedResult);
        driver.quit();
    }
    @And("Close Browser")
    public void closesTheBrowser() {
        driver.quit();
    }
}
