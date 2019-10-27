/**
Home Page Page Object, Which represents the upper most black menu having:
  1- sign in button (in case not signed in)
  2- sign out button (in case signed in)
  2- account button (in case signed in)
**/
package com.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver driver;
	WebElement signInButton;
  WebElement accountButton;
  WebElement logOutButton;
  	
  	public HomePage(WebDriver driver){
  		this.driver = driver;
      this.signInButton = driver.findElement(By.className("login"));
  	}
  	
  	public void clickSignInButton(){
  		signInButton.click();
  	}

    public void clickAccountButton(){
      this.accountButton = driver.findElement(By.className("account"));
      accountButton.click();
    }

    public void logOut(){
      this.logOutButton = driver.findElement(By.className("logout"));
      logOutButton.click();
    }

    public String accountButton(){
      this.accountButton = driver.findElement(By.className("account"));
      return this.accountButton.findElement(By.xpath("./span")).getText();
    }
  
}