package com.renderz.pageObj;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AAA {
	public static void main(String[] args) {
		List<String> li = new ArrayList<String>();
		li.add("Abhi");
		li.add("jit");
		System.out.println(li.size());
	}

}

/*
 * By playersBtn = By.xpath("//button[@class='cursor-pointer group']"); By
 * btnOptions = By.cssSelector("ul.menu a");
 * 
 * driver.findElement(playersBtn).click(); List<WebElement> options =
 * driver.findElements(btnOptions);
 * 
 * System.out.println(options); String cll = "FC 24"; for(WebElement option:
 * options) { System.out.print(option.getText());
 * if(cll.equalsIgnoreCase(option.getText())) option.click(); }
 */

/*
 * WebDriver driver = new ChromeDriver();
 * driver.get("https://renderz.app/24/players");
 * driver.manage().window().fullscreen(); List<WebElement> filters =
 * driver.findElements(By.cssSelector("div.mt-4 .collapse-title")); List<String>
 * filterNames = new ArrayList<String>(); for (WebElement filter : filters) {
 * filterNames.add(filter.getText()); } System.out.println(filterNames); String
 * tabName = "Rating"; // int eleNo = 1; for (WebElement filter : filters) {
 * System.out.println("in loop............."); if
 * (tabName.equalsIgnoreCase(filter.getText())) { // Thread.sleep(2000);
 * driver.findElement(By.
 * cssSelector("div.mt-4 input[type='checkbox']:nth-child(1)")).click();
 * System.out.println("Clicked........."); break; } // eleNo++; }
 * Thread.sleep(3000); // Actions action = new Actions(driver); //
 * action.moveToElement(driver.findElement(By.cssSelector( //
 * "#rangeSlider span[data-handle='0'] span.rangeNub"))). //
 * sendKeys(Keys.ARROW_RIGHT).perform(); //driver.close();
 * driver.findElement(By.cssSelector(
 * "#rangeSlider span[data-handle='0'] span.rangeNub")).
 * sendKeys(Keys.ARROW_RIGHT);
 */