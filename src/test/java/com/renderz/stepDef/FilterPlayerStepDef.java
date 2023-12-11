package com.renderz.stepDef;

import java.io.IOException;
import java.util.List;

import com.renderz.pageObj.FilterPlayer;
import com.renderz.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilterPlayerStepDef {

	private List<String> options;

	private FilterPlayer filterPlayer = new FilterPlayer(DriverFactory.getDriver());

	@Given("User is on Renderz Players page with minOvr as {int} and maxOvr as {int}")
	public void user_is_on_renderz_players_page_with_min_ovr_as_and_max_ovr_as(Integer minOvr, Integer maxOvr)
			throws InterruptedException {
		String url = "https://renderz.app/24/players?" + "minOvr=" + minOvr + "&" + "maxOvr=" + maxOvr;
		DriverFactory.getDriver().get(url);
		filterPlayer.getWorkingWindow();
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
