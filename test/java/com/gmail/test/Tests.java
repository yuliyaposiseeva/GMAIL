package com.gmail.test;

import com.gmail.helpers.TestConfig;
import com.gmail.pages.InboxPage;
import com.gmail.pages.LoginPage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Tests {

    private WebDriver driver;

    private TestConfig config;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\samaeru\\Downloads\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS );
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        this.config= ConfigFactory.create(TestConfig.class);
    }

    @Test
    public void test(){
        LoginPage loginPage = new LoginPage(driver);
        InboxPage inboxPage = loginPage.open().login(config.login(), config.password());
        Integer actualMailsCount = inboxPage.mailsCount(config.searchTheme());
        inboxPage.sendMail(config.email(), config.sendingTheme(), config.defaultText(), actualMailsCount);
        Assert.assertEquals(config.expectedMailCount(), actualMailsCount, "False");
    }

    @AfterClass
    public void shoutDown(){

        driver.quit();
    }
}
