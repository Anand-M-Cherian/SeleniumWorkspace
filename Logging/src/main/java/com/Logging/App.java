package com.Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{

    private static Logger log = LogManager.getLogger(App.class);

    public static void main( String[] args ) {

        System.out.println("Root Logger Level: " + LogManager.getRootLogger().getLevel());

        log.debug("I am now debugging");
        log.info("For you information");
        log.error("Not as expected");
        log.fatal("Critical error");

    }

}
