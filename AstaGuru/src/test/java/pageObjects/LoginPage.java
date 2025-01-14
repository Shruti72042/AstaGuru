/*
package pageObjects;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import abstractComponents.AbstractComponents;

public class LoginPage{
	WebDriver driver;
	 Properties properties;
	 
	
	    public LoginPage(WebDriver driver) {
	    	//Initialization
	    	//super(driver);
	    	this.driver=driver;
	    	
	    	
	    	properties = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/Locators/login.properties");
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
}
	   

	    //driver.findElement(By.xpath(properties.getProperty("mobileNumberTxtFld"));
	    //WebElement MobileNoField;
	    public WebElement getMobileNumberField() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("mobileNumberTxtFld"))));
	        return driver.findElement(By.xpath(properties.getProperty("mobileNumberTxtFld")));
	    }
	    
	    public WebElement getTermsAndConditionsCheckbox() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("termsAndCondition")));
	    }

	    public WebElement getOtpButton() {
	        return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("getOTPButton")));
	        
	    }
	    //public void WaitForEnteringOTP(){ 
	    //    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time
	     //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("getStartedButton"))));

		  
	  //}
	    public WebElement ClickGetStartedBtn() throws InterruptedException {
	    	System.out.println("Attempting to locate the GET STARTED button.");
	    	WebElement getStartedButton = driver.findElement(By.xpath(properties.getProperty("getStartedButton")));
	    	System.out.println("Found GET STARTED button: " + getStartedButton.isDisplayed());
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Adjust wait time as needed
	    	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("getStartedButton"))));
	    	    Thread.sleep(5000);
	    	return driver.findElement(org.openqa.selenium.By.xpath(properties.getProperty("getStartedButton")));
	    }
	    
	    }
	  */


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginPage {
    WebDriver driver;
    Properties properties;
  

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/Locators/login.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
   
    public WebElement getMobileNumberField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("mobileNumberTxtFld"))));
        return driver.findElement(By.xpath(properties.getProperty("mobileNumberTxtFld")));
    }

    public WebElement getTermsAndConditionsCheckbox() {
        return driver.findElement(By.xpath(properties.getProperty("termsAndCondition")));
    }

    public WebElement getOtpButton() {
        //WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        //wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(properties.getProperty("resendOTPNowBtn"))));
        return driver.findElement(By.xpath(properties.getProperty("getOTPButton")));
    }
    
 
    

    public WebElement ClickGetStartedBtn() throws InterruptedException {
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("getStartedButton"))));
        return driver.findElement(By.xpath(properties.getProperty("getStartedButton")));
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("getStartedButton"))));
       // return driver.findElement(By.xpath(properties.getProperty("getStartedButton")));
    }
    
    public WebElement getInvalidOTPErrorMessage() {
       
        return driver.findElement(By.xpath(properties.getProperty("invalidOTPErrorMsg")));
    }
    
    public WebElement getInvalidMobileNoErrorMessage() {
        return driver.findElement(By.xpath(properties.getProperty("invalidMobileNoErrorMsg")));
}
    public WebElement clickOnLoginWithMail() {
        return driver.findElement(By.xpath(properties.getProperty("loginWithEmailBtn")));
    }
    public WebElement enterEmaill() {
        return driver.findElement(By.xpath(properties.getProperty("loginWithEmailFld")));
    }
    public WebElement withoutOTPErrorMsg() {
        return driver.findElement(By.xpath(properties.getProperty("loginWithoutOtpErrorMessage")));
    }
    public WebElement withoutMobileNoErrorMessage() {
    	return driver.findElement(By.xpath(properties.getProperty("withoutMobileNoErrorMessage")));
    }
}


	    
    