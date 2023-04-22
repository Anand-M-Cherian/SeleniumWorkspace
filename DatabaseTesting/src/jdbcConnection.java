import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.*;

public class jdbcConnection {

    // Download my swl connector jar - https://dev.mysql.com/downloads/connector/j/?os=26
    // add jar through external libraries - File > Project Structure > Module > Dependencies > Jar / Directories

    public static void main(String[] args) throws SQLException {

        String host = "localhost";
        String port = "3306";
        String database = "uat_db";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        // Knowing the destination
        Connection dbCon = DriverManager.getConnection(url, "root", "LivingOnHopesAndDreams123!@");

        // Building a road
        Statement dbStatement = dbCon.createStatement();

        ResultSet resultSet = dbStatement.executeQuery("Select * from EMPLOYEE_INFO where EMPLOYEE_NAME = 'Lorenzo'");

        String employeeName = "";
        int employeeId = 0;

        // executeQuery will bring the result to first index. So we have to move pointer from base index to first index
        int i = 1;
        while (resultSet.next()) {
            // for varchar columns, choose getString
            employeeName = resultSet.getString("EMPLOYEE_NAME");
            employeeId = resultSet.getInt("EMPLOYEE_ID");
            System.out.println(i + ". " + employeeName + " : " + employeeId);
            i++;
        }

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://login.salesforce.com/?locale=in");

        driver.findElement(By.id("username")).sendKeys(employeeName);
        driver.findElement(By.id("password")).sendKeys(String.valueOf(employeeId));
    }
}