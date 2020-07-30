package com.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private WebDriver driver;

    @FindBy(id="identifierId")
    private WebElement loginInput;


    @FindBy(xpath = "//*[@id='identifierNext']/div/button/div[2]")
    private  WebElement nextLoginButton;


    @FindBy(id = "passwordNext")
    private WebElement nextPasswordButton;


    @FindBy(name = "password")
    private WebElement passwordInput;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage open(){
        driver.get("https://mail.google.com/mail");
        return this;
    }

    public InboxPage login(String login, String password) {
        loginInput.sendKeys(login);
        nextLoginButton.click();
        passwordInput.sendKeys(password);
        nextPasswordButton.click();
        return new InboxPage(driver);
    }

}
