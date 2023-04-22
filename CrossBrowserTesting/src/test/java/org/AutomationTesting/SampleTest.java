package org.AutomationTesting;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    @Test
    public void SampleTestCase() throws MalformedURLException {

        // Since we are testing in cloud we should use Remote driver as we are going to send the driver
        // We should also specify where we are sending the driver and also the capabilities
        MutableCapabilities mutableCapabilities = new MutableCapabilities();

        WebDriver driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub/"), mutableCapabilities);

        driver.get("https://rahulshettyacademy.com/");

        Assert.assertTrue(driver.getTitle().matches("Selenium.*"));

        driver.quit();
    }
}
