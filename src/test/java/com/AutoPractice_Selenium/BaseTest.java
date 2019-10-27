package com.AutoPractice_Selenium;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import java.security.SecureRandom;


public class BaseTest {
	WebDriver driver;
    String email;
    String password;
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
        
        email = generate(10);
        email += "@testtest.com";
        password = generate(10);

        fname = generate(10);
        lname = generate(10);
    }

    @BeforeMethod
    public void closeDriver(){
        // String pathToFireFoxDriver = "lib/chromedriver";
        // System.setProperty("webdriver.chrome.driver", pathToFireFoxDriver);
        // driver = new ChromeDriver();

        String pathToFireFoxDriver = "lib/geckodriver";
        System.setProperty("webdriver.gecko.driver", pathToFireFoxDriver);
        driver = new FirefoxDriver();


        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://automationpractice.com");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}