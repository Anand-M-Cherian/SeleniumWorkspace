package org.ObjectRepository;


import org.AbstractComponents.PageObjectParent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSummary extends PageObjectParent {

    private WebDriver driver;
    private WebDriverWait wait;

    public OrderSummary(WebDriver driver, WebDriverWait wait) {

        // driver and wait knowledge transfer: TC -> PageObject class -> PageObject parent class
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    private WebElement messageDisplayed;

    @FindBy(css = ".box .ng-star-inserted label")
    private WebElement orderNumber;

    // selenium always returns text displayed on screen while doing getText() and not from HTML DOM
    public String getMessageDisplayed() {
        return messageDisplayed.getText();
    }

    public void printOrderNumber() {
        System.out.println(orderNumber.getText());
    }

}
