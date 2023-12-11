package com.renderz.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	private WebDriver driver;

	public Utilities(WebDriver driver) { // Constructor
		this.driver = driver;
	}

	public void isPresentElement(By checkEle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(checkEle));
	}
	
	public static void backToWorkingWindow() {
		
	}

}
