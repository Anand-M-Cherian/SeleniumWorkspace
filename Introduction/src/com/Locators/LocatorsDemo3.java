package com.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LocatorsDemo3 {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Absolute Xpath -> /html/body/header....
        // Relative Xpath -> //parentTagName/childTagName....
        // Sibling traverse -> //parentTagName/childTagName/following-sibling::siblingTagName
        // Child to parent traverse -> //element/parent::parentTagName
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//button[text()='Login']/parent::div/button[2]")).getText());
        driver.close();
    }
}
