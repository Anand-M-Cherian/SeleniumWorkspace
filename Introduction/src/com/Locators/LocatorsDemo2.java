package com.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class LocatorsDemo2 {

    public static String getPassword(WebDriver driver){
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.linkText("Forgot your password?")).click();
        try {
            Thread.sleep(1000); // Waiting for page movement to stop
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        String infoMessage = driver.findElement(By.cssSelector("form p")).getText();
        String password = infoMessage.split("'", 3)[1];
        return password;
    }


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String password = getPassword(driver);

        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        String userName = "Anand";
        driver.findElement(By.id("inputUsername")).sendKeys(userName);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        driver.findElement(By.className("signInBtn")).click();
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }

        // By.tagName
        driver.findElement(By.tagName("p")).getText();
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Hello " + userName + ",");

        // Identiying element by text (only for Xpath) -> //tagname[text()='value']
        driver.findElement(By.xpath("//button[text()='Log Out']")).click();

        // We can omit tagname in Xpath and CSS if we are certain that no other tagnames exist
        // with the given attribute value
        System.out.println(driver.findElement(By.xpath("//*[contains(@class,'overlay')][2]/h1")).getText());
        System.out.println(driver.findElement(By.cssSelector("[class*='overlay']:nth-child(2) p")).getText());

        // [class*='container']:nth-child(1)
        // Selenium searches for an element with class that contains the string 'container'
        // the element should be the first child of the parent element
        driver.close();
    }
}
