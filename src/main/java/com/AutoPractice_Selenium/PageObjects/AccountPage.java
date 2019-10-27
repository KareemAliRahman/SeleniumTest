/**
Account page object having link to history
**/
package com.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AccountPage{
	
	WebDriver driver;
	WebElement orderHistory;
	WebElement pageHeading;
	

	public AccountPage(WebDriver driver){
		this.driver = driver;
		this.orderHistory = driver.findElement(By.xpath("//a[@title='Orders']"));
		this.pageHeading = driver.findElement(By.className("page-heading"));
	}

	public void clickOrderHistory(){
		orderHistory.click();
	}

	public String getPageHeading(){
		return this.pageHeading.getText();
	}

}