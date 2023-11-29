package com.renderz.pageObj;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.checkerframework.checker.units.qual.min;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterPlayer {

	private WebDriver driver;
	private List<WebElement> filters;
	private List<WebElement> positionsEle;
	private List<WebElement> playersEle;
	int count = 0;

	public FilterPlayer(WebDriver driver) {
		this.driver = driver;
	}

	private By filterCheckBoxes = By.cssSelector("div.mt-4 .collapse-title");
	private By posToggle = By.cssSelector("label.relative input.peer");
	private By positions = By.cssSelector("label.cursor-pointer span.text-xs");
	private By adBlock = By.cssSelector(".renderz-ad-container.bottom-0 button[data-svelte-h='svelte-1rfz1ec']");
	private By players = By.xpath("//main[@class='main'] //a //h3");
	private By playerName = By.cssSelector(".flex-col-reverse .details-list-item b");
	// //span[@class='name-width-helper svelte-1g0t9kr'] --> player name
	// private By priceNow = By.cssSelector("span[data-svelte-h='svelte-1s4sarz']");
	private By lowPriceLoc = By
			.xpath("(//div[@class='market-data--value-mini flex items-center svelte-u8a1u7'] /span)[1]");
	private By highPriceLoc = By
			.xpath("(//div[@class='market-data--value-mini flex items-center svelte-u8a1u7'] /span)[3]");
	private By lastPage = By.cssSelector("button.svelte-1cmlip7.rounded-full");

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
				// driver.findElement(clickCheckBox).click();
				String clickCheckBox = "(//input[@class='svelte-1jd0273'])[" + count + "]";
				// System.out.println("Found xpath--------------------");
				driver.findElement(By.xpath(clickCheckBox)).click();
				// System.out.println("Clicked xpath------------------");
				// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				// wait.until(ExpectedConditions.visibilityOf(driver.findElement(posToggle)));
				// Thread.sleep(5000);
				break;
			}
			// Thread.sleep(5000);

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
				// String selectPos = "//input[@value='" + position + "']";
				String selectPos = "input[value='" + position + "']";
				action.scrollToElement(driver.findElement(By.cssSelector(selectPos))).perform();
				Thread.sleep(2000);
				driver.findElement(By.cssSelector(selectPos)).click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".my-2 .items-center")));
				Thread.sleep(5000);
				break;
			}
		}
	}

	public void clickPlayers() throws InterruptedException {
		// String openInNew = Keys.chord(Keys.CONTROL, Keys.ENTER);
		By pagesEle = By.xpath("//button[contains(@class,'btn join-item  svelte-1cmlip7')]");
		int pages = driver.findElements(pagesEle).size();
		System.out.println("Pages of player: " + pages);
		Actions action = new Actions(driver);
		String nextPage = "";
		int currPage = 1;
		do {
			playersEle = driver.findElements(players);
			// Thread.sleep(5000);
			for (WebElement PlayerEle : playersEle) {
				Thread.sleep(500);
				action.moveToElement(PlayerEle).keyDown(Keys.CONTROL).click().build().perform();
				// playres.sendKeys(openInNew);
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
		// driver.findElement(RelativeLocator.with(lastPage).toRightOf(driver.findElement(By.xpath(nextPage)))).isEnabled()
		// (driver.findElement(lastPage).isEnabled());
	}

	public void playerDetails() throws InterruptedException {
		List<String> playerNames = new ArrayList<String>();
		List<String> playerLowPrices = new ArrayList<String>();
		List<String> playerLHighPrices = new ArrayList<String>();
		String player, lowPrice, highPrice;
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String parentId = itr.next();

		while (itr.hasNext()) {
			String playerpage = itr.next();
			driver.switchTo().window(playerpage);
			// driver.manage().window().fullscreen();
			Thread.sleep(2000);
			player = driver.findElement(playerName).getText();
			playerNames.add(player);
			try {
				// lowPrice =
				// driver.findElement(RelativeLocator.with(By.tagName("span")).near(priceNow)).getText();
				lowPrice = driver.findElement(lowPriceLoc).getText();
				playerLowPrices.add(lowPrice);
			} catch (Exception e) {
				playerLowPrices.add("0");
			}
			try {
				// highPrice =
				// driver.findElement(RelativeLocator.with(By.tagName("span")).below(priceNow)).getText();
				highPrice = driver.findElement(highPriceLoc).getText();
				playerLHighPrices.add(highPrice);
			} catch (Exception e) {
				playerLHighPrices.add("0");
			}
		}
		System.out.println("Players---> " + playerNames);
		System.out.println("Low Prices---> " + playerLowPrices);
		System.out.println("High Prices---> " + playerLHighPrices);
		driver.switchTo().window(parentId);
	}

}
