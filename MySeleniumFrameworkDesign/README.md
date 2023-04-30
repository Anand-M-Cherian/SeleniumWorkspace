# My Test Framework Design

Web Application under test: 
[https://rahulshettyacademy.com/client/auth/login]

## Framework Architecture:
### Locators for the webpage
1. PageObjectParent: Contains all the reusable code that can be used for all the webpages like the header and footer elements of the web application which does not change from page to page
2. Child Page Objects (LandingPage, ProductCatalogue, ShoppingCart, OrderSummary, PlaceOrder, MyOrders): Inherit the reusable code from PageObjectParent. Also contains locators specific to that webpage.

### Test Cases
1. BaseTestConfiguration: Contains all the reusable code like: 
   - initializing and closing driver
   - @BeforeMethod and @AfterMethod TestNG blocks
   - Test data and configurations:
     - Uses a JSON parser to get the test login credentials
     - .properties file provides info on which browser to run the test exectuion
2. EndToEndTest: Inherits from BaseTestConfiguration. Complete flow of a user logging in, adding a product to cart, viewing cart items, checking out, placing order and validating that order was placed
3. ErrorValidationTest: Inherits from BaseTestConfiguration. Contains invalid scenarios

## Reporting and Additonal Functionality:
1. Listeners: 
   - Acts as middleware between the test case and Extent Reports
   - Every test executes through listeners, creates an entry in Extent HTML reports and after execution reports back as pass / fail.
   - On Test Failure method captures screenshot
   - RetryListener executes the flaky test again to see if it was any performance / server issue
   - ThreadLocal is used to implement parallel execution
2. XML test runners:
   - POM.xml file decides which TestNG XML file to run based on the profile that is selected. Eg: Regression, Smoke etc.
   - TestNG XML files contains info on which test cases to include and how many tests should be run in parallel

## Logical Flow:
1. MAVEN command triggered from the terminal will contain which profile of test from the POM.xml should be run.
2. The corresponding test NG XML will be triggered.
3. The test cases mentioned in the test NG XML will be triggered.
4. Page Objects and PageObjectParent will be accessed while executing test cases.
5. Test Data and Test Congfigurations will be taken from JSON and .properties file
6. Reporting and screenshot is taken care by Listeners and Extent HTML reports

**NOTE: ** Cucumber wrapper is integrated to drive the test through BDD framework. Feature files containing the step defenitions map the functionality to test execution script.
