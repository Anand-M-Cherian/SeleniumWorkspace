package com.StreamsLambdaExpressions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class Pagination {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        List<WebElement> vegElements;
        List<String> vegPrice;

        do {
            vegElements= driver.findElements(By.xpath("//tr //td[1]"));

            vegPrice = vegElements.stream().filter(s -> {
                return s.getText().contains("Beans");
            }).map(s -> getPrice(s)).collect(Collectors.toList());

            if (vegPrice.isEmpty()){
                driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
            }
        } while (vegPrice.isEmpty());

        vegPrice.forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    private static String getPrice(WebElement s) {
        return s.findElement(By.xpath("following-sibling::td[1]")).getText();
    }
}
