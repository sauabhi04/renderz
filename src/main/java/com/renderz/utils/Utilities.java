package com.renderz.utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	WebDriver driver;

	public Utilities(WebDriver driver) { // Constructor
		this.driver = driver;
	}

	public void isPresentElement(By checkEle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(checkEle));
	}
	
	public static void backToWorkingWindow() {
		
	}
	
	public void backToWorkingTab() throws InterruptedException {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String workingPage = itr.next();
		String adBlockPage = itr.next();
		driver.switchTo().window(adBlockPage);
		driver.close();
		driver.switchTo().window(workingPage);
		Thread.sleep(2000);
	}

}
