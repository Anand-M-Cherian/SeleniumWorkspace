package com.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class  LocatorsDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");

        // Implicit Wait - Waiting for something to show
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // class = "submit signInBtn" means it is a compound class of
        // "submit" and "signInBtn"
        // Tagname = a -> The element is a link
        // By.id, By.name, By.classname

        driver.findElement(By.id("inputUsername")).sendKeys("Anand");
        driver.findElement(By.name("inputPassword")).sendKeys("hello123");
        driver.findElement(By.className("signInBtn")).click(); // only one of the compound class is required

        // =============CSS selector=============
        // When tagname is unique in the page -> tagname
        // tagname#id
        // tagname.className || tagname can be avoided. Then Selenium will fetch on first come first serve basis ->
        // Selenium scans the page top left corner
        // If Id and Classname is not present: tagname[attribute='value']
        // In case of multiple matches -> tagname[attribute='value']:nth-child(index)
        // Parent child traverse -> parentTagName childTagName
        // Regex -> tagname[attribute*='value']
        // Chrome developer validation -> $('<<CSS Selector>>')


        // =============XPATH=============
        // When tagname is unique in the page ->  //tagname
        // Syntax -> //tagname[@attribute='value']
        // In case of multiple matches -> //tagname[@attribute='value'][index]
        // Parent child traverse -> //parentTagName/childTagName
        // Regex -> //tagname[contains(@attribute,'value')]
        // Chrome developer validation -> $x('<<Xpath>>')


        String errorMessage = driver.findElement(By.cssSelector("p[class='error']")).getText();
        System.out.println(errorMessage);
        driver.findElement(By.linkText("Forgot your password?")).click();
        try {
            Thread.sleep(1000); // Waiting for page movement to stop
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }


        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("tester@abc.com");
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("John@abc.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("0110001");
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        String infoMessage = driver.findElement(By.cssSelector("form p")).getText();
        System.out.println(infoMessage);
        driver.findElement(By.xpath("//div[contains(@class,'forgot')]/button[1]")).click();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }


        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("John");
        driver.findElement(By.cssSelector("input[name*='Password']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//input[contains(@id,'chkbox')]")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
