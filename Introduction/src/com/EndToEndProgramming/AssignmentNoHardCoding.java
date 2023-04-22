package com.EndToEndProgramming;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AssignmentNoHardCoding {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        // Checkbox
        driver.findElement(By.xpath("//div[@id='checkbox-example'] //label[2]/input")).click();
        String text = driver.findElement(By.xpath("//div[@id='checkbox-example'] //label[2]")).getText().trim();

        // Dropdown
        Select optionSelect = new Select(driver.findElement(By.id("dropdown-class-example")));
        optionSelect.selectByVisibleText(text);

        // Alert
        driver.findElement(By.id("name")).sendKeys(text);
        driver.findElement(By.id("alertbtn")).click();
        if (driver.switchTo().alert().getText().contains(text)) {
            System.out.println(text + " is present in the alert message");
        } else {
            System.out.println(text + " is NOT present in the alert message");
        }
        driver.switchTo().alert().accept();

        driver.close();
        driver.quit();
    }
}
