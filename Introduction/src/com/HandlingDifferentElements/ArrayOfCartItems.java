package com.HandlingDifferentElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayOfCartItems {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }

        // Products reqruired as ArrayList
        String[] productsRequired = {"Cucumber", "Brocolli", "Beetroot", "Beans", "Potato"};
        // The List returned by Arrays.asList method of java.util.Arrays class
        // is a fixed-size list object which means that elements cannot be added to or removed from the list.
        // Easy work around is just to pass in the List into an ArrayList's constructor.
        List productsRequiredList = new ArrayList(Arrays.asList(productsRequired));

        // Get all the product names
        List<WebElement> productsAvailable = driver.findElements(By.cssSelector("h4.product-name"));

        // Loop through each product
        for (int i = 0; i < productsAvailable.size(); i++){
            // Product name Eg: Brocolli - 1Kg. Split to get only the product name
            String product = productsAvailable.get(i).getText().split("-")[0].trim();
            // Add to cart if product name is present in the list of products required
            if (productsRequiredList.contains(product)){
                // Never rely on text to identify the locators. Text can change dynamically
                // driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                productsRequiredList.remove(product);
                // If we have found all the products then exit for loop.
                if (productsRequiredList.isEmpty())
                    break;
            }
        }
    }
}
