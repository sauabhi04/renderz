package com.renderz.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.renderz.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private WebDriver driver;
	private DriverFactory driverFact;

	@Before
	public void setup() {
		driverFact = new DriverFactory();
		driver = driverFact.init_driver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@After(order = 0)
	public void quitB() {
		driver.quit();
	}

	@After(order = 1)
	public void takeScreenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}

}
