package org.TestUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ExtentReportsAndListenersAndRetry extends BaseTestConfiguration implements ITestListener, IRetryAnalyzer {

    // We should tell TestNG that we are using Listeners, please see the TestNG XML

    // The original tutorial video has made a different class for extent report initialization
    // and calls that STATIC method here to get the ExtentReports object

    // Concurrency Issue: While running tests in parallel, multiple test cases are trying to access
    // ExtentTest test variable. Hence, when TC actually fails and executes test.fail(), it might actually be
    // failing some other TC ExtentTest test variable. Hence, we use ThreadLocal. Any parameterized type that
    // is pushed to ThreadLocal is made thread safe, ie....any objection creation of that type is given a
    // unique thread.

    ExtentReports extent = initializeExtentReports();
    ExtentTest test;

    // Thread Safe -> each instance has its own thread, so that it does not interrupt other instances
    ThreadLocal<ExtentTest> tlExtentReport = new ThreadLocal<ExtentTest>();

    int numberOfRetries = 0;
    int retryLimit = 1;

    public ExtentReports initializeExtentReports() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("FrameworkDemoTestResults");
        reporter.config().setDocumentTitle("Selenium Framework Design");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Anand M Cherian");

        // All metadata has been added to the ExtentReports object.

        return extent;
    }

    // Since we have used Listeners at the time following things happen for each test case:
    //      1. On start
    //      2. On Success
    //      3. On Failure
    //      4. On skip
    // Execution will first come here and then only shall it proceed

    // ITestResult variable holds information about the test case that is currently being executed

    // ========================Listener Methods========================
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());

        // Push the test into ThreadLocal. A unique thread ID is assigned to whichever TC it was that instantiated
        // the test object. Hence, the test object is mapped to the corresponding TC using ThreadLocal
        tlExtentReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        // You don't have to explicitly pass a test case, just need to log
        // By default if execution does not go through OnTestFailure, TestNG treats it as passed

        // use the mapped test object to the TC using ThreadLocal get()
        tlExtentReport.get().log(Status.PASS, "Working as expected");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // We have to explicitly tell that we are failing the test case
        // Throwable will print the error message in the HTML report
        tlExtentReport.get().fail(result.getThrowable());

        // Take SS on test failure
        // Name the SS using the MethodName
        // Takes SS and stores it with test case name in local system
        // Also need to pass the driver so that takeScreenshot() gets that knowledge
        Object[] seleniumObjects = null;
        try {
            // we can only get the fields from a class and not the method, hence getRealClass()
            // fields are associated at class level

            // getTestClass() -> refers TestNG XML and gets the test class name
            // getRealClass() -> goes to actual class
            // getField() -> returns corresponding field

            // getFields is fetching fields from BaseTestConfiguration as errorValidationTest / EndToEndTests
            // is inheriting the same. Hence, we are dealing with seleniumObjects[]
            // so handling like an object array
            seleniumObjects = (Object[]) result.getTestClass().getRealClass().getField("seleniumObjects").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String ssPath;
        try {
            // second argument requires WebDriver which is at second position of seleniumObjects[]
            ssPath = takeScreenshot(result.getMethod().getMethodName(), (WebDriver) seleniumObjects[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // The second argument takes the SS from first and adds it to HTML report
        // with name as that of test case
        tlExtentReport.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // ========================Retry Methods========================
    // If the TC fails, after completing the Listener on test failure method execution comes here.
    // If the below method returns true, it means we are telling to retry
    // We should individually give retry attributes to TC that have chance of failure (FLAKY). Please check in
    // corresponding TC classes
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (numberOfRetries<retryLimit) {
            numberOfRetries++;
            return true;
        }
        return false;
    }
}
