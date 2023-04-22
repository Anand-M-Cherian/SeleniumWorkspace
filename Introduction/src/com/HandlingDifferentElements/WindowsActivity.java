package com.HandlingDifferentElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsActivity {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.co.in/");
        driver.navigate().to("https://in.search.yahoo.com/?fr2=inr");
        driver.navigate().back();
        driver.navigate().forward();

        // driver.get() method has inbuilt page synchronization
    }
}
