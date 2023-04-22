import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocalizationTesting {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        //============//
        Map<String, Object> coordinates = new HashMap<String, Object>();
        coordinates.put("latitude", 40);
        coordinates.put("longitude", 3);
        coordinates.put("accuracy", 1);

        // for this to work, the site should have access to know your location
        // so manually do once and grant location access

        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        driver.get("https://the-internet.herokuapp.com/geolocation");

        driver.findElement(By.cssSelector("button[onclick='getLocation()']")).click();

        String lattitude = driver.findElements(By.xpath("//p/div")).get(0).getText();
        String longitude = driver.findElements(By.xpath("//p/div")).get(1).getText();

        System.out.println("Lattitude: " + lattitude + "\n" + "Longitude: " + longitude);

        driver.findElement(By.linkText("See it on Google")).click();

        Thread.sleep(2000);

        TakesScreenshot driverSS = (TakesScreenshot) driver;
        File source = driverSS.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "/reports/" + "LocationInMapScreenshot.png");
        FileUtils.copyFile(source, destination);

        driver.close();

    }
}