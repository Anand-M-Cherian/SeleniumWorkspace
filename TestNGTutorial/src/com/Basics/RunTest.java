package com.Basics;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RunTest {

    // TestNG compiler requires tests coded as methods. The method name becomes the test name.
    // All results are neatly documented

    // @Test annotation followed by the method
    // Single class can have multiple tests

    @Parameters({"URL", "key"})
    @Test (groups = {"smoke"})
    public void Demo(String urlname, String key) {
        System.out.println(urlname);
        System.out.println(key);
        System.out.println("My First Test");
    }

    @Test (enabled = false)
    public void SecondTest() {
        System.out.println("Bye");
    }
}
