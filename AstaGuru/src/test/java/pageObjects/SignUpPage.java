package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

	WebDriver driver;
	 Properties properties;
	
	    public SignUpPage(WebDriver driver) {
	    	//Initialization
	    	//super(driver);
	    	this.driver=driver;
	    	
	    	
	    	properties = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/Locators/signUp.properties");
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
}
	   

	    public WebElement clickOnCreateAccountButton() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("createAnAccountBtn"))));
	        return driver.findElement(By.xpath(properties.getProperty("createAnAccountBtn")));
	    }
	    
	    public WebElement entername() {
	        return driver.findElement(org.openqa.selenium.By.id(properties.getProperty("enterNameFld")));
	    }

	    public WebElement enterEmail() {
	        return driver.findElement(org.openqa.selenium.By.id(properties.getProperty("emailIdFld")));
	    }
	  
	    public WebElement enterMobileNo() {
	    	return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("mobileNo")));
	    
	    }
	    
	    
	    public WebElement getTermsAndConditionsCheckbox() {
	        return driver.findElement(org.openqa.selenium.By.id(properties.getProperty("termsAndCondition")));
	    
	    }
	  
	    public WebElement getOtpButton() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("getOTPBtn")));
	        
	    }
	   
       
	    public WebElement clickOnGetStarted() {
	    	return driver.findElement(By.xpath(properties.getProperty("getStartedBtn")));
	    }
	    
	    public WebElement clickOnSubmit() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("submitBtn")));
	        
	    } 
	    
	    
	    public WebElement clickOnSkipNow() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("skipNowBtn")));
	        
        }
	    
	    public WebElement getSuccessfulMessage() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("successfulMessage")));
	    }
	    
	    public WebElement withoutMobileNoSignUp() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("blankMobliNoErrorMsg")));
	        
        }
	    public WebElement clickOnSignIn() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("signInBtn")));
	        
        }
	    
	    public WebElement getSignInText() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("signInText")));
	        
        }
}	   
