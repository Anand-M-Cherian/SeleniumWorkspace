package com.Logging.Alpha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class AlphaDemo {

    // Instantiate a static Logger object using LogManager's getLogger method
    // which requires the full path to the class in which Logger is instantiated
    private static Logger log = LogManager.getLogger(AlphaDemo.class.getName());

    public static void main(String[] args) throws IOException {

        // default configuration file only logs errors into the console
        // debug and info requires configuration file setup
        // debug and info are like routine messages to the user

        // To use custom configuration file add the file path into the build path of the project

        /*Use log. Error() to log when elements are not displayed in the page or if any validations fail
        Use Log. Debug()
        When each Selenium action is performed like click, SendKeys, getText()
        Use log.info()
        When operation is successfully completed ex: After loading page, or after any successful validations
        It’s just counterpart to log. Error()*/

        // log4j2.xml working correctly in eclipse but not in intellij for the first time
        // once set up in eclipse then no issues in intellij....

        System.out.println("Root Logger Level: " + LogManager.getRootLogger().getLevel());

        log.debug("I am now debugging");
        log.info("For you information");
        log.error("Not as expected");
        log.fatal("Critical error");

    }
}
