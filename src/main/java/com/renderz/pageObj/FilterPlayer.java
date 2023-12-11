package com.renderz.pageObj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.renderz.utils.ExcelWriteUtil;
import com.renderz.utils.Utilities;

public class FilterPlayer extends Utilities {

	private WebDriver driver;
	private List<WebElement> filters;
	private List<WebElement> positionsEle;
	private List<WebElement> playersEle;
	int count = 0;

	public FilterPlayer(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By filterCheckBoxes = By.cssSelector("div.mt-4 .collapse-title");
	private By positions = By.cssSelector("label.cursor-pointer span.text-xs");
	private By adBlock = By.cssSelector(".renderz-ad-container button[data-svelte-h='svelte-1rfz1ec']");
	private By players = By.xpath("//main[@class='main'] //a //h3");
	private By playerName = By.cssSelector(".flex-col-reverse .details-list-item b");
	private By lowPriceLoc = By
			.xpath("(//div[@class='market-data--value-mini flex items-center svelte-u8a1u7'] /span)[1]");
	private By highPriceLoc = By
			.xpath("(//div[@class='market-data--value-mini flex items-center svelte-u8a1u7'] /span)[3]");

	public void getWorkingWindow() throws InterruptedException {
		backToWorkingTab();
	}

	public List<String> collectCheckBoxes() {
		filters = driver.findElements(filterCheckBoxes);
		List<String> filterNames = new ArrayList<String>();
		for (WebElement filter : filters) {
			filterNames.add(filter.getText());
		}
		return filterNames;
	}

	public void openFilter(String tabName) throws InterruptedException {
		for (WebElement filter : filters) {
			count++;
			if (tabName.equalsIgnoreCase(filter.getText())) {
				String clickCheckBox = "(//input[@class='svelte-1jd0273'])[" + count + "]";
				driver.findElement(By.xpath(clickCheckBox)).click();
				break;
			}

		}
	}

	public List<String> getAllPos() {
		List<String> allPos = new ArrayList<String>();
		positionsEle = driver.findElements(positions);
		for (WebElement pos : positionsEle) {
			allPos.add(pos.getText());
		}
		return allPos;
	}

	public void setPosition(String position) throws InterruptedException {
		driver.findElement(adBlock).click();
		Actions action = new Actions(driver);
		for (WebElement pos : positionsEle) {
			if (position.equalsIgnoreCase(pos.getText())) {
				String selectPos = "input[value='" + position + "']";
				action.scrollToElement(driver.findElement(By.cssSelector(selectPos))).perform();
				Thread.sleep(2000);
				driver.findElement(By.cssSelector(selectPos)).click();
				isPresentElement(By.cssSelector(".my-2 .items-center"));
				Thread.sleep(5000);
				break;
			}
		}
	}

	public void clickPlayers() throws InterruptedException {
		By pagesEle = By.xpath("//button[contains(@class,'btn join-item  svelte-1cmlip7')]");
		int pages = driver.findElements(pagesEle).size();
		Actions action = new Actions(driver);
		String nextPage = "";
		int currPage = 1;
		do {
			playersEle = driver.findElements(players);
			for (WebElement PlayerEle : playersEle) {
				Thread.sleep(500);
				action.moveToElement(PlayerEle).keyDown(Keys.CONTROL).click().build().perform();
			}
			try {
				currPage = currPage + 1;
				nextPage = "(//button[contains(@class,'btn join-item  svelte-1cmlip7')])[" + currPage + "]";
				driver.findElement(By.xpath(nextPage)).click();
				Thread.sleep(2000);
			} catch (Exception e) {
				currPage = currPage - 1;
				nextPage = "(//button[contains(@class,'btn join-item  svelte-1cmlip7')])[" + currPage + "]";
				System.out.println("-----End Of Player List------");
			}
			pages--;
		} while (pages > 0);
	}

	public void playerDetails() throws InterruptedException, IOException {
		String player, lowPrice, highPrice;
		Set<String> windows = driver.getWindowHandles();
		int windowNos = windows.size(), currWindow = 1;
		Iterator<String> itr = windows.iterator();
		String parentId = itr.next();

		ExcelWriteUtil.imprtToExcel(0, 0, "Player");
		ExcelWriteUtil.imprtToExcel(0, 1, "Low Price");
		ExcelWriteUtil.imprtToExcel(0, 2, "High Price");

		while (itr.hasNext() && currWindow < windowNos) {
			String playerpage = itr.next();
			driver.switchTo().window(playerpage);
			Thread.sleep(2000);
			player = driver.findElement(playerName).getText();
			ExcelWriteUtil.imprtToExcel(currWindow, 0, player);

			try {
				lowPrice = driver.findElement(lowPriceLoc).getText();
			} catch (Exception e) {
				lowPrice = "0";
			}
			ExcelWriteUtil.imprtToExcel(currWindow, 1, lowPrice);
			try {
				highPrice = driver.findElement(highPriceLoc).getText();
			} catch (Exception e) {
				highPrice = "0";
			}
			ExcelWriteUtil.imprtToExcel(currWindow, 2, highPrice);

			currWindow++;
		}
		driver.switchTo().window(parentId);
	}

}
