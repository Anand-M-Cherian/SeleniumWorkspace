package org.ObjectRepository;


import org.AbstractComponents.PageObjectParent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PlaceOrder extends PageObjectParent {

    private WebDriver driver;
    private WebDriverWait wait;

    public PlaceOrder(WebDriver driver, WebDriverWait wait) {

        // driver and wait knowledge transfer: TC -> PageObject class -> PageObject parent class
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".user__address .txt")
    private WebElement selectCountryTextBox;

    // list of countries displayed in auto suggestive country dropdown
    @FindBy(css = ".list-group .list-group-item")
    private List<WebElement> suggestedCountries;

    @FindBy(css = ".action__submit")
    private WebElement placeOrderBtn;

    public void inputCountryName(String countryName) {
        selectCountryTextBox.sendKeys(countryName);
    }

    public void selectCountry(String countryName) {
        inputCountryName(countryName);
        // check if first country has displayed in list, if so full auto suggested list has displayed
        waitForElementToAppear(suggestedCountries.get(0));
        for (WebElement country: suggestedCountries) {
            if (country.getText().equalsIgnoreCase(countryName)) {
                country.click();
                break;
            }
        }
    }

    public OrderSummary placeOrderNow() throws InterruptedException {
        scrollToElement(placeOrderBtn);
        placeOrderBtn.click();
        return new OrderSummary(driver, wait);
    }

}
