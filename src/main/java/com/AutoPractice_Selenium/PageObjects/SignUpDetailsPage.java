/**
Sign up form page object, representing elements of different new account details entered by new user
**/
package com.AutoPractice_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


public class SignUpDetailsPage{
	
	WebDriver driver;
	WebElement firstnameTextBox;
	WebElement lastnameTextBox;
	WebElement emailTextBox;
	WebElement passTextBox;
	WebElement addrFirstNameTextBox;
	WebElement addrLastNameTextBox;
	WebElement addrTextBox;
	WebElement cityTextBox;
	Select stateTextBox;
	WebElement postalTextBox;
	Select countryTextBox;
	WebElement mobileTextBox;
	WebElement submitButton;
	WebElement header1;



	public SignUpDetailsPage(WebDriver driver){
		this.driver = driver;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_firstname")));


		this.firstnameTextBox = driver.findElement(By.id("customer_firstname"));
		this.lastnameTextBox = driver.findElement(By.id("customer_lastname"));
		this.emailTextBox = driver.findElement(By.id("email"));
		this.passTextBox = driver.findElement(By.id("passwd"));
		this.addrFirstNameTextBox = driver.findElement(By.id("firstname"));
		this.addrLastNameTextBox = driver.findElement(By.id("lastname"));
		this.addrTextBox = driver.findElement(By.id("address1"));
		this.cityTextBox = driver.findElement(By.id("city"));
		this.stateTextBox = new Select(driver.findElement(By.id("id_state")));
		this.postalTextBox = driver.findElement(By.id("postcode"));
		this.countryTextBox = new Select(driver.findElement(By.id("id_country")));
		this.mobileTextBox = driver.findElement(By.id("phone_mobile"));
		this.submitButton = driver.findElement(By.id("submitAccount"));
		this.header1 = driver.findElement(By.className("page-heading"));
	}

	public void fillSignUpDetails(String fname, String lname, String email, String pass,
			String addr, String city, String state, String postcode, String Country, String mobile){
		firstnameTextBox.sendKeys(fname);
		lastnameTextBox.sendKeys(lname);
		emailTextBox.clear();
		emailTextBox.sendKeys(email);
		passTextBox.sendKeys(pass);
		addrFirstNameTextBox.clear();
		addrFirstNameTextBox.sendKeys(fname);
		addrLastNameTextBox.clear();
		addrLastNameTextBox.sendKeys(lname);
		addrTextBox.sendKeys(addr);
		cityTextBox.sendKeys(city);
		stateTextBox.selectByVisibleText(state);
		postalTextBox.sendKeys(postcode);
		countryTextBox.selectByVisibleText(Country);
		mobileTextBox.sendKeys(mobile);

		submitButton.click();
	}

	//checks h1 header is "CREATE AN ACCOUNT"
	public String text_H1(){
		return this.header1.getText();
	}

	public String errorText(){
		return driver.findElements(By.xpath("//*[@id='center_column']/div/ol/li")).get(0).getText();
	}
	

}