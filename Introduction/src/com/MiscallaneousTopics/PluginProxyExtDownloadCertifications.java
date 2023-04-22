package com.MiscallaneousTopics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class PluginProxyExtDownloadCertifications {

    public static void main(String[] args) {

        System.setProperty("webdriver.edge.driver", "C:\\Users\\anand\\WebDrivers\\msedgedriver.exe");

        // Browser should bypass privacy error page caused by HTTPS certifications
        EdgeOptions edgeSettings = new EdgeOptions();
        edgeSettings.setAcceptInsecureCerts(true);

        // **********Enable extensions**********
        // edgeSettings.addExtensions("path to extension");

        // **********Set up proxy**********
        // Proxy proxy = new Proxy();
        // proxy.setHttpProxy("myhttpproxy:3337");
        // edgeSettings.setCapability("proxy", proxy);

        // **********Block Pop Ups**********
        // edgeSettings.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

        // **********Setting Download Directory**********
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "C:\\Users\\anand\\Documents\\Download backup");
        edgeSettings.setExperimentalOption("prefs", prefs);

        WebDriver driver = new EdgeDriver(edgeSettings);
        driver.get("https://expired.badssl.com/");

        System.out.println(driver.getTitle());
    }
}
