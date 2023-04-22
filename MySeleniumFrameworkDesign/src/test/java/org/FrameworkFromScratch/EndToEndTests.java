package org.FrameworkFromScratch;

import org.ObjectRepository.*;
import org.TestUtilities.BaseTestConfiguration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EndToEndTests extends BaseTestConfiguration {

    // ==========Setting Test Data Globally==========
    String countryName = "India";
    String messageToDisplay = "THANKYOU FOR THE ORDER.";

    @Test (dataProvider = "getData")
    public void submitOrderTest(HashMap<String, String> testData) throws InterruptedException {

        // retrieve and set tst data from data provider
        String email = testData.get("email");
        String password = testData.get("password");
        String productName = testData.get("productName");

        // ==========Launch Application and User Login==========
        // the current page last step returns object of next page
        // driving page object creation from within page object classes
        ProductCatalogue productCatalogue = landingPage.userLogin(email, password);

        // ==========Add Product in Product Catalogue Page==========
        productCatalogue.addProduct(productName);
        ShoppingCart shoppingCart = productCatalogue.goToCart();

        // ==========Check for product in Shopping Cart Page==========
        // Page objects should only have web elements and actions done on web elements
        // All assertions and validations should be present within the test case
        Assert.assertTrue(shoppingCart.isProductAdded(productName));
        PlaceOrder placeOrder = shoppingCart.checkOut();

        // ==========Filling info in Place Order Page==========
        placeOrder.selectCountry(countryName);
        OrderSummary orderSummary = placeOrder.placeOrderNow();

        // ==========Order Summary=========
        Assert.assertTrue(orderSummary.getMessageDisplayed().equalsIgnoreCase(messageToDisplay));
        orderSummary.printOrderNumber();
        orderSummary.signOut();
    }

    // separating out submitOrderTest and orderPresentInHistoryTest
    // can help us in pinpointing the failed functionality

    @Test (dependsOnMethods = "submitOrderTest", dataProvider = "getData")
    public void orderPresentInHistoryTest(HashMap<String, String> testData) {

        // retrieve and set tst data from data provider
        String email = testData.get("email");
        String password = testData.get("password");
        String productName = testData.get("productName");

        // ==========Launch Application and User Login==========
        landingPage.userLogin(email, password);
        MyOrders myOrders = landingPage.goToMyOrders();

        // ==========Verify product ordered is present in My Orders Page==========
        Assert.assertTrue(myOrders.isProductOrdered(productName));

        // =========delete all orders from history==========
        myOrders.deleteOrders();
    }

//    @DataProvider
//    public Object[][] getData(){
//
//        // no. of rows = no. of datasets with which u want the TCs to run
//        // no. of columns = no. of values for each dataset
//
//        return new Object[][] { {"betixeh188@lurenwu.com", "Testitout123!@", "zara coat 3"},
//                                {"sinefos204@keyido.com", "Heythere123!@", "ADIDAS ORIGINAL"}};
//    }

    // DataProvider with HashMap concept
    @DataProvider
    public Object[][] getData() throws IOException {

        // no. of rows = no. of datasets with which u want the TCs to run
        // no. of columns = no. of values for each dataset

        List<HashMap <String, String>> testData = getTestDataFromJSON(System.getProperty("user.dir") + "/src/test/resources/EndToEndTestData.json");
        return new Object[][] { {testData.get(0)}, {testData.get(1)}};
    }

}
