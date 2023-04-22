package com.MiscallaneousTopics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class DeleteCookieMaximizeScreenshot {

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        DesiredCapabilities handlSSLErr = new DesiredCapabilities();
        handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriver driver = new ChromeDriver(handlSSLErr);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        // Eg: delete session login cookie and verify login url appears
        // driver.manage().deleteCookieNamed("sessionKey");
        // Click on any link
        // login page opens and get url

        driver.get("https://expired.badssl.com/");

        // take screenshot as file
        // make sure to add common.io jar to build path
        // C Drives are usually not accessible -> will throw AccessDeniedException
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\anand\\Documents\\Selenium Webdriver\\pic.png"));
    }
}
