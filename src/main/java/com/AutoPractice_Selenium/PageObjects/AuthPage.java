/**
Page object for authentication page after clicking sign in from the upper most 
black button (in case of not signed in).
Page contains either "CREATE AN ACCOUNT" or "ALREADY REGISTERED?"
**/
package com.AutoPractice_Selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AuthPage{
	
	WebDriver driver;
	WebElement emailCreateTextBox;
	WebElement emailTextBox;
	WebElement emailPassword;
	WebElement emailCreateButton;
	WebElement emailButton;


	public AuthPage(WebDriver driver){
		this.driver = driver;
		this.emailCreateTextBox = driver.findElement(By.id("email_create"));
		this.emailTextBox = driver.findElement(By.id("email"));
		this.emailPassword = driver.findElement(By.id("passwd"));
		this.emailCreateButton = driver.findElement(By.id("SubmitCreate"));
		this.emailButton = driver.findElement(By.id("SubmitLogin"));
	}

	public void emailCreateAndProceed(String email){
		emailCreateTextBox.sendKeys(email);
		emailCreateButton.click();
	}

	public void login(String email, String pass){
		emailTextBox.sendKeys(email);
		emailPassword.sendKeys(pass);
		emailButton.click();
	}

	public String errorMessage(){
		List<WebElement> errors = driver.findElements(By.xpath("//*[@id='center_column']/div[1]/ol/li"));
		if(errors.isEmpty())
			return "";
		else
			return errors.get(0).getText();
	} 

}