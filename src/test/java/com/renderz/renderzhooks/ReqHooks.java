package com.renderz.renderzhooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.renderz.utils.ConfigReader;
import com.renderz.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ReqHooks {
	private WebDriver driver;
	private DriverFactory driverFact;
	private ConfigReader configReader;
	Properties prop;

	@Before(order = 0)
	public void getProperties() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		driverFact = new DriverFactory();
		String browserName = prop.getProperty("browser");
		driver = driverFact.init_driver(browserName);
	}

	@After(order = 0)
	public void quitB() {
		driverFact = new DriverFactory();
		String browserName = prop.getProperty("browser");
		System.out.println("--------------------------------------");
		driverFact.init_driver(browserName).quit();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	@After(order = 1)
	public void takeScreenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			driverFact = new DriverFactory();
			String browserName = prop.getProperty("browser");
			driver = driverFact.init_driver(browserName);
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}

}
