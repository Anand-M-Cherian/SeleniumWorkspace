package com.EndToEndProgramming;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CalendarGenericMethods {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.path2usa.com/travel-companions/");
        driver.findElement(By.id("travel_date")).click();

        // Month
        WebElement month = driver.findElement(By.cssSelector(".datepicker-days .datepicker-switch"));
        while (!month.getText().contains("December")) {
            driver.findElement(By.cssSelector(".datepicker-days .next")).click();
        }

        // Day
        List<WebElement> dates = driver.findElements(By.cssSelector("td[class='day']"));
        for (WebElement date : dates) {
            if (date.getText().equalsIgnoreCase("25")) {
                date.click();
                break;
            }
        }
    }
}
