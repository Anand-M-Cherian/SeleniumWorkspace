import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v110.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v110.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkFailedRequest {

    // Sometimes we might need to validate the error message servers are busy need to be displayed when
    // a request is failed.
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Pattern should be sent as RequestPattern (fetch model) data type wrapped as list and
        // again wrapped as Optional class.
        Optional<List<RequestPattern>> requestPatternList = Optional.of(Arrays.asList(new RequestPattern(
                Optional.of("*GetBook*"),
                Optional.empty(),
                Optional.empty())));
        devTools.send(Fetch.enable(requestPatternList, Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request -> {

            devTools.send((Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED)));

        } );

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.cssSelector(".btn-primary")).click();

        // driver.close();

    }
}
