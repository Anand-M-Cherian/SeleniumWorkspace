package org.ObjectRepository;


import org.AbstractComponents.PageObjectParent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductCatalogue extends PageObjectParent {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductCatalogue(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // list of product card web elements displayed
    @FindBy(css = ".mb-3")
    private List<WebElement> productCard;

    // green product added message
    @FindBy(css = "#toast-container")
    private WebElement productAddedMsg;

    // loading animation after adding product
    @FindBy(css = ".ngx-spinner-overlay")
    private WebElement loadingProductAdd;

    // We cannot use @FindBy (PageFactory) when we are limiting scope to within a webElement.findElement()
    // Hence using By locators for the following:

    // By Locator of product name within each product card
    private By productNameByLocator = By.cssSelector("b");


    // By Locator of add to cart button within each product card
    private By addToCartBtn = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProducts() {
        // check whether first product card has loaded, if so then full page has loaded and return the
        // product card list
        waitForElementToAppear(productCard.get(0));
        return productCard;
    }

    public WebElement getProductByName(String productName) {
        // get me the first matching product or else null
        return getProducts().stream().filter(product -> {
            return product.findElement(productNameByLocator).getText().equalsIgnoreCase(productName);
        }).findFirst().orElse(null);
    }

    // Action to add a desired product to cart. Uses all the previous methods and parent class wait method
    public void addProduct(String productName) {
        // css last of type selects the last css matching the description
        getProductByName(productName).findElement(addToCartBtn).click();
        waitForElementToAppear(productAddedMsg);
        // waiting for spinner to disappear can take more time as even though in the front end, the spinner
        // is gone, the website is still calling for it in back end to ensure all web services are loaded.
        // hence there may be a hidden spinner and only when that also disappears, the code will proceed
        waitForElementToDisappear(loadingProductAdd);
    }



}
