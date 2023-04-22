package com.ActionsFramesChildWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowHandlesAssignment {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");

        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.findElement(By.linkText("Click Here")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iter = windowHandles.iterator();
        String parentWindowHandle = iter.next();
        String childWindowHandle = iter.next();
        driver.switchTo().window(childWindowHandle);

        System.out.println(driver.findElement(By.cssSelector("h3")).getText());
        driver.switchTo().window(parentWindowHandle);
        System.out.println(driver.findElement(By.cssSelector("h3")).getText());
    }
}
