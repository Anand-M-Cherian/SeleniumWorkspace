package com.ActionsFramesChildWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class MultipleWindowsAndTabs {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        driver.switchTo().newWindow(WindowType.TAB);

        // changing scope to new tab
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iter = windowHandles.iterator();
        String parentiWindowHandle = iter.next();
        String childWindowHandle = iter.next();
        driver.switchTo().window(childWindowHandle);
        driver.get("https://rahulshettyacademy.com/");

        String text = driver.findElement(By.xpath("//h2/a[contains(@href, 'https://courses.rahulshettyacademy.com/')]")).getText();

        driver.switchTo().window(parentiWindowHandle);
        WebElement nameEditBox = driver.findElement(By.cssSelector(".form-control.ng-untouched.ng-pristine.ng-invalid"));
        nameEditBox.sendKeys(text);

    }
}
