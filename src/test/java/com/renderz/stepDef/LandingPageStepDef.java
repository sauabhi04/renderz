package com.renderz.stepDef;

import org.testng.Assert;

import com.renderz.pageObj.LandingPage;
import com.renderz.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LandingPageStepDef {

	private LandingPage landingPage = new LandingPage(DriverFactory.getDriver());

	@Given("User on the Renderz landing page")
	public void user_on_the_renderz_landing_page() throws InterruptedException {
		DriverFactory.getDriver().get("https://renderz.app/");
		landingPage.getWorkingWindow();
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
