Feature: Print an Account with no transactions
  A user wants to print an account with no transactions
  Scenario: Print an Account with no transactions
    Given I have an empty account
    When I print the account statement
    Then I see an empty account statement