package com.SeleniumSynchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitDemo {

    public static void main(String[] args) {

        //***********Fluent Wait************
        // until timeout checks the DOM for the required element at regular POLLLING INTERVALS
        // Pros: polling frequency
        // Cons: More code, No inbuilt methods

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.cssSelector("#start button")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                // ignore any NoSuchElementexception during the Timeout of 30 s
                .ignoring(NoSuchElementException.class);

        // the apply method should return a WebElement. as it keeps checking the DOM, it returns null
        // if the element has not loaded yet. the FluentWait ignores NoSuchElement and polls again after 3 seconds.
        // when element is found WebElement is returned and FluentWait proceeds
        WebElement textField = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {

                // trying to create a method similar to visibilityOfElementsLocated of WebDriverWait class
                // because the element changes from
                // style="display:none" ->->->->->-> style
                if (driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()) {
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                } else {
                    return null;
                }
            }
        });

        System.out.println(driver.findElement(By.cssSelector("[id='finish'] h4")).getText());

    }
}
