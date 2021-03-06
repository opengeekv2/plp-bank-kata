Outside-In TDD with Acceptance Tests
====================================

### TODO

- [X] Print header on StatementPrinter
- [X] Implement withdraw -> transaction concept
- [ ] Test PrintStatement with Deposit and Withdraw mocks
- [ ] Test Bank Account with Deposit and Withdraw mocks
- [ ] Test applyTransaction in Deposit and Withdraw
- [ ] Transaction repository (obtains dates form calendar)
  - [ ] Migrate the responsibility to keep balances on Transaction repository

### Objective

Learn and practice the double loop of TDD
Test application from outside, identifying side effects

### Problem description - Bank kata

Create a simple bank application with the following features:

     - Deposit into Account
     - Withdraw from an Account
     - Print a bank statement to the console

## Acceptance criteria

Statement should have transactions in the following format:

```
  DATE       | AMOUNT  | BALANCE
  10/04/2014 | 500.00  | 1400.00
  02/04/2014 | -100.00 | 900.00
  01/04/2014 | 1000.00 | 1000.00
```

## Starting point and constraints

Start with a class the following structure:

    public class Account {

        public void deposit(int amount);

        public void withdrawal(int amount);

        public void printStatement();

    }

You are not allowed to add any other public method to this class.

**NOTE:** In order to keep the exercise simple, use _int_ for money and _String_ for dates.
Also, you can ignore the formatting of the statement (spaces between pipes and words, etc).