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
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\anand\\WebDrivers\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

		// close() method closes only the current window.
		// quit() method closes all the associated windows that were opened by Selenium
		driver.close();


		// System.setProperty("webdriver.edge.driver", "C:\\Users\\anand\\WebDrivers\\msedgedriver.exe");
		// The above step has been made optional. If we don't provide it, Selenium Manager will be turned on.
		// The latest version of Selenium WebDriver which is also compatible with our local browser,
		// will be taken from the net and used for execution

		// If we give the step then, Selenium Manager will be turned off and depends upon the chrome driver path
		// that we give.

		// This was introduced in Selenium 4.6
		// WebDriver driver = new EdgeDriver();
	}

}
