package com.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import java.util.List;


public class InboxPage {

    private WebDriver driver;


    @FindBy(name="q")
    private WebElement searchingInBoxInput;

    @FindBy(xpath = "//*[contains(@class,'T-I T-I-KE L3')]")
    private WebElement newMailButton;

    @FindBy(name="to")
    private WebElement mailInPut;

    @FindBy(name = "subjectbox")
    private WebElement subjectMailInput;

    @FindBy(css = "div[role='textbox']")
    private WebElement textMailInput;

    @FindBy(className = "aoO")
    private WebElement sendMailButton;

    public InboxPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int mailsCount(String theme){
        String query = "subject:(" + theme + ")";
        String locator = "//span/span[text()='" + theme + "']";
        searchingInBoxInput.sendKeys(query);
        searchingInBoxInput.sendKeys(Keys.ENTER);
        return driver.findElements(By.xpath(locator)).size();
    }

    public void sendMail(String address, String theme, String text, Integer count){
        newMailButton.click();
        mailInPut.sendKeys(address);
        subjectMailInput.sendKeys(theme);
        String result = text.replaceAll("count", count.toString());
        textMailInput.sendKeys(result);
        sendMailButton.click();
    }
}
