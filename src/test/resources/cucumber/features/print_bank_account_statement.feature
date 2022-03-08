Feature: Print an account statement
  A user wants to print an account statement

  Scenario: Print an Account with no transactions
    Given I have an empty account
    When I print the account statement
    Then I see an empty account statement

  Scenario Outline: Print an Account with a deposit
    Given the date is "<date>"
    Given I have an empty account
    When I do a deposit of "<amount>"
    When I print the account statement
    Then I see an account statement for "<date>" with a deposit of "<amount>" and a balance of "<balance>"

  Examples:
    | amount | date       | balance |
    | 10000  | 2022-02-24 | 10000   |