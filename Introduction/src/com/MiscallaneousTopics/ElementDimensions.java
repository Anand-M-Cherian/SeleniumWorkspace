package com.MiscallaneousTopics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementDimensions {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameEditBox = driver.findElement(By.cssSelector(".form-control.ng-untouched.ng-pristine.ng-invalid"));

        // Dimension can change according to window size if the developer
        // has designed for optimum web responsiveness
        System.out.println(nameEditBox.getRect().getDimension().getWidth());
        System.out.println(nameEditBox.getRect().getDimension().getHeight());
    }
}
