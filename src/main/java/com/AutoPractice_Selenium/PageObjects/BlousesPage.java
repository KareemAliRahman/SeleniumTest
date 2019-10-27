/**
page object for page after choosing a product type from white menu (blouses form WOMEN menu).
**/
package com.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class BlousesPage {
	WebDriver driver;
	WebElement blouseLink;
  WebElement categoryName;
  	
  	public BlousesPage(WebDriver driver){
  		this.driver = driver;
  		this.blouseLink = driver.findElement(By.xpath("//h5/a[@title='Blouse']"));
      this.categoryName = driver.findElement(By.className("cat-name"));
  	}
  	
  	public void clickOnBlouseLink(){
		  this.blouseLink.click();
  	}

    public String getCategoryName(){
      return this.categoryName.getText();
    }

    public void scrollToBlouseLink(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,500)");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5/a[@title='Blouse']")));

    }
  
}