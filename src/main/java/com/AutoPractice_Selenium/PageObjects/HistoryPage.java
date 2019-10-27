/**
ORDER HISTORY page object at link (http://automationpractice.com/index.php?controller=history)
, represents the following elements:
	1- warning: in case of empty order history
	2- order: in case of non empty order history

**/
package com.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class HistoryPage{
	
	WebDriver driver;

	

	public HistoryPage(WebDriver driver){
		this.driver = driver;
	}


	//Checks if "You have not placed any orders." Warning exists or not
	public boolean checkWarning(){
		List<WebElement> warnings = driver.findElements(By.xpath("//p[text()='You have not placed any orders.']"));
		return !warnings.isEmpty();
	}

	public int checkOrdersNumber(){
		List<WebElement> orderList = driver.findElements(By.xpath("//div[@id='block-history']/table/tbody/tr"));
		return orderList.size();
	}

	public boolean checkOrderExists(String orderReference){
		WebElement temp = driver.findElement(By.xpath("//*[@id='order-list']/tbody/tr[1]/td[1]/a"));
		return orderReference.equals(temp.getText());
	}


}