package org.ObjectRepository;


import org.AbstractComponents.PageObjectParent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends PageObjectParent {

    private WebDriver driver;
    private WebDriverWait wait;

    public LandingPage(WebDriver driver, WebDriverWait wait) {

        // driver and wait knowledge transfer: TC -> PageObject class -> PageObject parent class
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    private WebElement emailTextBox;

    @FindBy(id = "userPassword")
    private WebElement passwordTextBox;

    @FindBy(id = "login")
    private WebElement loginBtn;

    @FindBy(css = "[class*='ng-trigger-flyInOut']")
    private WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    // user login action
    public ProductCatalogue userLogin(String email, String password) {
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        loginBtn.click();
        return new ProductCatalogue(driver, wait);
    }
}
