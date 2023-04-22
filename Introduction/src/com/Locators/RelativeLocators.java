package com.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;


public class RelativeLocators {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        // Remember to type in the import statement. Since it is a static import, the methods
        // won't be auto suggested
        WebElement nameEditBox = driver.findElement(By.cssSelector(".form-control.ng-untouched.ng-pristine.ng-invalid"));
        System.out.println(driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText());

        // Relative locators can not handle flex elements. If so, it finds the next element in that direction.
        WebElement dob = driver.findElement(By.cssSelector("[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dob)).click();

        // Relative locators work using tag names
        WebElement checkMeOut = driver.findElement(By.cssSelector("[for='exampleCheck1']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(checkMeOut)).click();

        WebElement radioButton = driver.findElement(By.id("inlineRadio1"));
        System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(radioButton)).getText());

    }
}
