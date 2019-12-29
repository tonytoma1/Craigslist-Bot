package com.web.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import  org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jsoup.Jsoup;
import com.web.scraper.Advertisement;

/*
 * A user's Craigslist account. Retrieves, posts, logins and sets the user's account information.
 */
public class CraigslistAccount {
	// The user's email.
	private String email;
	// The user's password.
	private String password;
	// The user's postal code.
	private String postalCode;
	// The user's city.
	private String city;
	// The user's street.
	private String street;

	/*
	 * Sets the email and password to the value of the arguments.
	 * @param email - the user's email.
	 * @param password - the user's password.
	 * @param postalCode - the user's postalCode.
	 * @param city - the user's city.
	 * @param street - the user's street address.
	 */
	public CraigslistAccount(String email, String password, String postalCode, String city, 
			String street) {
		this.email = email;
		this.password = password;
		this.postalCode = postalCode;
		this.city = city;
		this.street = street;
	}
	
	/*
	 * Takes the user's account information and logs into the user's account.
	 * @param email - the user's email.
	 * @param password - the user's password.
	 * @param driver - the google chrome web driver.
	 * @param url - the login page url.
	 * @return void.
	 */
	public void userLogin(String email, String password, WebDriver driver, String loginUrl) throws Exception {
		// Go to the login page.
		driver.get(loginUrl);
		
		// Wait until the body loads.
		WebElement firstResult = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.className("body")));
		
		// Enter the user's email into the email field.
		WebElement emailField = driver.findElement(By.id("inputEmailHandle"));
		emailField.clear();
		emailField.sendKeys(email);
		
		// Enter the user's password.
		WebElement passwordField = driver.findElement(By.id("inputPassword"));
		passwordField.clear();
		passwordField.sendKeys(password);
		
		// Click the login button.
		driver.findElement(By.id("login")).click();
		
		/*
		 * Check if the login failed. If the login didn't succeed,
		 * close the web driver.
		 */
		if(!checkIfUserIsLoggedIn(driver, loginUrl)) {
			driver.close();
			throw new Exception("The user has not been logged in, please check the login "
					+ "credentials and try again.");
			
		}
	}
	
	/*
	 * Checks if the user is logged in.
	 * @parm driver - the google web driver.
	 * @param loginUrl - the login url to craigslist
	 * @return boolean.
	 */
	public boolean checkIfUserIsLoggedIn(WebDriver driver, String loginUrl) {
		driver.get(loginUrl);
		
		// The current title of the webpage.
		String title = driver.getTitle();
		
		/*
		 * The user has successfully logged in if the title of the webpage 
		 * is "craigslist account", the user has not logged in otherwise.
		 */
		if (title.equalsIgnoreCase("craigslist account")) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	
	/* 
	 * Posts an ad to craigslist.
	 * @param advertisement - the craigslist advertisement.
	 * @param driver - the driver that controls the web browser.
	 * @param homePage - the home page url.
	 * @param account - the craigslist account the bot has logged into.
	 * @return void
	 */
	public void postAdvertisement(Advertisement advertisement, WebDriver driver, String homePage, 
			String imageName, CraigslistAccount account) {
		// go to the home page.
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver.get(homePage);
		
		/*
		 * Navigate through the craigslist until the advertisement form is reached.
		 */
		driver.findElement(By.id("post")).click();
		driver.findElement(By.xpath("//input[@value='1']")).click();
		driver.findElement(By.xpath("//input[@value='iwo']")).click();
		
		// Fill out the advertisement form.
		driver.findElement(By.id("PostingTitle")).sendKeys(advertisement.getTitle());
		driver.findElement(By.id("PostingBody")).sendKeys(advertisement.getDescription());
		driver.findElement(By.id("postal_code")).sendKeys(account.getPostalCode() + Keys.ENTER);
		
		
		// Fill out the location information.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("continue")));
		
		WebElement streetField = driver.findElement(By.id("xstreet0"));
		streetField.clear();
		streetField.sendKeys(account.getStreet());

		driver.findElement(By.className("continue")).click();
		driver.findElement(By.id("classic")).click();
		
		// Upload an image and continue
		driver.findElement(By.name("file"))
		.sendKeys(System.getProperty("user.dir") + "/src/main/resources/" + imageName);	
		driver.findElement(By.className("bigbutton")).click();
		
		// Publish the advertisement.
		driver.findElement(By.className("button")).click();
	}
	
	
	/*
	 * Sets the user's email to the value of the argument
	 * @param email - the user's email.
	 * @return void.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
	 * Retrieves the user's email.
	 * @return String.
	 */
	public String getEmail() {
		return this.email;
	}
	
	/*
	 * Sets the user's password to the value of the argument.
	 * @param password - the user's password.
	 * @return void.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	 * Retrieves the user's password.
	 * @return String.
	 */
	public String getPassword() {
		return this.password;
	}
	
	
	/*
	 * Retrieves the user's postal code.
	 * @return String.
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/*
	 * Sets the user's postal code.
	 * @param postalCode - the user's postal code.
	 * @return void.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/*
	 * Retrieves the user's city.
	 * @return String.
	 */
	public String getCity() {
		return city;
	}

	/*
	 * Sets the user's city.
	 * @param city - the user's city.
	 * @return void.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/*
	 * Get the user's street address.
	 * @return String.
	 */
	public String getStreet() {
		return street;
	}

	/*
	 * Set the user's street address.
	 * @param street - the user's street address.
	 * @return String.
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	

}
