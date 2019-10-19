package com.DELL.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage {
	WebDriver driver;
	WebElement signInButton;
  WebElement accountButton;
  WebElement logOutButton;
  	
  	public HomePage(WebDriver driver){
  		this.driver = driver;
      // WebDriverWait wait = new WebDriverWait(driver, 10);
      // wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
  		this.signInButton = driver.findElement(By.className("login"));
      // this.logOutButton = driver.findElement(By.className("logout"));
  	}
  	
  	public void clickSignInButton(){
  		signInButton.click();
  	}

    public void clickAccountButton(){
      if(this.accountButton==null)
        this.accountButton = driver.findElement(By.className("account"));
      accountButton.click();
    }

    public void logOut(){
      this.logOutButton = driver.findElement(By.className("logout"));
      logOutButton.click();
    }

    public String accountButton(){
      if(this.accountButton==null)
        this.accountButton = driver.findElement(By.className("account"));
      return this.accountButton.findElement(By.xpath("./span")).getText();
    }
  
}