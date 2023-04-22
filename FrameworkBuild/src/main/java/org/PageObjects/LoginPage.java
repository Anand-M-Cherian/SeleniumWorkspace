package org.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;

    @FindBy(id = "email")
    private WebElement emailTextBox;

    private By passwordTextBox = By.id("password");

    @FindBy(css = "input[class*='btn-primary button']")
    private WebElement submitButton;

    public WebElement getPasswordTextBox() {
        return driver.findElement(passwordTextBox);
    }

    public WebElement getEmailTextBox() {
        return emailTextBox;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }
}
