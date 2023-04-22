package com.SeleniumSynchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SynchronizationAssignment {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("password")).sendKeys("learning");
        driver.findElement(By.xpath("//label[@class='customradio'][2]/span[@class='checkmark']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
        driver.findElement(By.id("okayBtn")).click();
        Select userTypeSelect = new Select(driver.findElement(By.cssSelector("select.form-control")));
        userTypeSelect.selectByIndex(2);
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("signInBtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn.btn-info")));
        List<WebElement> addAllProducts = driver.findElements(By.cssSelector("button.btn.btn.btn-info"));
        for (WebElement addProduct : addAllProducts) {
            addProduct.click();
        }
        driver.findElement(By.cssSelector("a.nav-link.btn.btn-primary")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn-success")));
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        driver.findElement(By.id("country")).sendKeys("ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='suggestions']/ul")));
        List<WebElement> countryParentElements = driver.findElements(By.xpath("//div[@class='suggestions']/ul"));
        for (WebElement countryParentElement : countryParentElements) {

        // StateElementReferenceException: element not attached to the page
        // ie...element is being removed from the DOM and Selenium can't find it
        // Workaround: define the element twice with exception handling
            int attempts =0;
            while (attempts < 2){
                try {
                    WebElement country = countryParentElement.findElement(By.xpath("li/a"));
                    if (country.getText().equalsIgnoreCase("india")) {
                        country.click();
                    }
                }
                catch (org.openqa.selenium.StaleElementReferenceException e) {}
                attempts++;
            }

        }
        driver.findElement(By.cssSelector("label[for='checkbox2']")).click();
        driver.findElement(By.cssSelector("input.btn.btn-success.btn-lg")).click();
        System.out.println(driver.findElement(By.xpath("//div[contains(@class,'alert-success')]")).getText());
    }
}
