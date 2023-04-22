package org.ObjectRepository;


import org.AbstractComponents.PageObjectParent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyOrders extends PageObjectParent {

    private WebDriver driver;
    private WebDriverWait wait;

    public MyOrders(WebDriver driver, WebDriverWait wait) {

        // driver and wait knowledge transfer: TC -> PageObject class -> PageObject parent class
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody //tr")
    private List<WebElement> ordersTableRows;

    @FindBy(xpath = "//td[2]")
    private List<WebElement> productsOrdered;

    private By deleteBtn = By.xpath("//td[6] //button");

    public List<WebElement> getOrdersTableRows() {
        return ordersTableRows;
    }

    public List<WebElement> getProductsOrdered() {return productsOrdered; }

    public boolean isProductOrdered(String productName) {
        // anyMatch() gives true if any element of stream matches given condition
        return getProductsOrdered().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public void deleteOrders() {
        waitForElementToAppear(driver.findElement(deleteBtn));
        for (WebElement order : getOrdersTableRows()) {
            order.findElement(deleteBtn).click();
        }
    }

}
