package com.renderz.stepDef;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.renderz.pageObj.FilterPlayer;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilterPlayerStepDef {

	private WebDriver driver;
	private FilterPlayer filterPlayer;
	private List<String> options;

	@Before
	public void setup() {
		ChromeOptions co = new ChromeOptions();
		co.addExtensions(new File("E:\\EclipseWS\\projects\\Extensions\\AdBlock.crx"));
		co.addArguments("--headless=new");
		driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@After
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@Given("User is on Renderz Players page with minOvr as {int} and maxOvr as {int}")
	public void user_is_on_renderz_players_page_with_min_ovr_as_and_max_ovr_as(Integer minOvr, Integer maxOvr)
			throws InterruptedException {
		String url = "https://renderz.app/24/players?" + "minOvr=" + minOvr + "&" + "maxOvr=" + maxOvr;
		driver.get(url);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String workingPage = itr.next();
		String adBlockPage = itr.next();
		driver.switchTo().window(adBlockPage);
		driver.close();
		driver.switchTo().window(workingPage);
		Thread.sleep(2000);
		driver.manage().window().fullscreen();
		filterPlayer = new FilterPlayer(driver);
	}

	@Given("User set the position as {string} on {string} tab")
	public void user_set_the_position_as_on_tab(String position, String tabName) throws InterruptedException {
		options = filterPlayer.collectCheckBoxes();
		System.out.print("All filters ---> " + options);
		System.out.println("");
		filterPlayer.openFilter(tabName);
		options = filterPlayer.getAllPos();
		System.out.print("All positions ---> " + options);
		System.out.println("");
		filterPlayer.setPosition(position);
	}

	@When("User clicks on a player")
	public void user_clicks_on_a_player() throws InterruptedException {
		filterPlayer.clickPlayers();
	}

	@Then("Player details opens in a new tab")
	public void player_details_opens_in_a_new_tab() throws InterruptedException, IOException {
		filterPlayer.playerDetails();
	}

}
