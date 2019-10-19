package com.DELL.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MenuPage{
	
	WebDriver driver;
	WebElement womenLink;
	WebElement blousesLink;
	

	public MenuPage(WebDriver driver){
		this.driver = driver;
		this.womenLink = driver.findElement(By.xpath("//a[@title='Women']"));
		// this.blousesLink = driver.findElement(By.linkText("Blouses"));
	}

	public void hoverOverWomenMenuButton(){
		Actions action = new Actions(driver);
		action.moveToElement(this.womenLink).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Blouses")));
		this.blousesLink = driver.findElement(By.linkText("Blouses"));
	}

	public void clickBlousesButton(){
		this.blousesLink.click();
	}

	public boolean isBlousesButtonVisible(){
		return blousesLink.isDisplayed();
	}

}

