package com.ActionsFramesChildWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class WndowHandles {

    public static void main(String[] args) {

        // Selenum treats any new window or tab opened from the current window as a 'window'.
        // It follows a parent child hierarchy

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.findElement(By.cssSelector(".blinkingText")).click();

        // Getting the parent and child window handles
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iter = windowHandles.iterator();
        String parentWindow = iter.next();
        String childWindow = iter.next();

        // Switching to the child window
        driver.switchTo().window(childWindow);
        System.out.println(driver.findElement(By.cssSelector(".im-para.red")).getText());

        // Extracting out the email ID
        String email = driver.findElement(By.cssSelector(".im-para.red")).getText().split("at ")[1].split(" ")[0];

        // Coming back to parent window
        driver.switchTo().window(parentWindow);
        driver.findElement(By.id("username")).sendKeys(email);
    }
}
