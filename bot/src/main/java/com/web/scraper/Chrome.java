package com.web.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Initializes the google chrome web driver to control the chrome web browser.
 */
public class Chrome {
	// The web driver to control the chrome browser.
	private WebDriver driver;
	
	/*
	 * Initializes the chrome driver
	 * @param chromeDriverLocation - the location of the chrome driver on your computer.
	 * For example, your chrome driver could be located in "C:\home\webdrivers\chromedriver.exe".
	 */
	public Chrome(String chromeDriverLocation) {
		System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        this.driver = new ChromeDriver();
	}
	
	/*
	 * Returns the webdriver back to the caller.
	 * @return WebDriver.
	 */
	public WebDriver getDriver() {
		return this.driver;
	}
}
