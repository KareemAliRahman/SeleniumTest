package com.DELL.AutoPractice_Selenium;

import java.util.*;
import java.nio.charset.Charset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class CheckoutFormsPage {
  WebDriver driver;
  // //product elements
  // ArrayList<WebElement> productElements;
  // ArrayList<Product> products;
  WebElement productNameElement;
  WebElement productQuantityElement;
  WebElement productSizeColorElement;
  // WebElement product;
  WebElement currentStep;
  WebElement checkoutButton;
  
  // public CheckoutFormsPage(WebDriver driver){
  //   this.driver = driver;
  //   this.checkoutButton = driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]"));

  //   products = new ArrayList<Product>();
  //   this.productElements = (ArrayList<WebElement>)driver.findElements(By.xpath("//tbody//*[@class='cart_description']"));
  //   for( WebElement pe : productElements){
  //     String name = pe.findElement(By.xpath(".//p/a")).getText();
  //     int quantity = Integer.parseInt(
  //       pe.findElement(By.xpath(".//td[starts-with(class,'cart_quantity')]/input[@type='text']")).getAttribute("value"));
  //     // String snc = pe.findElement(By.xpath("//*[starts-with(@id,'product_')]/td[2]/small[2]/a")).getText();
  //     String snc = pe.findElement(By.xpath(".//small/a")).getText();

  //     boolean color = snc.contains("White");
  //     char size = 'S';
  //     if(snc.contains(": M")){
  //       size = 'M';
  //     }else{
  //       size = 'L';
  //     }
  //     Product p = new Product(name, quantity, color, size);
  //     products.add(p);
  //   }
  //   this.currentStep = driver.findElement(By.xpath("//li[starts-with(@class,'step_current')]"));
    
  // }

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

  // //Can check multiple Orders
  // public boolean checkProductsExist(ArrayList<Product> products){
  //   boolean result = true;
  //   for(Product p : products){
  //     boolean temp = false;
  //     for(Product cp : this.products){
  //       if(p.equals(cp))
  //         temp = true;
  //     }
  //     result &= temp;
  //   }
  //   return result;

  // }

  public void proceedCheckout(){
    checkoutButton.click();
    
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
    // System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    // System.out.println(currentStep.getAttribute("class"));
    // System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    return currentStep.getAttribute("class");
  }

  public void confirmOrder(){
    WebElement confirmButton = driver.findElement(By.xpath("//*[@id='cart_navigation']/button"));
    confirmButton.click();
  }

  //the message should be "Your order on My Store is complete."
  public String getEndPaymentMessage(){
    return driver.findElement(By.xpath("//*[@id='center_column']/div/p/strong")).getText();
  }

}
