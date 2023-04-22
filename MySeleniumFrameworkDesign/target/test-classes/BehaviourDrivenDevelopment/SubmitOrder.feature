# Make sure to download Gherkin and Cucumber for Java plugins provided bny JetBrains

  # Feature is like a test suite. Similar to a Java Class that holds many @Test methods of TestNG
  Feature: Submit an order
    The user should be able to submit an order from the E-commerce website

    # Background executes before all scenarios. Similar to @BeforeTest from TestNG
    Background:
      # No outline data nor static date in the below line
      Given user opens landing page

    # Scenario is like a test case. Similar to @Test from TestNG
    # Use tags to run selective tests
    @Default
    Scenario Outline: Positive test of submitting order
      # OUTLINE data present in below lines
      Given user logs in with <username> and <password>
      When user adds <productName> into cart
      And user checks out with the <productName>
      And user clicks on submit order in <countryName>
      # STATIC data present in below line
      Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

      Examples:
      | username                | password        | productName   | countryName |
      | betixeh188@lurenwu.com  | Testitout123!@  | zara coat 3   | India       |