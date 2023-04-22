package com.StreamsLambdaExpressions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class FilterWebTables {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        driver.findElement(By.id("search-field")).sendKeys("be");

        List<WebElement> vegElements = driver.findElements(By.xpath("//tr //td[1]"));

        List<WebElement> filteredList = vegElements.stream().filter(s -> {
            return s.getText().toLowerCase().contains("be");
        }).collect(Collectors.toList());

        Assert.assertTrue(vegElements.equals(filteredList));
    }
}
