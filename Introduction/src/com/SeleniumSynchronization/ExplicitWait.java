package com.SeleniumSynchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplicitWait {

    public static void main(String[] args) {

        // **********Explicit Wait***********
        // Pros: only applied to wherever required. No hiding performance issues.
        // Cons: More code

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }

        String[] productsRequired = {"Cucumber", "Brocolli", "Beetroot", "Beans", "Potato"};

        // building a utility
        addItemsToCart(driver, productsRequired);

        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//div[@class='cart-preview active']/div[@class='action-block']/button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector("button.promoBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
        System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());

    }

    public static void addItemsToCart(WebDriver driver, String[] productsRequired) {

        List productsRequiredList = new ArrayList(Arrays.asList(productsRequired));

        List<WebElement> productsAvailable = driver.findElements(By.cssSelector("h4.product-name"));

        for (int i = 0; i < productsAvailable.size(); i++){
            String product = productsAvailable.get(i).getText().split("-")[0].trim();
            if (productsRequiredList.contains(product)){
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                productsRequiredList.remove(product);
                if (productsRequiredList.isEmpty())
                    break;
            }
        }

    }
}
