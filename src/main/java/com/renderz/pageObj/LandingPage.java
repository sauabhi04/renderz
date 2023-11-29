package com.renderz.pageObj;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

	private WebDriver driver;
	private List<WebElement> options;

	public LandingPage(WebDriver driver) { // Constructor
		this.driver = driver;
	}

	// By locators
	private By logo = By.className("renderz-logo");
	// private By title = By.
	private By playersBtn = By.xpath("//button[@class='cursor-pointer group']");
	private By btnOptions = By.cssSelector("ul.menu a"); // ul[@class='menu'] /a[text()='FC 24']

	public boolean isAppearing() {
		return driver.findElement(logo).isDisplayed();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public List<String> optionsOnHover() throws InterruptedException {
		driver.findElement(playersBtn).click();
		// Thread.sleep(2000);
		options = driver.findElements(btnOptions);
		List<String> optionsStr = new ArrayList<String>();
		for (WebElement option : options) {
			optionsStr.add(option.getText());
		}
		return optionsStr;
	}

	public void onClick(String elementToCLick) throws InterruptedException {
		// driver.findElement(playersBtn).click();
		// Thread.sleep(2000);
		for (WebElement option : options) {
			if (elementToCLick.equalsIgnoreCase(option.getText())) {
				option.click();
				// Thread.sleep(5000);
			}
		}
	}

	public String getPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mb-5")));
		return driver.getTitle();

	}
}
