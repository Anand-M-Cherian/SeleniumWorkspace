import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;

import java.time.Duration;
import java.util.Optional;

public class NetworkBlockedRequest {

    // When we only need to validate the functional scenario, we might not need the webpage to load any images.
    // Hence, we blocked the .jpg requests so that test execution is faster.

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // setBlockedURLs method needs list of URLs as string. This string URL is used to build an immutable map
        // Hence we should pass as ImmutableList
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        System.out.println(driver.findElement(By.cssSelector(".sp")).getText());
    }
}
