package com.StreamsLambdaExpressions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class IsWebTableSorted {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        driver.findElement(By.cssSelector("th:nth-child(1) .sort-icon")).click();

        List<WebElement> vegElementList = driver.findElements(By.xpath("//tr //td[1]"));

        List<String> vegNamesOriginal = vegElementList.stream().map(s -> s.getText()).collect(Collectors.toList());

        List<String> vegNamesSorted = vegNamesOriginal.stream().sorted().collect(Collectors.toList());

        Assert.assertTrue(vegNamesSorted.equals(vegNamesOriginal));




    }
}
