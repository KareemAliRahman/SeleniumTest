package com.AutoPractice_Selenium;

import java.util.*; 


import org.testng.Assert;
import org.testng.annotations.*;

public class AutoPractice_Test extends BaseTest{

	@Test(priority=2)
    public void signUpWithValidDetails(){
        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.emailCreateAndProceed(email);

        
        SignUpDetailsPage signUpPage = new SignUpDetailsPage(driver);
        Assert.assertEquals(signUpPage.text_H1() ,"CREATE AN ACCOUNT");
        signUpPage.fillSignUpDetails(fname, lname, email, password, 
            "long Street, Research Triangle Park", "Park", "Virginia", "02020", "United States", "01010010101");

        Assert.assertEquals(hm.accountButton(), fname + " " + lname);
        hm.logOut();
    }

	@Test(priority=1)
    public void signUpWithInvalidDetails(){
        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.emailCreateAndProceed(email);
        

        SignUpDetailsPage signUpPage = new SignUpDetailsPage(driver);
        Assert.assertEquals(signUpPage.text_H1() ,"CREATE AN ACCOUNT");
        //Wrong postal code with 6 digits instead of 5
        signUpPage.fillSignUpDetails(fname, lname , email, password, 
            "long Street, Research Triangle Park", "Park", "Virginia", "020200", "United States", "01010010101");
        Assert.assertEquals(signUpPage.errorText(), "The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
    }

    @Test(priority=3, dependsOnMethods={"signUpWithValidDetails"})
    public void signInWithInvalidPassword(){
        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.login(email, "wrongPassword"); //using wrong password
        
        Assert.assertEquals(ap.errorMessage(),"Authentication failed.");
    }

    @Test(priority=4, dependsOnMethods={"signUpWithValidDetails"})
    public void signInAndShowEmptyHistory(){
        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.login(email, password);
        Assert.assertEquals(hm.accountButton(), fname + " " + lname);

        hm.clickAccountButton();

        AccountPage accP = new AccountPage(driver);
        Assert.assertEquals(accP.getPageHeading() ,"MY ACCOUNT");
        accP.clickOrderHistory();

        
        HistoryPage hp = new HistoryPage(driver);
        Assert.assertEquals(driver.getTitle(), "Order history - My Store");       
        Assert.assertTrue(hp.checkWarning()); //a warning should appear on the Order History page
        Assert.assertEquals(hp.checkOrdersNumber(), 0);

        hm.logOut();
    }

    @Test(priority=5, dependsOnMethods={"signUpWithValidDetails"})
    public void addToCartAndConfirmOrderAndCheckHistory(){


        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.login(email, password);
        Assert.assertEquals(hm.accountButton(), fname + " " + lname);

        
        MenuPage mp = new MenuPage(driver);
        try{
            mp.hoverOverWomenMenuButton();
            mp.clickBlousesButton();
        }catch(Error e){
            System.out.println("Physical Cursor should be out of the test browser");
            //if physical cursor inside browser the test will fail. catching error and continue
            driver.get("http://automationpractice.com/index.php?id_category=7&controller=category");
        }



        BlousesPage bp = new BlousesPage(driver);
        Assert.assertEquals(bp.getCategoryName(),"BLOUSES ");
        
        // scrolling down to see blouse img and link
        try{
            bp.scrollToBlouseLink();
            bp.clickOnBlouseLink();
    
        }catch(Error e){
            System.out.println("Couldn't scroll and click on the product");
            driver.get("http://automationpractice.com/index.php?id_product=2&controller=product");
        }
        

        

        ProductPage pp = new ProductPage(driver);
        Assert.assertEquals(pp.getProductName(), "Blouse");
        //select 2 blouses of size 'M' and White(True)
        Product prod = new Product("", 2, true, 'M');
        pp.addToCart(prod);
        Assert.assertTrue(pp.getMessage().contains("Product successfully added to your shopping cart"));
        pp.proceedCheckout();

        
        CheckoutFormsPage cfp = new CheckoutFormsPage(driver);
        Assert.assertTrue(cfp.checkCurrentStep().contains("first"));
        Assert.assertTrue(cfp.CheckProductExists(prod));

        cfp.scrollToProceedLink();
        cfp.proceedCheckout();
        Assert.assertTrue(cfp.checkCurrentStep().contains("third"));

        cfp.scrollToProceedLink();
        cfp.proceedCheckout();
        Assert.assertTrue(cfp.checkCurrentStep().contains("four"));

        cfp.scrollToProceedLink();
        cfp.checkShippingbox();
        cfp.proceedCheckout();
        Assert.assertTrue(cfp.checkCurrentStep().contains("last"));

        cfp.scrollToProceedLink();
        cfp.chooseBankWire();
        String orderReference = cfp.confirmOrder();
        Assert.assertTrue(cfp.getEndPaymentMessage().equals("Your order on My Store is complete."));
        


        hm.clickAccountButton();

        AccountPage accP = new AccountPage(driver);
        Assert.assertEquals(accP.getPageHeading() ,"MY ACCOUNT");
        accP.clickOrderHistory();

        
        HistoryPage hp = new HistoryPage(driver);
        Assert.assertEquals(driver.getTitle(), "Order history - My Store");       
        Assert.assertTrue(hp.checkOrderExists(orderReference));

        hm.logOut();
    }
}