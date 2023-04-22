package com.Basics;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CarLoan {

    // tests execute in alphabetical order in TestNG

    @BeforeClass
    public void BeforeClass() {
        System.out.println("I will run before any tests in the class i am defined in");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("I will run after any tests in the class i am defined in");
    }

    @Test (groups = {"smoke"})
    public void WebLoginCarLoan() {
        System.out.println("Web login car loan");
    }

    @Test
    public void MobileLoginCarLoan() {
        System.out.println("Mobile login car loan");
    }

    @Parameters ({"URL"})
    @Test
    public void APILoginCarLoan(String urlname) {
        System.out.println(urlname);
        System.out.println("API login car loan");
    }
}
