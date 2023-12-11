package com.renderz.utils;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser) {
		if (browser.equals("edge")) {
			EdgeOptions eo = new EdgeOptions();
			eo.addArguments("--remote-allow-origins=*");
			eo.addArguments("--incognito");
			tlDriver.set(new EdgeDriver(eo));
		} else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\EclipseWS\\drivers\\chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			co.setBinary("E:\\EclipseWS\\drivers\\chrome\\chrome.exe");
			co.addExtensions(new File("E:\\EclipseWS\\projects\\Extensions\\AdBlock.crx"));
			co.addArguments("--headless=new");
			tlDriver.set(new ChromeDriver(co));
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
