package com.StreamsLambdaExpressions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class CustomStreamMapperMethods {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        List<WebElement> vegElements = driver.findElements(By.xpath("//tr //td[1]"));

        List<String> vegPrice = vegElements.stream().filter(s -> {
            return s.getText().contains("Rice");
        }).map(s -> getPrice(s)).collect(Collectors.toList());

        // simple code to print list
        vegPrice.forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    private static String getPrice(WebElement s) {
        return s.findElement(By.xpath("following-sibling::td[1]")).getText();
    }
}
