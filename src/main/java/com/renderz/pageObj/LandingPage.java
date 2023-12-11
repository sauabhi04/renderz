package com.renderz.pageObj;
//Page layout and feature changed on December 4

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.renderz.utils.Utilities;

public class LandingPage extends Utilities {

	private WebDriver driver;

	public LandingPage(WebDriver driver) { // Constructor
		super(driver);
		this.driver = driver;
	}

	// By locators
	private By logo = By.cssSelector(".gap-3 .bg-gradient-to-br");
	private By playersBtn = By.xpath("//a[text()='Players']");
	private By waitTillText = By.xpath("//h1[text()='FC 24']");
	private By homeText = By.xpath("//h2[text()='Latest players']");
	private By topAdBlock = By.cssSelector("img#clever_75815_topscroll_close");

	public boolean isAppearing() {
		return driver.findElement(logo).isDisplayed();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void onClick() throws InterruptedException {
		driver.findElement(playersBtn).click();
		Thread.sleep(5000);	}

	public String getPageTitle() {
		isPresentElement(waitTillText);
		return driver.getTitle();
	}
	
	public void backToHome() throws InterruptedException {
		isPresentElement(waitTillText);
		try{
			driver.findElement(topAdBlock).click();
		}catch(Exception e) {
			
		}
		Thread.sleep(1000);
		driver.findElement(logo).click();
	}
	
	public String atHome() {
		isPresentElement(homeText);
		return getTitle();
	}
}
