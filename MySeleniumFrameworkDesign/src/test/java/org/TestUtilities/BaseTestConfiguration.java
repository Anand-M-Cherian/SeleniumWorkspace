package org.TestUtilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.ObjectRepository.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTestConfiguration {

    // public Object array containing driver and wait objects
    public Object[] seleniumObjects = new Object[2];
    public LandingPage landingPage;

    public void initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalData.properties");
        prop.load(fis);

        // WebDriverManager automatically downloads the latest chrome driver and sets it up
        // We don't need to manage file locations, versions or anything

        // check whether we are passing any global system variables through mvn commands from terminal
        // otherwise use the property from GlobalData.properties external file.
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : (prop.getProperty("browser"));

        if (browserName.toLowerCase().contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            // Run chrome in headless mode. Chrome engine executes in back end through APi
            if (browserName.toLowerCase().contains("headless")) {
                chromeOptions.addArguments("headless");
            }
            seleniumObjects[0] = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            seleniumObjects[0] = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            seleniumObjects[0] = new EdgeDriver();
        } else {
            System.out.println("Not a valid browser, hence defaulting to chrome");
            WebDriverManager.chromedriver().setup();
            seleniumObjects[0] = new ChromeDriver();
        }

        WebDriver driver = (WebDriver) seleniumObjects[0];
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Always better to maximize window to avoid non visibility issue

        // If running in headless, then run in full screen resolution to catch all the elements.
        // Set size according to respective display. Else, chance of element click interception
        // This will run on top over windows.maximize command
        // Step is optional. If TC has any flakiness, we can include
        // currently we are facing issue of element click interception
        if (browserName.contains("headless")) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }
        driver.manage().window().maximize();

        // WebDriverWait
        seleniumObjects[1] = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public List<HashMap<String, String>> getTestDataFromJSON(String jsonPath) throws IOException {

        //json to String
        String content = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);

        //String to HashMap using Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap <String, String>> testData = mapper.readValue(content, new TypeReference<List <HashMap <String, String>>>() {});
        return testData;
    }

    public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

        // The driver over here is coming from extent reports and listeners utility
        // which in turn is getting from ITestResult variable which has knowledge about the
        // driver that is executing the current test case

        TakesScreenshot driverSS = (TakesScreenshot) driver;
        File source = driverSS.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
        FileUtils.copyFile(source, destination);
        return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
    }

    // TestNG will scan current class and all parent classes to check for any @Before and @After annotations
    // Hence, we add the all the boilerplate code into the parent class (base test)
    @BeforeMethod (alwaysRun = true)
    public void launchApplication() throws IOException {
        initializeDriver();
        WebDriver driver = (WebDriver) seleniumObjects[0];
        WebDriverWait wait = (WebDriverWait) seleniumObjects[1];
        landingPage = new LandingPage(driver, wait);
        landingPage.goTo();
    }

    @AfterMethod (alwaysRun = true)
    public void closeDriver() {
        WebDriver driver = (WebDriver) seleniumObjects[0];
        driver.close();
    }
}
