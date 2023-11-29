package com.renderz.stepDef;

import java.util.List;

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
	private List<String> options;
	
	@Before 	//Hooks
	public void setup() {
//		ChromeOptions co = new ChromeOptions();
//		co.addArguments("--headless=new");
//		driver = new ChromeDriver(co);
		driver = new ChromeDriver();
	}
	
	@After 		//Hooks
	public void tearDown() {
		if(driver!=null)
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
	
	@Given("User hover on Players")
	public void user_hover_on_players() throws InterruptedException {
		options = landingPage.optionsOnHover();
		System.out.print("--> " + options);
	}

	@When("User click on {string}")
	public void user_click_on(String elementToCLick) throws InterruptedException {
		landingPage.onClick(elementToCLick);
	}

	@Then("User redirects to {string} page")
	public void user_redirects_to_page(String pageTitle) {
		String actualTitle = landingPage.getPageTitle();
		Assert.assertEquals(actualTitle, pageTitle, "Please check again...");
	}

}
