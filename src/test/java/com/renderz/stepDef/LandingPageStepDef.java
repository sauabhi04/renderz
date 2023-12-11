package com.renderz.stepDef;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.renderz.pageObj.LandingPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LandingPageStepDef {

	private WebDriver driver;
	private LandingPage landingPage;

	@Before // Hooks
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\EclipseWS\\drivers\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.setBinary("E:\\EclipseWS\\drivers\\chrome\\chrome.exe");
		// co.addExtensions(new File("/home/jit__04/all
		// aboutJAVA/Extensions/AdBlock.crx"));
		co.addArguments("--headless=new");
		driver = new ChromeDriver(co);
	}

	@After // Hooks
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@Given("User on the Renderz landing page")
	public void user_on_the_renderz_landing_page() {
		driver.get("https://renderz.app/");
		driver.manage().window().fullscreen();
		landingPage = new LandingPage(driver);
	}

	@Then("Application logo should be visible properly")
	public void application_logo_should_be_visible_properly() {
		Assert.assertTrue(landingPage.isAppearing(), "Logo is present on page...");
	}

	@Then("User should see the title {string}")
	public void user_should_see_the_title(String title) {
		String actualTitle = landingPage.getTitle();
		Assert.assertEquals(actualTitle, title, "Got Expected...");
	}

	@Given("User clicks on Players tab")
	public void user_clicks_on_players_tab() throws InterruptedException {
		landingPage.onClick();
	}

	@Then("User redirects to {string} page")
	public void user_redirects_to_page(String pageTitle) {
		String actualTitle = landingPage.getPageTitle();
		Assert.assertEquals(actualTitle, pageTitle, "Please check again...");
	}

	@When("User clicks on logo")
	public void user_clicks_on_logo() throws InterruptedException {
		landingPage.backToHome();
	}

	@Then("User redirects back to Home page")
	public void user_redirects_back_to_home_page() {
		System.out.println(landingPage.atHome());
	}

}
