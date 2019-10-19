package com.DELL.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class HistoryPage{
	
	WebDriver driver;
	// WebElement orderHistory;

	

	public HistoryPage(WebDriver driver){
		this.driver = driver;
		// this.orderHistory = driver.findElement(By.xpath("//a[@title='Orders']"));
	}

	// public void clickOrderHistory(){
	// 	orderHistory.click();
	// }


	//Checks if "You have not placed any orders." Warning exists or not
	public boolean checkWarning(){
		List<WebElement> warnings = driver.findElements(By.xpath("//p[text()='You have not placed any orders.']"));
		return !warnings.isEmpty();
	}

	public int checkOrdersNumber(){
		List<WebElement> orderList = driver.findElements(By.xpath("//div[@id='block-history']/table/tbody/tr"));
		return orderList.size();
	}


}