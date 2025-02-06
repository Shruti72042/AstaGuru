package stepDefinitions;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(Auctions.class);
    private Map<String, String> lotStatusData = new HashMap<>();
    String viewCatalogue;
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
  
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
    	Map<String, Object> actionNameData = TestDataUtil.getTestData("auction");
        Map<String, Object> placingBid = (Map<String, Object>) actionNameData.get("placingBid");
        String auctionName = (String) placingBid.get("auctionName");
    	logger.info("...Scenario Started...");
    	driver = Base.getDriver();
       auctionsPage = new AuctionsPage(driver);
    	  actionTab = auctionsPage.getAuctionTab();
    	 Actions action = new Actions(driver);
		action.moveToElement(actionTab).perform();
		 auctionsPage.getAuctionsLink().click();
		Thread.sleep(5000); 
    }

    @And("Click on first View catalogue button")
    public void click_on_first_view_catalogue_button() throws InterruptedException {
    	Map<String, Object> actionNameData = TestDataUtil.getTestData("auction");
        Map<String, Object> placingBid = (Map<String, Object>) actionNameData.get("placingBid");
        String auctionName = (String) placingBid.get("auctionName");
      //  wait.until(ExpectedConditions.elementToBeClickable(auctionsPage.getFirstViewVatalogueBtn(auctionName)));
       
        //wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    	auctionsPage.getFirstViewVatalogueBtn(auctionName).click();
    	 //Thread.sleep(5000);
    }
   
    
   
    @Given("Click on Testing The Auction Section's View Catalogue Button")
    public void click_on_testing_the_auction_section_s_view_catalogue_button() {
    Map<String, Object> actionNameData = TestDataUtil.getTestData("auction");
    Map<String, Object> placingBid = (Map<String, Object>) actionNameData.get("LotsStatusFromTestingAuction");
    String auctionName = (String) placingBid.get("auctionName");
  //  wait.until(ExpectedConditions.elementToBeClickable(auctionsPage.getFirstViewVatalogueBtn(auctionName)));
   
    //wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	auctionsPage.getFirstViewVatalogueBtn(auctionName).click();
	 //Thread.sleep(5000);
   }
    
    @Then("Observe the lot list")
    public void observe_the_lot_list() throws InterruptedException {
    	List<WebElement> lots = (List<WebElement>) auctionsPage.getListOfLots();
    	 for (WebElement lot : lots) {
    	        String lotName = lot.getText();
    	        System.out.println(lotName);
    	        //Thread.sleep(5000);
    	}
    }
    

@Given("Click on View Catalogue button and store all the lots with respect to it's status")
public void click_on_view_catalogue_button_and_store_all_the_lots_with_respect_to_it_s_status() throws InterruptedException {
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
    
    private String status = ""; // Temporary storage for status
    private String currentLotStatus = ""; // Temporary storage for currentLotStatus

    @Given("Select the lot and place the bid and do validation")
    public void select_the_lot_and_place_the_bid_and_do_validation() throws InterruptedException, TimeoutException {
        Map<String, Object> lotData = TestDataUtil.getTestData("auction");
        Map<String, Object> placingBid = (Map<String, Object>) lotData.get("placingBid");
        String lotName = (String) placingBid.get("lotName");
        String successfulMessage = (String) placingBid.get("successfulMessage");

        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[text()='SERVICES']")));

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
                                auctionsPage.getBidIsClosedStatus(lotName)
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
                            Thread.sleep(5000);
                            auctionsPage.confirmBtn().click();
                            Thread.sleep(1000);
                            String actualSuccessfulMessage = auctionsPage.getPlacedBidSuccessfulMgs().getText();
                            Assert.assertEquals(actualSuccessfulMessage, successfulMessage);
                            Thread.sleep(5000);

                            currentLotStatus = auctionsPage.getCurrentlyLeadingStatus(lotName).getText();
                        }
                        break;
                }

                if (element != null && element.isDisplayed()) {
                    status = element.getText();
                    System.out.println("Status of the lot: " + status);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Status type not found: " + statusType);
            }
        }

        if (status.isEmpty() && currentLotStatus.isEmpty()) {
            throw new TimeoutException("No matching status found for the lot: " + lotName);
        }

        System.out.println("Status of the lot: " + status);
        System.out.println("Current lot status: " + currentLotStatus);
    }
    
    
    private static boolean isStatusStored = false;
    @And("Store the status")
    public void store_the_status() {
        if (isStatusStored) {
            System.out.println("Status already stored. Skipping update.");
            return; // Skip storing the status if already stored
        }

        // Validate and store status or currentLotStatus
        if (currentLotStatus != null && !currentLotStatus.isEmpty()) {
            lotStatusData.put("currentLotStatus", currentLotStatus);
            System.out.println("Stored currentLotStatus: " + currentLotStatus);
            isStatusStored = true;
        } else if (status != null && !status.isEmpty()) {
            lotStatusData.put("status", status);
            System.out.println("Stored status: " + status);
            isStatusStored = true;
        } else {
            logger.error("Failed to determine the status of the lot.");
            throw new AssertionError("Failed to determine the status of the lot.");
        }

    }
   
   /*
    @And("Check the status of the lot")
    public void check_the_status_of_the_lot() throws TimeoutException, InterruptedException {
        //String status = "";
        Map<String, Object> lotData = TestDataUtil.getTestData("auction");
        Map<String, Object> statusOfTheLot = (Map<String, Object>) lotData.get("statusOfTheLot");
        String lotName = (String) statusOfTheLot.get("lotName");
        System.out.println("Status of the lot: " + lotName);

        // Use WebDriverWait to handle potential delays
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(5000);
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
        //this.status=status;
        System.out.println("Status of the lot: " + status); 
    }
   */ 
    
  
    
    @And("Check the status of the lot")
    public void check_the_status_of_the_lot() throws TimeoutException, InterruptedException {
        String status = "";
        Map<String, Object> lotData = TestDataUtil.getTestData("auction");
        Map<String, Object> statusOfTheLot = (Map<String, Object>) lotData.get("statusOfTheLot");
        String lotName = (String) statusOfTheLot.get("lotName");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(5000);
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

                if (element != null && element.isDisplayed()) {
                    status = element.getText();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Status type not found: " + statusType);
            }
        }

        if (status.isEmpty()) {
            throw new TimeoutException("No matching status found for the lot: " + lotName);
        }
  
        System.out.println("Status of the lot: " + status);
     // Update lotStatusData
        if (!status.isEmpty()) {
            lotStatusData.put("finalStatus", status);
        } else {
            logger.error("Final status of the lot could not be determined.");
        }
    }
    
   
   
    @Given("Logout")
    public void logout() throws InterruptedException {
    	Thread.sleep(5000);
     accountTab = auctionsPage.getAccountTab();
   	 Actions action = new Actions(driver);
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
   
    
    @And("Validation for the final status of the lot after multiple bidding")
    public void validation_for_the_final_status_of_the_lot_after_multiple_bidding() {
        String storedCurrentLotStatus = (String) lotStatusData.get("currentLotStatus");
        String storedStatus = (String) lotStatusData.get("status");
        String finalStatus = (String) lotStatusData.get("finalStatus");

        // Log for debugging
        System.out.println("Stored currentLotStatus: " + storedCurrentLotStatus);
        System.out.println("Stored status: " + storedStatus);
        System.out.println("Final status: " + finalStatus);

        // Check that finalStatus is available
        if (finalStatus == null || finalStatus.isEmpty()) {
            throw new AssertionError("finalStatus is missing in lotStatusData.");
        }

        // Perform validation based on available data
        if (storedCurrentLotStatus != null && !storedCurrentLotStatus.isEmpty()) {
            // Validate finalStatus against currentLotStatus
            //Assert.assertEquals(storedCurrentLotStatus, finalStatus);
            Assert.assertEquals(finalStatus, "PLACE BID");
        } else if (storedStatus != null && !storedStatus.isEmpty()) {
            // Validate finalStatus against status
            Assert.assertEquals(storedStatus, finalStatus);
        } else {
            // Neither currentLotStatus nor status is available
            throw new AssertionError("Neither currentLotStatus nor status is stored in lotStatusData.");
        }
    }


    @Then("Navigate to home page")
    public void navigate_to_home_page() {
	auctionsPage.clickOnAstaGuruLink().click();
  
   }
}   



    
   