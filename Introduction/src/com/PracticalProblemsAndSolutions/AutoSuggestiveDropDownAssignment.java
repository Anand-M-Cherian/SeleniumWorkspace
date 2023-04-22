package com.PracticalProblemsAndSolutions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AutoSuggestiveDropDownAssignment {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.id("autocomplete")).sendKeys("ind");
        Thread.sleep(3000);

        List<WebElement> autoSuggestiveList = driver.findElements(By.cssSelector(".ui-menu-item"));
        for (WebElement autoSuggestedOption : autoSuggestiveList) {
            if (! autoSuggestedOption.getText().equalsIgnoreCase("india")) {
                driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
            }
            else {
                driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
                break;
            }
        }
        Thread.sleep(3000);

        // System.out.println(driver.findElement(By.id("autocomplete")).getAttribute("value"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value = (String) js.executeScript("return document.getElementById('autocomplete').value;");
        System.out.println(value);
    }
}
