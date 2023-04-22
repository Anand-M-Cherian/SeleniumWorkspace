package org.TestUtilities.CucumberStepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ObjectRepository.OrderSummary;
import org.ObjectRepository.PlaceOrder;
import org.ObjectRepository.ProductCatalogue;
import org.ObjectRepository.ShoppingCart;
import org.TestUtilities.BaseTestConfiguration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImplementation extends BaseTestConfiguration {

    public ProductCatalogue productCatalogue;
    public ShoppingCart shoppingCart;
    public PlaceOrder placeOrder;
    public OrderSummary orderSummary;

    // Always better to name the method according to the step definition
    // Tidy Gherkin chrome plugin simplifies the process
    @Given("user opens landing page")
    public void user_opens_landing_page() throws IOException {
        launchApplication();
    }

    // Step definitions with outline data should be implemented as regular expressions "^string$"
    // (.+) can accept anything
    @Given("^user logs in with (.+) and (.+)$")
    public void user_logs_in_with_username_and_password(String username, String password) {
        productCatalogue = landingPage.userLogin(username, password);
    }

    @When("^user adds (.+) into cart$")
    public void user_adds_product_into_cart(String productName) {
        productCatalogue.addProduct(productName);
        shoppingCart = productCatalogue.goToCart();
    }

    @And("^user checks out with the (.+)$")
    public void user_checks_out_with_the_product(String productName) {
        Assert.assertTrue(shoppingCart.isProductAdded(productName));
        placeOrder = shoppingCart.checkOut();
    }

    // Since the below is an extension of 'when', we can use @When also.
    @And("^user clicks on submit order in (.+)$")
    public void user_clicks_on_submit_order_in_country(String countryName) throws InterruptedException {
        placeOrder.selectCountry(countryName);
        orderSummary = placeOrder.placeOrderNow();
    }

    // regular expressions work only for step definitions with outline data driven from examples
    @Then("{string} message is displayed on the confirmation page")
    public void confirmation_message_is_displayed_on_the_confirmation_page (String confirmationMessage) throws InterruptedException {
        Assert.assertTrue(orderSummary.getMessageDisplayed().equalsIgnoreCase(confirmationMessage));
        orderSummary.printOrderNumber();
        orderSummary.signOut();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void invalid_login_message_is_displayed(String expectedErrorMessage) {
        Assert.assertEquals(landingPage.getErrorMessage(), expectedErrorMessage);
    }

    // @After hook executes after every scenario. It is not part of the gherkin code.
    @After
    public void closeDriver() {
        WebDriver driver = (WebDriver) seleniumObjects[0];
        driver.close();
    }

}
