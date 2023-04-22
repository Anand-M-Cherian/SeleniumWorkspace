import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumIntroduction {

	public static void main(String[] args) {

		// CHROME
		// Invoke Chrome Browser
		//System.setProperty("webdriver.chrome.driver", "C:\Users\anand\WebDrivers\\chromedriver.exe");

		// We only need methods of WebDriver that were implemented by ChromeDriver
		//WebDriver driver = new ChromeDriver();

		// FIREFOX
		/*System.setProperty("webdriver.gecko.driver", "C:\\Users\\anand\\WebDrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();*/

		// EDGE
		System.setProperty("webdriver.edge.driver", "C:\\Users\\anand\\WebDrivers\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

		// close() method closes only the current window.
		// quit() method closes all the associated windows that were opened by Selenium
		driver.close();

	}

}
