package org.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    // include PageFactory code in constructor

    public HomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    WebDriver driver;

    @FindBy(id = "srchword")
    WebElement searchBox;

    @FindBy(className = "newsrchbtn")
    WebElement searchButton;

    // FindBy takes care of the whole 'driver.findElement.' syntax

    public WebElement searchBox () {
        return searchBox;
    }

    public WebElement searchButton () {
        return searchButton;
    }



}
