package org.FrameworkFromScratch;

import org.ObjectRepository.ProductCatalogue;
import org.ObjectRepository.ShoppingCart;
import org.TestUtilities.BaseTestConfiguration;
import org.TestUtilities.ExtentReportsAndListenersAndRetry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTestConfiguration {

    // If we have groups of test then before and after method must be set to always run

    @Test (groups = {"Error Handling"}, retryAnalyzer = ExtentReportsAndListenersAndRetry.class)
    public void invalidLoginTest() {

        // ==========Setting Test Data==========
        // Looking for different string so that test case fails on purpose.
        String expectedErrorMessage = "Incorrect email or password.";

        // ==========Launch Application and Invalid User Login==========
        landingPage.userLogin("betixeh188@lurenwu.com", "Blaaa");
        Assert.assertEquals(landingPage.getErrorMessage(), expectedErrorMessage);
    }

    @Test
    public void invalidProductTest() {

        // ==========Setting Test Data==========
        String productName = "zara coat 3";

        // ==========Launch Application and User Login==========
        ProductCatalogue productCatalogue = landingPage.userLogin("pavihij288@dnitem.com", "ConfirmOut123!@");

        // ==========Add Product in Product Catalogue Page==========
        productCatalogue.addProduct(productName);
        ShoppingCart shoppingCart = productCatalogue.goToCart();

        // ==========Check for product in Shopping Cart Page==========
        Assert.assertFalse(shoppingCart.isProductAdded("zara coat 33"));
    }

}
