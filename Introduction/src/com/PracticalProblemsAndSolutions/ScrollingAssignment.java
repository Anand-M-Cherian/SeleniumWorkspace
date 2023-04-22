package com.PracticalProblemsAndSolutions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ScrollingAssignment {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");

        // printing the number of rows and columns
        int rowCount = driver.findElements(By.cssSelector(".table-display tr")).size();
        int colCount = driver.findElements(By.cssSelector(".table-display tr th")).size();
        System.out.println("Row: " + rowCount + " Col: " + colCount);

        // printing the contents of second row
        List<WebElement> rowContent = driver.findElements(By.cssSelector(".table-display tr:nth-child(3) td"));
        for (WebElement rowCell : rowContent) {
            System.out.print("\t||\t" + rowCell.getText() + "\t||\t");
        }
        System.out.println();
    }
}
