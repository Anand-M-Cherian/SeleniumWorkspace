package org.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;

    @FindBy(css = "a[class='theme-btn register-btn']")
    WebElement loginPageButton;
    //By loginPageButton = By.cssSelector("a[class='theme-btn register-btn']");

    public WebElement getLoginPageButton() {
        return loginPageButton;
    }

}
