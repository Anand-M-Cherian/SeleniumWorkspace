package org.PageObjectDemoProject;

import org.ObjectRepository.HomePage;
import org.ObjectRepository.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void Login() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");

        // Login page automation using objects from LoginPage central repository class
        // implemented with PageObjectPattern style
        LoginPage loginPage = new LoginPage(driver);
        loginPage.username().sendKeys("Hello");
        loginPage.password().sendKeys("123");
        loginPage.submitButton().click();
        loginPage.homePage().click();

        // Home page object repository implemented with PageObjectFactory style
        HomePage homePage = new HomePage(driver);
        homePage.searchBox().sendKeys("Motorcyles");
        homePage.searchButton().click();

        // driver.close();

    }
}
