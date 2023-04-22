import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class ConsoleLogs {

    // Network domain can only find errors in API calls.
    // When front end fails due to Javascript code error, it is logged in the console

    // Ideally this should be included in OnTestFailure of TestNG listener because we don't know when test wil fail
    // Similar to taking screenshot when test fails

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        List<LogEntry> logEntriesList =  driver.manage().logs().get(LogType.BROWSER).getAll();

        for (LogEntry logEntry : logEntriesList) {
            System.out.println(logEntry.getMessage());
        }
    }
}
