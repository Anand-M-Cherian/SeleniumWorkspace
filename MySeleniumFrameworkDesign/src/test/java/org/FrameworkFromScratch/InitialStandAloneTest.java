package org.FrameworkFromScratch;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class InitialStandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        // WebDriverManager automatically downloads the latest chrome driver and sets it up
        // We don't need to manage file locations, versions or anything
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Always better to maximize window to avoid non visibility issue
        driver.manage().window().maximize();
        Actions action = new Actions(driver);
        driver.get("https://rahulshettyacademy.com/client");


        // User Login
        driver.findElement(By.id("userEmail")).sendKeys("betixeh188@lurenwu.com");
        driver.findElement(By.id("userPassword")).sendKeys("Testitout123!@");
        driver.findElement(By.id("login")).click();

        //Add Products
        String productName = "zara coat 3";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        // get me the first matching product or else null
        WebElement item = products.stream().filter(product -> {
            return getProductName(product).equalsIgnoreCase(productName);
        }).findFirst().orElse(null);
        addProduct(item);

        // Waiting for product to add
        // waiting for green message to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        // waiting for loading to disappear. usual syntax was giving lag in performance
        // so used from driver.findElement
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
        driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();

        // Checking the carts for product added
        // anyMatch() gives true if any element of stream matches given condition
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
        Assert.assertTrue(cartItems.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName)));
        driver.findElement(By.cssSelector(".subtotal .btn-primary")).click();

        // Shipment Information
        driver.findElement(By.cssSelector(".user__address .txt")).sendKeys("ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
        WebElement placeOrderBtn = driver.findElement(By.cssSelector(".action__submit"));
        List<WebElement> countries = driver.findElements(By.cssSelector(".list-group .list-group-item"));
        for (WebElement country: countries) {
            if (country.getText().equalsIgnoreCase("india")) {
                country.click();
                break;
            }
        }
        // to avoid element click interception of place order button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderBtn);
        Thread.sleep(1000);
        placeOrderBtn.click();

        // Order Summary
        String thanksMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        // selenium always looks for text on screen while doing getText()
        Assert.assertTrue(thanksMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        String orderNo = driver.findElement(By.cssSelector(".box .ng-star-inserted label")).getText();
        System.out.println(orderNo);
        // avoiding element click interception for sign out button
        WebElement signOutBtn = driver.findElement(By.cssSelector("li:nth-child(5) .btn.btn-custom"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signOutBtn);
        Thread.sleep(1000);
        signOutBtn.click();
        driver.close();
    }

    private static String getProductName(WebElement product) {
        return product.findElement(By.cssSelector("b")).getText();
    }

    // css last of type selects the last css matching the description
    private static void addProduct(WebElement product) {
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    }

}
