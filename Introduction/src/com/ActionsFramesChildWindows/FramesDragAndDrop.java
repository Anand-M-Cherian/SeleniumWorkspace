package com.ActionsFramesChildWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FramesDragAndDrop {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/droppable/");

        // Frame is detached from the HTML and may contain elements from other websites.
        // We have to give knowledge to Selenium about the frame
        // Frame can be identified by index. Starts from 0.
        System.out.println("No. of frames: " + driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);
        // Or frame can be identified by web element
        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        // Drag and Drop
        Actions a = new Actions(driver);
        WebElement dragSource = driver.findElement(By.id("draggable"));
        WebElement dragTarget = driver.findElement(By.id("droppable"));
        a.dragAndDrop(dragSource, dragTarget).build().perform();

        // Move back to default content
        driver.switchTo().defaultContent();
        System.out.println(driver.findElement(By.cssSelector("h2 a")).getText());
    }
}
