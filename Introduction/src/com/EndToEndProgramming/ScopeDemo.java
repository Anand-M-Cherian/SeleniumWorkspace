package com.EndToEndProgramming;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ScopeDemo {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        // getting the count of links on the ENTIRE page
        System.out.println("Number of links on the page: " + driver.findElements(By.tagName("a")).size());

        // getting the count of links in the footer ONLY
        // the WebElement containing the links act as a 'mini driver'
        WebElement footerElement = driver.findElement(By.id("gf-BIG"));
        System.out.println("Number of link in the footer: "  + footerElement.findElements(By.tagName("a")).size());

        // Getting the count of links under 'Discount Coupons'
        WebElement linkParent = footerElement.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        List<WebElement> links = linkParent.findElements(By.tagName("a"));

        // Key sequences
        String ctrlClick = Keys.chord(Keys.CONTROL, Keys.ENTER);
        for (int i = 1; i < links.size(); i++) {
            (links.get(i)).sendKeys(ctrlClick);
        }
        Assert.assertEquals(driver.getWindowHandles().size() - 1, links.size() - 1);

        // Print window titles
        Set<String> openWindows = driver.getWindowHandles();
        Iterator<String> iter = openWindows.iterator();
        while (iter.hasNext()) {
            driver.switchTo().window(iter.next());
            System.out.println(driver.getTitle());
        }

    }
}
