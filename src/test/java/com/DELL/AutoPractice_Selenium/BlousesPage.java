package com.DELL.AutoPractice_Selenium;

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
  		this.blouseLink = driver.findElement(By.xpath("//a[@title='Blouse']"));
      this.categoryName = driver.findElement(By.className("cat-name"));
  	}
  	
  	public void clickOnBlouseLink(){
		  this.blouseLink.click();
  	}

    public String getCategoryName(){
      return this.categoryName.getText();
    }

    // public WebElement getBlouseLink(){
    //   return this.blouseLink;
    // }

    public void scrollToBlouseLink(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", this.blouseLink);
        // Actions actions = new Actions(driver);
        // actions.moveToElement(blouseLink);
        // actions.build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Blouse']")));
        // this.blouseLink.click();

        // Actions actions = new Actions(driver);
        // // actionChains.context_click(link)
        // actions.send_keys(Keys.ARROW_DOWN).send_keys(Keys.ARROW_DOWN).send_keys(Keys.ARROW_DOWN)
        // .send_keys(Keys.ARROW_DOWN).perform();
        // actionChains.send_keys(Keys.ARROW_DOWN);
        // actionChains.send_keys(Keys.ARROW_DOWN);
        // actionChains.send_keys(Keys.ARROW_DOWN);
        // actionChains.send_keys(Keys.ARROW_DOWN);
        // actionChains.send_keys(Keys.RETURN);
        // actionChains.perform();
    }
  
}