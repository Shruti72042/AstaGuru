package stepDefinitions;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.AuctionsPage;
import pageObjects.LoginPage;
import utils.TestDataUtil;
import utils.Base;

public class Auctions {
    WebDriver driver;
    LoginPage loginPage;
    AuctionsPage auctionsPage;
    WebElement actionTab ;
    WebElement accountTab;
    Properties properties;
    HashMap<String, String> lotStatuses = new HashMap<>();
    WebElement placeBidBtn;
    WebElement currentlyLeadingStatus;
    List<WebElement>lotNames;
    String currentLotStatus;
    String status;
    
    private Map<String, String> lotStatusData = new HashMap<>();
  
    @Given("User logs in with valid credentials")
    public void userLogsInWithValidCredentials() throws InterruptedException {
        driver = Base.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        auctionsPage = new AuctionsPage(driver);
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
    }
    
    
    @When("Mouse hover on Auctions tab and click on Auctions Link")
    public void mouse_hover_on_auctions_tab() throws InterruptedException {
    	driver = Base.getDriver();
       auctionsPage = new AuctionsPage(driver);
    	  actionTab = auctionsPage.getAuctionTab();
    	 Actions action = new Actions(driver);
		action.moveToElement(actionTab).perform();
		 auctionsPage.getAuctionsLink().click();
		 //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("viewResult")))); 
		 Thread.sleep(5000);
    }

    @And("Click on first View catalogue button")
    public void click_on_first_view_catalogue_button() throws InterruptedException {
    	Map<String, Object> actionNameData = TestDataUtil.getTestData("auction");
        Map<String, Object> placingBid = (Map<String, Object>) actionNameData.get("placingBid");
        String auctionName = (String) placingBid.get("auctionName");
    	
    	auctionsPage.getFirstViewVatalogueBtn(auctionName).click();
    	 Thread.sleep(5000);
    }
   
    @Then("Observe the lot list")
    public void observe_the_lot_list() throws InterruptedException {
    	List<WebElement> lots = (List<WebElement>) auctionsPage.getListOfLots();
    	 for (WebElement lot : lots) {
    	        String lotName = lot.getText();
    	        System.out.println(lotName);
    	        Thread.sleep(5000);
    	}
    }
    
    
    @And("Click on View Catalogue button and store all the lots with respect to it's Status")
    public void click_on_view_catalogue_button_and_store_lots() throws InterruptedException {
        List<WebElement> viewCatalogueButtons = auctionsPage.clickOnViewCatalogueButtons();

        for (int index = 0; index < viewCatalogueButtons.size(); index++) {
            // Refetch buttons to avoid StaleElementReferenceException
            viewCatalogueButtons = auctionsPage.clickOnViewCatalogueButtons();
            WebElement button = viewCatalogueButtons.get(index);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
           Thread.sleep(10000);
           // wait.until(ExpectedConditions.visibilityOfAllElements(auctionsPage.getAllLotNames()));
            // Collect lot details
           List<WebElement>  lotNames = auctionsPage.getAllLotNames();
            List<WebElement> bidIsClosedStatuses = (List<WebElement>) auctionsPage. getAllBidIsClosedStatuses();
            List<WebElement>  currentlyLeadingStatus= auctionsPage.getAllCurrentlyLeadingStatuses();
            List<WebElement> youHaveOwnStatuses = auctionsPage.getYouHaveOwnStatus();
            List<WebElement>placeBidBtn= auctionsPage.getAllPlaceBidBtn();
          
            // Process "Bid is Closed" lots
            for (int i = 0; i < lotNames.size(); i++) {
                String LotName = lotNames.get(i).getText();
                if (i < bidIsClosedStatuses.size()) {
                    String status2 = lotStatuses.put(LotName, "Bid is Closed");
                }
            }

            // Process "You Have Won" lots
            for (int i = 0; i < lotNames.size(); i++) {
                String LotName  = lotNames.get(i).getText();
                if (i < youHaveOwnStatuses.size()) {
                    String Status1 = lotStatuses.put(LotName, "You Have Won");
                   
                } 
            }

            for (int i = 0; i < lotNames.size(); i++) {
                String LotName  = lotNames.get(i).getText();
                if (i < currentlyLeadingStatus.size()) {
                    String Status3 = lotStatuses.put(LotName,"Currently Leading");
                   
                } 
            }
            
            for (int i = 0; i < lotNames.size(); i++) {
                String LotName  = lotNames.get(i).getText();
                if (i < placeBidBtn.size()) {
                    String Status4 = lotStatuses.put(LotName, "PLace Bid");  
                } 
            }
            // Navigate back to the auctions page
            driver.navigate().back();
            Thread.sleep(5000); // Allow the page to reload
        }
        // Print all collected lot statuses
        lotStatuses.forEach((LotName, status) -> {
            System.out.println("Lot: " + lotNames + ", Status: " + status);
        });
    }
    

    @Given("Select the lot and place the bid and do validation")
    public void select_the_lot_and_place_the_bid_and_do_validation() throws InterruptedException, TimeoutException {
    	String status = "";
    	
    	//    String status = null;
         Map<String, Object> lotData = TestDataUtil.getTestData("auction");
         Map<String, Object> placingBid = (Map<String, Object>) lotData.get("placingBid");
         String lotName = (String) placingBid.get("lotName");
	    	 String successfulMessage = (String) placingBid.get("successfulMessage");

         // Use WebDriverWait to handle potential delays
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

         // Iterate over possible statuses and determine the current status
         for (String statusType : new String[]{"currentlyLeading", "bidIsClosed", "youHaveWon", "placeBid"}) {
             WebElement element = null;

             try {
                 switch (statusType) {
                     case "currentlyLeading":
                         element = wait.until(ExpectedConditions.visibilityOf(
                                 auctionsPage.getCurrentlyLeadingStatus(lotName)
                         ));
                         break;

                     case "bidIsClosed":
                         element = wait.until(ExpectedConditions.visibilityOf(
                                 (WebElement) auctionsPage.getBidIsClosedStatus(lotName)
                         ));
                         break;
                         

                     case "youHaveWon":
                         element = wait.until(ExpectedConditions.visibilityOf(
                                 auctionsPage.getYouHaveWonStatus(lotName)
                         ));
                         break;

                     case "placeBid":
                         element = wait.until(ExpectedConditions.visibilityOf(
                                 auctionsPage.clickOnPlaceBidBtn(lotName)));
                         if (element != null && element.isDisplayed()) {
                             auctionsPage.clickOnPlaceBidBtn(lotName).click();
                             auctionsPage.agreeBtn().click();
                             Thread.sleep(5000); // Explicit wait, can be replaced with WebDriverWait
                             auctionsPage.confirmBtn().click();
                             Thread.sleep(1000);
                         
                  	 
                  	    	 String actualSuccessfulMessage = auctionsPage.getPlacedBidSuccessfulMgs().getText();
                  	    	  System.out.println("ActualMessage:" + actualSuccessfulMessage);
                  	    	 System.out.println("expectedMessage:" +successfulMessage );
                  	        Assert.assertEquals(actualSuccessfulMessage, successfulMessage);
                  	        Thread.sleep(5000);
                  	     
                          // After placing the bid, get the status
                          String currentLotStatus = auctionsPage.getCurrentlyLeadingStatus(lotName).getText();
                          System.out.println("Current Lot Status after placing the bid: " + currentLotStatus);
           
                         }
                         break;
                 }
                 // If an element is found and is displayed, retrieve the text and exit the loop
                 if (element != null && element.isDisplayed()) {
                     status = element.getText();
                     break;
                 }
             } catch (Exception e) {
                 // Handle exceptions for this status and proceed with the next one
                 System.out.println("Status type not found: " + statusType);
             }
                }
             if (status.isEmpty()) {
                 throw new TimeoutException("No matching status found for the lot: " + lotName);
             }
      
             System.out.println("Status of the lot: " + status); 
         }
    
    @And("click on Agree Button for aceepting terms and condition")
    public void click_on_agree_button_for_aceepting_terms_and_condition() throws InterruptedException {
    	auctionsPage.agreeBtn().click();
    	Thread.sleep(5000);
    }
     
    
    @And("click on Confirm Button")
    public void click_on_confirm_button() throws InterruptedException {
    	auctionsPage.confirmBtn().click();
    	Thread.sleep(1000);
    }
    
    @And("Validate the Successful Message")
    public void validate_the_successful_message() throws InterruptedException {
    	
 	    	Map<String, Object> auctionData = TestDataUtil.getTestData("auction");
 	    	Map<String, Object> 	placingBid=(Map<String, Object>) auctionData.get("placingBid");
 	    	 String successfulMessage = (String) placingBid.get("successfulMessage");
 	 
 	    	 String actualSuccessfulMessage = auctionsPage.getPlacedBidSuccessfulMgs().getText();
 	    	  System.out.println("ActualMessage:" + actualSuccessfulMessage);
 	    	 System.out.println("expectedMessage:" +successfulMessage );
 	        Assert.assertEquals(actualSuccessfulMessage, successfulMessage);
 	        //driver.quit();
    }
    
    @And("Check the status of the lot")
    public void check_the_status_of_the_lot() throws TimeoutException {
        //String status = "";
        Map<String, Object> lotData = TestDataUtil.getTestData("auction");
        Map<String, Object> statusOfTheLot = (Map<String, Object>) lotData.get("statusOfTheLot");
        String lotName = (String) statusOfTheLot.get("lotName");
        System.out.println("Status of the lot: " + lotName);

        // Use WebDriverWait to handle potential delays
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Iterate over possible statuses and determine the current status
        for (String statusType : new String[]{"currentlyLeading", "bidIsClosed", "placeBid", "youHaveWon"}) {
            WebElement element = null;

            try {
                switch (statusType) {
                    case "currentlyLeading":
                        element = wait.until(ExpectedConditions.visibilityOf(
                                auctionsPage.getCurrentlyLeadingStatus(lotName)
                        ));
                        break;

                    case "bidIsClosed":
                        element = wait.until(ExpectedConditions.visibilityOf(
                                (WebElement) auctionsPage.getBidIsClosedStatus(lotName)
                        ));
                        break;

                    case "placeBid":
                        element = wait.until(ExpectedConditions.visibilityOf(
                                auctionsPage.clickOnPlaceBidBtn(lotName)));
                       
                        break; 
                        
                    case "youHaveWon":
                        element = wait.until(ExpectedConditions.visibilityOf(
                                auctionsPage.getYouHaveWonStatus(lotName)
                        ));
                        break;
                }

                // If an element is found and is displayed, retrieve the text and exit the loop
                if (element != null && element.isDisplayed()) {
                    status = element.getText();
                    break;
                }
            } catch (Exception e) {
                // Handle exceptions for this status and proceed with the next one
                System.out.println("Status type not found: " + statusType);
            }
        }

        // If no status is found, throw an exception
        if (status.isEmpty()) {
            throw new TimeoutException("No matching status found for the lot: " + lotName);
        }
        this.status=status;
 
        System.out.println("Status of the lot: " + status); 
    }
   
    @Given("Logout")
    public void logout() throws InterruptedException {
    	Thread.sleep(5000);
     accountTab = auctionsPage.getAccountTab();
   	 Actions action = new Actions(driver);
	     //WebElement actionTab = null;
		action.moveToElement(accountTab).perform();
		//Thread.sleep(5000);
		 auctionsPage.getLogoutLink().click();
    }
  
    @Then("Close the browser")
    public void Close_the_browser() {
        if (driver != null) {
            driver.quit();
            driver = null; // Prevent reuse of the terminated driver
        }
    }

    @Given("Login with other account")
    public void loginWithOtherAccount() throws InterruptedException {
    	Thread.sleep(5000);
    	WebElement accountLink = auctionsPage.getAccountTab();
    	Actions action = new Actions(driver);
		action.moveToElement(accountLink).perform();
		auctionsPage.getLoginLink().click();
		//driver.findElement(By.xpath("//div[text()='Login']")).click();
	
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }

        // Fetch account details for login
        Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
        Map<String, Object> anotherAccount = (Map<String, Object>) loginData.get("anotherAccount");
        String mobileNo = (String) anotherAccount.get("mobileNo");

        // Input mobile number and perform login steps
        loginPage.getMobileNumberField().sendKeys(mobileNo);
        loginPage.getTermsAndConditionsCheckbox().click();
        loginPage.getOtpButton().click();
        Thread.sleep(25000);
        loginPage.ClickGetStartedBtn().click();
        Thread.sleep(10000);
    }
    
    private void login(String mobileNo) throws InterruptedException {
    	Map<String, Object> loginData = (Map<String, Object>) TestDataUtil.getTestData("login");
        Map<String, Object> validLogin = (Map<String, Object>) loginData.get("validLogin");
        String mobileNo1 = (String) validLogin.get("mobileNo");
        loginPage.getMobileNumberField().sendKeys(mobileNo1);
        loginPage.getTermsAndConditionsCheckbox().click();
        loginPage.getOtpButton().click();

        // Wait for manual OTP entry
        Thread.sleep(25000);

        loginPage.ClickGetStartedBtn().click();

        // Wait for navigation to complete
        Thread.sleep(10000);
    }   
    
    @And("Clear the cookies")
    	public void clear_the_cookies() {
    	driver.navigate().back();
    	driver.manage().deleteAllCookies();
    	driver.navigate().refresh();

    }

    @Given("Check the final status of the lot after bidding")
    public void chech_the_final_status_of_the_lot_after_bidding() {

    	try {
            String currentLotStatus = lotStatusData.get("currentLotStatus");
            String status = lotStatusData.get("status");

            if ("CURRENTLY LEADING".equalsIgnoreCase(currentLotStatus)) {
                if ("PLACE BID".equalsIgnoreCase(status)) {
                    System.out.println("Validation Passed: Status transitioned correctly from 'Currently Leading' to 'Place Bid'.");
                } else {
                    System.out.println("Validation Failed: Expected 'Place Bid' but got: " + status);
                    Assert.fail("Validation Failed: Incorrect status transition.");
                }
            } else {
                System.out.println("Validation Failed: Current Lot Status is not 'CURRENTLY LEADING'. Actual status: " + currentLotStatus);
                Assert.fail("Validation Failed: Incorrect initial status.");
            }
        } catch (Exception e) {
            System.out.println("Exception during validation: " + e.getMessage());
            Assert.fail("Validation Failed due to an exception.");
        }
    }
   

    @Given("Select the lot and make proxy bid and do validation")
    public void select_the_lot_and_make_proxy_bid_and_do_validation() throws InterruptedException {
    	Map<String, Object> lotData = TestDataUtil.getTestData("auction");
        Map<String, Object> placingBid = (Map<String, Object>) lotData.get("proxyBid");
        String lotName = (String) placingBid.get("lotName");
        
        auctionsPage.getProxyBidBtn(lotName).click();
        Thread.sleep(5000);
       
    }


@Then("Navigate to home page")
public void navigate_to_home_page() {
	auctionsPage.clickOnAstaGuruLink().click();
  // driver.findElement(By.xpath("//a//img[@class='h-[28px] w-[110px] cursor-pointer lg:h-[52px] lg:w-[203px]']")).click();
   }
}


    



    
   