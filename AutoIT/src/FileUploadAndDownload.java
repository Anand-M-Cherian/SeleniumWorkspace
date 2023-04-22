import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FileUploadAndDownload {

    public static void main(String[] args) throws IOException, InterruptedException {

        String downloadPath = System.getProperty("user.dir");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\WebDrivers\\chromedriver.exe");

        // Use chrome options to set preferences like download path, ignore pop ups, SSL certifications.
        // https://chromedriver.chromium.org/capabilities
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);

        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.ilovepdf.com/pdf_to_jpg");
        driver.findElement(By.xpath("//a[@id='pickfiles']")).click();

        // Selenium can only do upto click the 'choose file' button
        // After that we have to create script in AutoIT to navigate to the required path and select the file

        // https://www.autoitscript.com/site/autoit/downloads/
        // Editor: C:\Program Files (x86)\AutoIt3\SciTE
        // Spy Tool: C:\Program Files (x86)\AutoIt3\Au3Info_x64.exe

        // Step 1: Shift focus to file upload window
        // ControlFocus("Open", "", "Edit1") -> ControlID is concatenation of Class and Instance from spy tool
        // text parameter is optional

        // Step 2: Enter complete path into file name edit box
        // ControlSetText("Open", "", "Edit1","C:\Users\Hp\Documents\Backup\Documents\Anand_Resume_09062022.pdf")

        // Step 3: Click Open
        // ControlClick("Open", "", "Button1")

        // Step 4: Save file with .au3 extension

        // Step 5: Right Click file > Compile Script (x86)
        // .exe file will be created

        // Step 6: Invoke the .exe file from JAVA code
        // adding sleep for .exe file to invoke and execute
        Thread.sleep(3000);
        Runtime.getRuntime().exec("C:\\Users\\Hp\\Documents\\Backup\\Documents\\Selenium Webdriver\\Study Material\\AUTO IT\\FileUpload.exe");

        // Do not touch the system, when Auto IT is running. If you do, it might shift focus and get confused.

        //===================Downloading File====================

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@id='processTask']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='pickfiles']")));
        driver.findElement(By.xpath("//a[@id='pickfiles']")).click();

        // Waiting for file to download
        Thread.sleep(5000);

        // while appending additonal path to string path variable, use forward slash
        File file = new File(downloadPath + "/Anand_Resume_page-0001.jpg");
        Assert.assertTrue(file.exists());
        if(file.delete())
            System.out.println("File has been deleted");
    }
}
