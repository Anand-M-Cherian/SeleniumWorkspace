import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ModifiedUrlForWindowPopUp {
    public static void main(String[] args) {

        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        // modified url with credentials
        // whenever window sign in pop up is encountered, this credential is passed
        driver.get("http://admin:admin@the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Basic Auth")).click();
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
    }
}