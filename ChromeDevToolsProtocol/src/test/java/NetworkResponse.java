import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.devtools.v110.network.model.Request;
import org.openqa.selenium.devtools.v110.network.model.Response;

import java.util.Optional;

public class NetworkResponse {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enables network tracking, network events will now be delivered to the client.
        // client is selenium in our case
        // now client can listen
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // now we need response details. For this we need to wait until response event is triggered.
        // Once response is triggered, before it is reflected in front end, we catch the event and pull details.
        // Network.responseReceived event is Fired when HTTP response is available.
        // Listener will keep on listening until that event is triggered
        // Consumer handler deals with the data that generates from the event trigger
        // we have to act upon the consumer handler object to get the details. Lambda expression is used for this
        // The handler object scope will be inside the lambda expression block only

        // getResponse returns object of type Response
        // https://chromedevtools.github.io/devtools-protocol/tot/Network/#type-Response
        devTools.addListener(Network.responseReceived(), response ->
        {
            Response res = response.getResponse();
            System.out.println("Response URL: " + res.getUrl());
            System.out.println(res.getStatus());
            if (res.getStatus().toString().startsWith("4")) {
                System.out.println(res.getUrl() + " is failing with status code " + res.getStatus());
            }
        });

        // same way for request also
        devTools.addListener(Network.requestWillBeSent(), request ->
        {
            Request req = request.getRequest();
            System.out.println("Request URL: " + req.getUrl());
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.close();
    }
}
