package org.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    // Object repository of login page with all the required objects

    // to transfer the knowledge of browser and url from the LoginTest class
    // constructor automatically executed when object instantiated
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    By username = By.xpath("//input[@id='login1']");
    By password = By.xpath("//input[@id='password']");
    By submitButton = By.className("signinbtn");
    By homePage = By.linkText("rediff.com");

    public WebElement username () {
        return driver.findElement(username);
    }

    public WebElement password () {
        return driver.findElement(password);
    }

    public WebElement submitButton () {
        return driver.findElement(submitButton);
    }

    public WebElement homePage () {
        return driver.findElement(homePage);
    }



}
