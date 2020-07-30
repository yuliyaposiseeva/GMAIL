package com.gmail.test;

import com.gmail.pages.InboxPage;
import com.gmail.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Tests {

    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\samaeru\\Downloads\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS );
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void test(){
       int expectedMailCount = 4;
        LoginPage loginPage = new LoginPage(driver);
        InboxPage inboxPage = loginPage.open().login("yuliyatestprofil", "yuliyatest123");
        int actualMailsCount = inboxPage.mailsCount("Simbirsoft");
        inboxPage.sendMail("yuliyatestprofil@gmail.com", "Simbirsoft theme", actualMailsCount);
        Assert.assertEquals(expectedMailCount, actualMailsCount, "False");
    }

    @AfterClass
    public void shoutDown(){

        driver.quit();
    }
}
