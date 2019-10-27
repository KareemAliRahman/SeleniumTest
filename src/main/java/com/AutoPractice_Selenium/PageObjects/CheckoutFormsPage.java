/**
Page object of checkout form (muliple pages) until choosing payment options (in this case bankwire)
and clicking CONFIRM ORDER button
**/
package com.AutoPractice_Selenium;

import java.util.*;
import java.nio.charset.Charset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;



public class CheckoutFormsPage {
  WebDriver driver;
  WebElement productNameElement;
  WebElement productQuantityElement;
  WebElement productSizeColorElement;
  WebElement currentStep;
  WebElement checkoutButton;
  String orderReference;
  
  public CheckoutFormsPage(WebDriver driver){
    this.driver = driver;
    this.checkoutButton = driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]"));
    this.productNameElement = driver.findElement(By.xpath("//td[@class='cart_description']/p/a"));
    this.productQuantityElement = driver.findElement(By.xpath("//td[@class='cart_quantity text-center']/input[2]"));
    this.productSizeColorElement = driver.findElement(By.xpath("//td[@class='cart_description']/small[2]/a"));
    this.currentStep = driver.findElement(By.xpath("//li[starts-with(@class,'step_current')]"));
  }

  public boolean CheckProductExists(Product prod){
    if(prod.name.equals(productNameElement.getText()) && prod.quantity == Integer.parseInt(productQuantityElement.getAttribute("value")))
      return true;
    return false;
  }

  public void scrollToProceedLink(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,700)");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[starts-with(@class,'step_current')]")));
  }

  public void proceedCheckout(){
    checkoutButton.click();
    if(!checkCurrentStep().contains("last")){
      checkoutButton = driver.findElement(By.xpath("//button[starts-with(@name,'process')]"));  
    }
    
    
  }

  public void checkShippingbox(){
    WebElement shippingCheckBox = driver.findElement(By.id("cgv"));
    shippingCheckBox.click();
  }

  public void chooseBankWire(){
    WebElement bankWire = driver.findElement(By.xpath("//div[@id='HOOK_PAYMENT']//a[@class='bankwire']"));
    bankWire.click();
  }

  //return current step: first, second, third, four, last
  public String checkCurrentStep(){
    this.currentStep = driver.findElement(By.xpath("//li[starts-with(@class,'step_current')]"));
    return currentStep.getAttribute("class");
  }

  //confim order and return the order reference id
  public String confirmOrder(){
    WebElement confirmButton = driver.findElement(By.xpath("//*[@id='cart_navigation']/button"));
    confirmButton.click();
    WebElement temp = driver.findElement(By.xpath("//*[@id='center_column']/div"));
    String tempString = temp.getText();
    this.orderReference = tempString.split("reference ")[1].split(" ")[0];
    return this.orderReference;
  }

  //the message should be "Your order on My Store is complete."
  public String getEndPaymentMessage(){
    return driver.findElement(By.xpath("//*[@id='center_column']/div/p/strong")).getText();
  }

}
