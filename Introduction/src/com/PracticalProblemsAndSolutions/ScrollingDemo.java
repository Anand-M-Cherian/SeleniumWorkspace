package com.PracticalProblemsAndSolutions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class ScrollingDemo {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // When executing test in headless mode, it is always safe to invoke methods on a
        // web element which is in focus (visible to the user).

        // Scrolling the window using JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");

        Thread.sleep(5000);

        // Scrolling down a web table
        js.executeScript("document.querySelector(\".tableFixHead\").scrollTop = 10000");

        // Summing up the 'amounts' in the fourth column of the table
        int actualSum = 0;
        List<WebElement> amounts = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        for (WebElement amount : amounts) {
            actualSum = actualSum + Integer.parseInt(amount.getText());
        }
        System.out.println("Total Amount Collected: " + actualSum);

        int expectedSum = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(": ")[1]);

        Assert.assertEquals(actualSum, expectedSum);
    }
}
