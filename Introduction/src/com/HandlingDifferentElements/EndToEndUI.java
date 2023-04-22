package com.HandlingDifferentElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class EndToEndUI {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // From and To
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='DEL']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();

        // Departure date and return date isDisabled??
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        if (driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5")) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }

        // 3 Adults
        driver.findElement(By.id("divpaxinfo")).click();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        for (int i = 1; i <= 3; i++){
            driver.findElement(By.id("hrefIncAdt")).click();
        }
        driver.findElement(By.id("btnclosepaxoption")).click();

        // Family and friends checkbox
        driver.findElement(By.cssSelector("input[id*='chk_friendsandfamily']")).click();

        // Search
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();

    }
}
