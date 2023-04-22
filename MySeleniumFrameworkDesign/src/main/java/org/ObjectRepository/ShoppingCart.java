package org.ObjectRepository;


import org.AbstractComponents.PageObjectParent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShoppingCart extends PageObjectParent {

    private WebDriver driver;
    private WebDriverWait wait;

    public ShoppingCart(WebDriver driver, WebDriverWait wait) {

        // driver and wait knowledge transfer: TC -> PageObject class -> PageObject parent class
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    private List<WebElement> productsAdded;

    @FindBy(css = ".subtotal .btn-primary")
    private WebElement checkOutBtn;

    public List<WebElement> getProductsAdded() {
        return productsAdded;
    }

    public boolean isProductAdded(String productName) {
        // anyMatch() gives true if any element of stream matches given condition
        return getProductsAdded().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public PlaceOrder checkOut() {
        checkOutBtn.click();
        return new PlaceOrder(driver, wait);
    }


}
