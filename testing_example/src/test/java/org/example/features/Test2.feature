Feature: Data-driven Testing with DataTable

  Scenario: Verify user registration with valid data
    Given the user is on the registration page
    When the user enters the following details and submits
      | First Name | Last Name | Email Address       | Password |
      | John       | Doe      | john.doe@email.com  | password |
      | John2       | Doe2      | john2.doe2@email.com  | password2 |
    Then the user should be registered successfully
    #And This is a new step to test snippets

  Scenario: Verify the ability to open greenKartUrl
    Given Open 'angularpracticeUrl' website
    Then Get page title