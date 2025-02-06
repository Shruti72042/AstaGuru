package pageObjects;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionsPage {
	WebDriver driver;
	 Properties properties;
	
	    public AuctionsPage(WebDriver driver) {
	    	this.driver=driver;
	    	properties = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/Locators/auctions.properties");
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }        
		public WebElement getAuctionTab() {
			  WebElement actionTab = driver.findElement(By.xpath(properties.getProperty("auctionsTab")));
		    return actionTab;
		}
	    
	    public WebElement getAuctionsLink() {
	        return driver.findElement(By.xpath(properties.getProperty("auctionsLink")));
	    
	    }
	    public WebElement getFirstViewVatalogueBtn(String auctionName) {
	    	String ViewCatalogueLocator = properties.getProperty("viewCatalogueBtn1");
	    	String ViewCatalogue = ViewCatalogueLocator.replace("{}", auctionName);
	    	WebElement ViewCatalogueBtn = driver.findElement(By.xpath(ViewCatalogue));
	       return ViewCatalogueBtn;
	    
	    }
	    public List<WebElement> getListOfLots() {
	        return driver.findElements(By.xpath(properties.getProperty("lotsInTestingTheAuctionSection")));
	    
	    }
	    	
	    public List<WebElement> clickOnViewCatalogueButtons(){
	    	
	    	return driver.findElements(By.xpath(properties.getProperty("viewCatalogueButtons")));
	    }
	    public List<WebElement> getAllLotNames() {
	    	return driver.findElements(By.xpath(properties.getProperty("lotNames")));
		
	    }
	    
	    public  List<WebElement>  getAllBidIsClosedStatuses() {
	    return driver.findElements(By.xpath(properties.getProperty("bidIsClosedStatuses")));
	    }
           
	    public  List<WebElement>  getAllCurrentlyLeadingStatuses() {
		    return driver.findElements(By.xpath(properties.getProperty("currentlyLeaingStatuses")));
		    }
	    
	    public  List<WebElement>  getAllPlaceBidBtn() {
		    return driver.findElements(By.xpath(properties.getProperty("placeBidBtn")));
		    }
	    
	    public   WebElement  getBidIsClosedStatus(String lotName) {
	    	String BidClosed = properties.getProperty("BidIsClosedLocator");
            String bidIsClosed = BidClosed.replace("{}", lotName);
          WebElement bidIsClosedStatus = driver.findElement(By.xpath(bidIsClosed ));
            return bidIsClosedStatus;
        
	    }
	    
	    public List<WebElement> getYouHaveOwnStatus() {
	    	return driver.findElements(By.xpath(properties.getProperty("youHaveOwnStatuses")));
		
	    }
	    public WebElement clickOnPlaceBidBtn(String lotName) {
	            String locatorTemplate = properties.getProperty("placeBidLocator");
	            String dynamicLocator = locatorTemplate.replace("{}", lotName);
	            WebElement placeBidBtn = driver.findElement(By.xpath(dynamicLocator));
	            return placeBidBtn;
	    }
	    
	    public WebElement agreeBtn() {
	        return driver.findElement(By.xpath(properties.getProperty("agreeBtn")));
	    
	    }
	    public WebElement confirmBtn() {
	        return driver.findElement(By.xpath(properties.getProperty("confirmBtn")));
	       
	    }
	    
	    public WebElement getPlacedBidSuccessfulMgs() {
	    	 
	        return driver.findElement(By.xpath(properties.getProperty("placedBidSuccessfulMsg")));
	    
	    }
	    
	    public WebElement getCurrentlyLeadingStatus(String lotName) {
	    	 
	    	 String currentlyLeadingLocator = properties.getProperty("currentlyLeadingStatusLocator");
	            String currentlyLeading = currentlyLeadingLocator.replace("{}", lotName);
	           WebElement currentlyLeadingStatus= driver.findElement(By.xpath(currentlyLeading));
	        return currentlyLeadingStatus;
	    
	    }
	    
	    public WebElement getYouHaveWonStatus(String lotName) {
	    	 
	    	 String youHaveWonLocator = properties.getProperty("youHaveWonLocator");
	            String currentlyLeading = youHaveWonLocator.replace("{}", lotName);
	           WebElement youHaveWonStatus= driver.findElement(By.xpath(currentlyLeading));
	        return youHaveWonStatus;
	    
	    }
	    
	    public WebElement lotStatus(String lotName) {
	    	 
	    	 String lotStausLocator = properties.getProperty("lotStatus");
	            String actualLotStatus = lotStausLocator .replace("{}", lotName);
	           WebElement lotStaus= driver.findElement(By.xpath(actualLotStatus));
	        return lotStaus;
	    
	    }
	    
	    public WebElement getAccountTab() {
	    	return driver.findElement(By.xpath(properties.getProperty("accountTab")));
	    }
	    
	    public WebElement getLogoutLink() {
	    	return driver.findElement(By.xpath(properties.getProperty("logoutLink")));
	    }
	    
	    public WebElement getLoginLink() {
	    	return driver.findElement(By.xpath(properties.getProperty("loginLink")));
	    }
	    
	    public WebElement getProxyBidBtn(String lotName) {
	    	 String proxyBidLocator = properties.getProperty("proxyBidLocator");
	            String proxyBid = proxyBidLocator.replace("{}", lotName);
	           WebElement proxyBidBtn= driver.findElement(By.xpath(proxyBid));
	        return proxyBidBtn;
	    
	    }
	    
	    public WebElement clickOnAstaGuruLink() {
	    	return driver.findElement(By.xpath(properties.getProperty("astaGuruLink")));
			
	    }	    
}
