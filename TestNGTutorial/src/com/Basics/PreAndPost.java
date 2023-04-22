package com.Basics;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class PreAndPost {

    // Before Test - Cleaning data from database for fresh start, setting up server for Appium etc
    // After Test - Closing streams and objects, delete cookies etc
    // Before Suite - Setting environment and global variables
    // is not counted as test case

    // beforeclass, afterclass, beforemethod, aftermethod = class level scope
    // beforetest, aftertest, beforesuite, aftersuite = testng XML level

    @BeforeTest
    public void PreTest() {
        System.out.println("I will run  before test folder");
    }

    @AfterTest
    public void PostTest() {
        System.out.println("I will run after test folder");
    }

    @BeforeSuite
    public void PreSuite() {
        System.out.println("I will execute before whole suite");
    }

    @AfterSuite
    public void PostSuite() {
        System.out.println("I will execute after entire suite");
    }

}
