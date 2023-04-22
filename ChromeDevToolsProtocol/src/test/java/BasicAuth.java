import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuth {

    // For URLs with Basic authentication enabled, it expects username and password to get the response
    // It is not a web pop up. So selenium cannot handle. It is a window pop up.
    // Credentials will be remembered until cookies and cache is cleared.

    public static void main(String[] args) {

        // URI class in JAVA helps to get url what we have hit in the browser
        // https://httpbin.org/basic-auth/foo/bar
        // https://httpbin.org -> Host (Main Domain)
        // basic-auth -> Resource
        // foo/bar -> parameters

        // predicates : used to create filter conditions for data
        // takes input, filters it, then gives output
        // can be achieved with lambda expression

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        // Create a predicate with the conditions for filtering out the required domains.
        // then register the driver using the predicate and credentials
        // When driver hits the URL, then credentials will be passed to the domain

        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
        ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));

        driver.get("https://httpbin.org/basic-auth/foo/bar");

    }
}
