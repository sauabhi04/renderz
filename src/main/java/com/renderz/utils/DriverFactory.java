package com.renderz.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
	
	public WebDriver driver;
	
	public WebDriver init_driver() {
		ChromeOptions co = new ChromeOptions();
		//co.addExtensions(new File("/home/jit__04/all about JAVA/Extensions/AdBlock.crx"));
		co.addArguments("--headless=new");
		driver = new ChromeDriver(co);
		
		return driver;
	}

}
