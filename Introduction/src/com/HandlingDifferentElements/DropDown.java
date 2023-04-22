package com.HandlingDifferentElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class DropDown {
    public static void main(String[] args) {

        // Select HTML tag -> Static Drop Down

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anand\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Handling currency static drop down (Select tag)
        WebElement currencyDropDown = driver.findElement(By.xpath("//select[contains(@name,'Currency')]"));
        Select currencyDropDownSelect = new Select(currencyDropDown);
        currencyDropDownSelect.selectByIndex(3);
        System.out.println(currencyDropDownSelect.getFirstSelectedOption().getText());
        currencyDropDownSelect.selectByVisibleText("AED");
        System.out.println(currencyDropDownSelect.getFirstSelectedOption().getText());
        currencyDropDownSelect.selectByValue("INR");
        System.out.println(currencyDropDownSelect.getFirstSelectedOption().getText());

        // Handling passengers dropdown
        // Whenever 'id' attribute is available, use it as the locator
        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
        driver.findElement(By.id("divpaxinfo")).click();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        for (int i = 1; i <= 4; i++){
            driver.findElement(By.id("hrefIncAdt")).click();
        }
        driver.findElement(By.id("btnclosepaxoption")).click();
        Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

        // Handling from and to dropdowns (Dynamic)
        // Options are loaded according to user actions and the elements are visible on the page
        // only after clicking the dropdown
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        // Chennai element is present in both From and To dropdowns. So use indexing.
        // **********Client does not like indexing and denies your pull request**********
        // driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
        // Parent Child relationship -> parentXpath childXpath
        // The parent need not be the immediate one
        driver.findElement(By.xpath("//div[@class='right1'] //a[@value='MAA']")).click();

        // Handling country dropdown (Auto Suggestive)
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("Thread.sleep wil cause an InterruptedException, hence skipping it...");
        }
        // Figure out the generator that returns all the auto suggested web elements
        List<WebElement> countries = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement country : countries){
            if (country.getText().equalsIgnoreCase("India")){
                country.click();
                break;
            }
        }
    }
}
