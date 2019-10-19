package com.DELL.AutoPractice_Selenium;

import java.util.*; 
import java.nio.charset.Charset;
import java.security.SecureRandom;

import org.testng.Assert;
import org.testng.annotations.*;

// import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.support.ui.ExpectedConditions;




public class AppTest {

    WebDriver driver;
    String email;
    String fname;
    String lname;


    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @BeforeClass
    public void setup(){
        
        String pathToFireFoxDriver = "lib/geckodriver";
        System.setProperty("webdriver.gecko.driver", pathToFireFoxDriver);
        driver = new FirefoxDriver();

        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://automationpractice.com");

        email = generate(10);
        email += "@testtest.com";

        fname = "kareem";
        lname = "ali";
    }

    // @Test
    public void signUpWithInvalidDetails(){
        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.emailCreateAndProceed(email);
        

        SignUpDetailsPage signUpPage = new SignUpDetailsPage(driver);
        Assert.assertEquals(signUpPage.text_H1() ,"CREATE AN ACCOUNT");
        //Wrong postal code with 6 digits instead of 5
        signUpPage.fillSignUpDetails("kareem", "Ali", email, "password", 
            "long Street, Research Triangle Park", "Park", "Virginia", "020200", "United States", "01010010101");
        Assert.assertEquals(signUpPage.errorText(), "The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
    }

    // @Test
    public void signUpWithvalidDetails(){
        HomePage hm = new HomePage(driver);

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.emailCreateAndProceed(email);

        
        SignUpDetailsPage signUpPage = new SignUpDetailsPage(driver);
        Assert.assertEquals(signUpPage.text_H1() ,"CREATE AN ACCOUNT");
        signUpPage.fillSignUpDetails(fname, lname, email, "password", 
            "long Street, Research Triangle Park", "Park", "Virginia", "02020", "United States", "01010010101");

        Assert.assertEquals(hm.accountButton(), fname + " " + lname);
        hm.logOut();

    }

    // @Test
    public void signInWithInvalidPassword(){
        HomePage hm = new HomePage(driver);
        email = "kareem@ali.com";

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.login(email, "wrongPassword"); //using wrong password
        
        Assert.assertEquals(ap.errorMessage(),"Authentication failed.");
        

    }

    // @Test
    public void signInAndShowEmptyHistory(){
        HomePage hm = new HomePage(driver);
        email = "kareem@ali.com";

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.login(email, "password");
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

    @Test
    public void addtoCartAndConfirmOrderAndCheckHistory(){
        HomePage hm = new HomePage(driver);
        email = "kareem@ali.com";

        hm.clickSignInButton();
        Assert.assertEquals(driver.getTitle(),"Login - My Store");

        AuthPage ap = new AuthPage(driver);
        ap.login(email, "password");
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
        
        //scrolling down to see blouse img and link
        // try{
        //     bp.scrollToBlouseLink();
        //     bp.clickOnBlouseLink();
    
        // }catch(Error e){
        //     System.out.println("Couldn't scroll and click on the product");
        //     driver.get("http://automationpractice.com/img/p/7/7-home_default.jpg");
        // }
        driver.get("http://automationpractice.com/index.php?id_product=2&controller=product");


        

        ProductPage pp = new ProductPage(driver);
        Assert.assertEquals(pp.getProductName(), "Blouse");
        //select 2 blouses of size 'M' and White(True)
        Product prod = new Product("", 2, true, 'M');
        pp.addToCart(prod);
        Assert.assertTrue(pp.getMessage().contains("Product successfully added to your shopping cart"));
        pp.proceedCheckout();

        
        CheckoutFormsPage cfp = new CheckoutFormsPage(driver);
        Assert.assertEquals(cfp.checkCurrentStep(), "step_current  first");
        Assert.assertTrue(cfp.CheckProductExists(prod));

        cfp.proceedCheckout();
        Assert.assertEquals(cfp.checkCurrentStep(), "step_current  third");

        cfp.proceedCheckout();
        Assert.assertEquals(cfp.checkCurrentStep(), "step_current  four");
        cfp.checkShippingbox();

        cfp.proceedCheckout();
        Assert.assertEquals(cfp.checkCurrentStep(), "step_current  last");
        cfp.chooseBankWire();
        cfp.confirmOrder();
        Assert.assertTrue(cfp.getEndPaymentMessage().equals("Your order on My Store is complete."));
        


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





}
