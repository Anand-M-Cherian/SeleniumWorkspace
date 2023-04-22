package org.AbstractComponents;

import org.ObjectRepository.MyOrders;
import org.ObjectRepository.ShoppingCart;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectParent {

    // parent class for all page object classes

    private WebDriver driver;
    private WebDriverWait wait;

    // All header web elements are common to all pages, hence put them in parent class
    @FindBy(css = "[routerlink*='/dashboard/cart']")
    private WebElement cartBtn;

    @FindBy(css = "[routerlink*='/dashboard/myorders']")
    private WebElement ordersBtn;

    @FindBy(css = "li:nth-child(5) .btn.btn-custom")
    private WebElement signOutBtn;



    public PageObjectParent(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // common wait method for all page object classes to inherit
    public void waitForElementToAppear(WebElement webElement) {
        // Sometimes wait by driver.findElement is faster to execute than the one using By locator
        // Hence using visibilityOf rather than visibilityOfElementLocated
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToDisappear(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void scrollToElement(WebElement webElement) throws InterruptedException {
        // to avoid click interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(1000);
    }

    public ShoppingCart goToCart() {
        cartBtn.click();
        return new ShoppingCart(driver, wait);
    }

    public void signOut() throws InterruptedException {
        scrollToElement(signOutBtn);
        signOutBtn.click();
    }

    public MyOrders goToMyOrders() {
        ordersBtn.click();
        MyOrders myOrders = new MyOrders(driver, wait);
        return myOrders;
    }

}
