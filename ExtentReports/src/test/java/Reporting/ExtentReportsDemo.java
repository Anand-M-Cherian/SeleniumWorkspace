package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportsDemo {

    // ExtentReports object declared globally so that every method (Test Case) has access
    ExtentReports extent;

    @BeforeTest
    public void initialize() {
        // ExtentSparkReporter to set all configurations of reports like path where reports generate etc...
        // user.dir gets path up to project level
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("My First Extent Report");
        reporter.config().setDocumentTitle("Demo Extent Report");

        // ExtentReports to drive all our test execution reports
        // We have to attach our ExtentSparkReporter object so that main class gets
        // knowledge of helper class.
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Anand M Cherian");
    }

    @Test
    public void openBrowser() {

        // We have to tell ExtentReports that a Test Case is being executed
        // Mandatory step for all test case

        // One object created for complete test method. Not mandatory to catch it.
        // But if we catch it, we can tweak our reports further like forcibly fail it etc...
        ExtentTest test = extent.createTest("Open Browser");
        // "test" object responsible for listening and monitoring our test case and reports
        // back to ExtentReports object. "test" object can be used to attach screenshots,
        // fail test cases etc...
        // createTest object will automatically report back to ExtentReport whether test has failed or passed
        // we need not do any additional steps

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        driver.close();

        // Explicitly fail test case through createTest object. TestNG output will show as passed. But
        // index.html will show failed
        test.fail("Failed on purpose");

        // After execution of ALL Test Cases, we have to tell ExtentReports that execution is over
        // and no need to monitor anymore
        extent.flush();
        // This will also update the results for the executed test cases
    }
}
