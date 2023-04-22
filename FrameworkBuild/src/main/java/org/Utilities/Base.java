package org.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    // We will store all utilities in src\main\java\Utilities
    // The Base class starts the framework (Main method)

    // To make the scope of the driver to entire class
    public WebDriver driver;

    // common method for initializing WebDriver so that if it needs to change, we only need to
    // edit in one place.
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\TestSetup.properties");
        prop.load(fis);

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\anand\\WebDrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\anand\\WebDrivers\\msedgedriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.out.println("Not a valid browser, hence defaulting to chrome");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }

        // Setting the synchronization time
        // Make sure to set language  level to 8
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}
