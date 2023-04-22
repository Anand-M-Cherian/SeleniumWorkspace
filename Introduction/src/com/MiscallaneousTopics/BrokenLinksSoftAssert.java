package com.MiscallaneousTopics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinksSoftAssert {

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Broken links are actually broken URLs
        // The href attribute of anchor tag holds a broken URL
        // Selenium gets us the URL using getAttribute method
        // Java gets us the Network Status Code using URL class

        SoftAssert softAssert = new SoftAssert();

        System.out.println("The broken links are: ");
        List<WebElement> links = driver.findElements(By.cssSelector("li.gf-li a"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            // Store all failures is in SoftAssert object
            softAssert.assertTrue(conn.getResponseCode()<400, link.getText() + " is broken");
        }
        // Enforce failure if any
        softAssert.assertAll();
    }
}
