import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

// Since this is JAVA project add external JARS for Selenium Server and TestNG
public class GoogleTest {

    @Test
    public void homePageCheck() throws MalformedURLException {

        DesiredCapabilities desiredCap = new DesiredCapabilities();
        desiredCap.setBrowserName("chrome");
        desiredCap.setPlatform(Platform.WIN10);
        // Set additional capabilities
        // desiredCap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        // RemoteWebDriver needs knowledge of hub IP address and capabilities
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.29.36:4444"), desiredCap);

        driver.get("https://www.google.com/");

        driver.findElement(By.name("q")).sendKeys("Anand M Cherian");

        System.out.println(driver.getTitle());
    }
}