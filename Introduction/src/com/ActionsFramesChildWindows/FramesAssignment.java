package com.ActionsFramesChildWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FramesAssignment {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");

        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        // driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-top']")));
        // driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-middle']")));

        System.out.println(driver.findElement(By.id("content")).getText());


    }
}
