package com.Basics;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BikeLoan {

    // Before method - clearing cookies and caches etc.
    // dependOn helper attribute
    // timeOut helper attribute

    // Data Provider Annotations for running test cases with multiple data sets

    @Test
    public void WebLoginBikeLoan() {
        System.out.println("Web login Bike loan");
    }

    @Test (timeOut = 4000)
    public void MobileLoginBikeLoan() {
        System.out.println("Mobile login Bike loan");
    }

    @Test (dependsOnMethods = {"MobileLoginBikeLoan"})
    public void APILoginBikeLoan() {
        System.out.println("API log in Bike loan");
    }

    @Test
    public void APILogoutBikeLoan() {
        System.out.println("API log out Bike loan");
        Assert.assertTrue(false);
    }

    @Test (groups = {"smoke"})
    public void APISignInBikeLoan() {
        System.out.println("API sign in Bike loan");
    }

    @Test (dataProvider = "getData")
    public void APISignOutBikeLoan(String user, String pass) {
        System.out.println(user);
        System.out.println(pass);
        System.out.println("API sign out Bike loan");
    }

    @BeforeMethod
    public void beforeEvery() {
        System.out.println("I run before every method of the class I am defined in");
    }

    @AfterMethod
    public void afterEvery() {
        System.out.println("I run after every method of the class I am defined in");
    }

    @DataProvider
    public Object[][] getData(){

        // no. of rows = no. of datasets with which u want the TCs to run
        // no. of columns = no. of values for each dataset

        Object[][] data = new Object[3][2];

        data[0][0] = "user1";
        data[0][1] = "pass1";

        data[1][0] = "user2";
        data[1][1] = "pass2";

        data[2][0] = "user3";
        data[2][1] = "pass3";
        return data;
    }
}
