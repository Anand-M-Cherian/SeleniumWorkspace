import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v110.network.Network;

import java.util.Optional;

public class NetworkMocking {

    // We are trying to get the webpage when only 1 book is available. We do this by mocking the original request from
    // shetty to BadGuy. We can also achieve this by mocking the response to only return one book.

    // Fetch Domain: A domain for letting clients substitute browser's network layer with client code.

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Fetch.enable(patterns, handleAuthRequests)
        // patterns: filter specific types of requests/responses. like .css .html etc
        // Since we are sending patterns as empty, it will match all the requests.
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

        // requestPaused() event will trigger when a request is fired.
        // It won't send that request to server. Instead, it will wait until client responds
        // with one of continueRequest, failRequest or fulfillRequest
        // During this time we should mock the request by manipulating the request that the addListener returns
        // This we do through Lambda expression
        // https://chromedevtools.github.io/devtools-protocol/tot/Network/#type-Request
        devTools.addListener(Fetch.requestPaused(), request -> {
            // only stopping and modifying the request with url containing shetty. letting all others go.
            if(request.getRequest().getUrl().contains("=shetty")) {
                String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println(mockedUrl);

                // only mocking the url, remaining all are send as the same way as we got them
                // wrap everything in Optional class
                // then we are resuming the request which we paused.
                devTools.send(Fetch.continueRequest(
                        request.getRequestId(),
                        Optional.of(mockedUrl),
                        Optional.of(request.getRequest().getMethod()),
                        // we do not have any headers or post data, hence sending it as empty
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                ));
            }

            // if there is no match in URL with shetty, we should continue them as they have been paused.
            // request paused has paused all requests as we have given pattern filter as empty.
            else {
                devTools.send(Fetch.continueRequest(
                        // not mocking anything here
                        request.getRequestId(),
                        Optional.of(request.getRequest().getUrl()),
                        Optional.of(request.getRequest().getMethod()),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                ));
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.cssSelector(".btn-primary")).click();
        Thread.sleep(3000);

        System.out.println(driver.findElement(By.cssSelector("p")).getText());

        // driver.close();
    }
}
