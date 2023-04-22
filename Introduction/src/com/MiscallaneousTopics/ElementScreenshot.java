package com.MiscallaneousTopics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class ElementScreenshot {

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameEditBox = driver.findElement(By.cssSelector(".form-control.ng-untouched.ng-pristine.ng-invalid"));
        nameEditBox.sendKeys("Anand M Cherian");
        File screenShot = nameEditBox.getScreenshotAs(OutputType.FILE);

        // File generated in Project path by default
        FileUtils.copyFile(screenShot, new File("pic.png"));
    }
}
