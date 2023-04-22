package com.ActionsFramesChildWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsDemo {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver110.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");

        Actions a = new Actions(driver);

        // Simulate user actions by compounding methods and using build-perform pattern
        a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();

        // Move to element and right click
        a.moveToElement(driver.findElement(By.id("nav-link-accountList"))).contextClick().build().perform();

    }
}
