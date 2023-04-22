package com.Logging.Beta;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class BetaDemo {

    // Instantiate a static Logger object using LogManager's getLogger method
    // which requires the full path to the class in which Logger is instantiated
    private static Logger log = LogManager.getLogger(BetaDemo.class.getName());

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

        System.out.println("Root Logger Level: " + LogManager.getRootLogger().getLevel());

        log.debug("Clicking the button");	//what is happening now??
        log.info("Button has been clicked");	// successful activity, opposite of error
        log.error("Button could not be clicked");	
        log.fatal("Button not found");

    }
}
