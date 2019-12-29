package com.web.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.web.scraper.CraigslistAccount;
import com.web.scraper.Chrome;
import io.github.cdimascio.dotenv.Dotenv;
import com.web.scraper.Advertisement;

/**
 * Starts the chrome driver and posts ads to craigslist.
 */
public class App {
	
    public static void main( String[] args ) throws IOException {
    	// Locate and load the .env file.
    	Dotenv dotenv = Dotenv.configure()
    					.directory(System.getProperty("user.dir") + "/src/main/resources")
    					.ignoreIfMalformed()
    					.load();
    	
    	// Load the user's credentials from the .env file.
    	String userEmail = dotenv.get("USER_EMAIL");
    	String userPassword = dotenv.get("USER_PASSWORD");
    	String userStreet = dotenv.get("USER_STREET");
    	String userCity = dotenv.get("USER_CITY");
    	String userPostalCode = dotenv.get("USER_POSTAL_CODE");
    	String websiteUrl = dotenv.get("WEBSITE_URL");
    	
    	// The name of the image you want to upload. All images must be stored 
    	// in "src/main/resources"
    	String imageName = "iphone.jpg";
    	
    	// Create and initalize the chrome driver. 
    	String chromeDriverLocation = dotenv.get("CHROME_DRIVER_LOCATION");
    	Chrome chromeDriver = new Chrome(chromeDriverLocation);
    	
    	// get the login and website url.
    	
    	String loginUrl = "https://accounts.craigslist.org/login?rt=L&rp=%2Flogin%2Fhome";
    	
    	// Create the advertisement.
    	Advertisement advertisement = new Advertisement("test", "testing description");
    	
    	// Initialize the Craigslist account
    	CraigslistAccount userAccount = new CraigslistAccount(userEmail, userPassword, userPostalCode,
    									userCity, userStreet);
    	
    	WebDriver driver = chromeDriver.getDriver();
    	
    	driver.get(websiteUrl);
    	
    	try {
    		userAccount.userLogin(userAccount.getEmail(), userAccount.getPassword(), driver, loginUrl);
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	userAccount.postAdvertisement(advertisement, driver, websiteUrl, imageName, userAccount);
    }


}


