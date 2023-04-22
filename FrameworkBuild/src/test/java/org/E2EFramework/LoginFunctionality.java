package org.E2EFramework;

import org.PageObjects.HomePage;
import org.PageObjects.LoginPage;
import org.Utilities.Base;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginFunctionality extends Base {

    @Test
    public void userLogin() throws IOException {

        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/");

        HomePage homePage = new HomePage(driver);
        homePage.getLoginPageButton().click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.getEmailTextBox().sendKeys("admin");
        loginPage.getPasswordTextBox().sendKeys("password");
        loginPage.getSubmitButton().click();

    }
}
