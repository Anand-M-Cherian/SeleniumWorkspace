Feature: Invalid Credentials should be denied
  Invalid Login message should appear when user tries to login with invalid credentials

  Background:
    Given user opens landing page

  @ErrorValidation
  Scenario Outline: Invalid Credentials Login Test
    # The step definitions for repeated steps and reused. No need to code and implement again.
    Given user logs in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username                | password        |
      | betixeh188@lurenwu.com  | blaa            |