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

  Scenario: Print an Account with two deposits
    Given the date is "2022-02-24"
    And I have an empty account
    When I do a deposit of "10000"
    And the date becomes "2022-02-25"
    And I do a deposit of "20000"
    And I print the account statement
    Then I see the header for the account statement
    And I see an account statement for "2022-02-24" with a deposit of "10000" and a balance of "10000"
    And I see an account statement for "2022-02-25" with a deposit of "20000" and a balance of "30000"

  Scenario Outline: Print a Account with a withdrawal
    Given the date is "<date>"
    Given I have an empty account
    When I do a withdrawal of "<amount>"
    When I print the account statement
    Then I see an account statement for "<date>" with a withdrawal of "<amount>" and a balance of "<balance>"

    Examples:
      | amount | date       | balance |
      | 500    | 2022-02-24 | -500    |

  Scenario: Print an Account with two withdrawals
    Given the date is "2022-02-24"
    And I have an empty account
    When I do a withdrawal of "10000"
    And the date becomes "2022-02-25"
    And I do a withdrawal of "20000"
    And I print the account statement
    Then I see the header for the account statement
    And I see an account statement for "2022-02-24" with a withdrawal of "10000" and a balance of "-10000"
    And I see an account statement for "2022-02-25" with a withdrawal of "20000" and a balance of "-30000"
