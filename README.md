# Bank Utility

# Overview

The Banking Account System is designed to simulate common banking transactions including deposits, withdrawals, and transfers between checking and savings accounts. This system is managed through a central Java application, 'BankSystemMain.java', which serves as the entry point for interacting with the banking functionalities.

# Getting Started
## Banking System

To run the application, execute the 'BankSystemMain.java' file. This will initiate the system and present you with the following options in your first iteration:

Today is Day: 1

Please select an option:
1. Deposit to Checking Account
2. Deposit to Savings Account
3. Withdraw from Checking Account
4. Transfer from Checking to Savings
5. Transfer from Savings to Checking
6. Check Balances
7. Check Daily Transaction Limits
8. Check Your Bank Account Numbers
9. Move to Next Day
10. Exit

### Initial Setup

At the start of the first iteration, the day is set to 1, with both the checking and savings accounts beginning with an initial balance of $1000 each.

### Menu Options

- **Option 1: Deposit to Checking Account**
  - Allows deposits up to $5000 per day into the checking account. This can be accomplished in one or more transactions.

- **Option 2: Deposit to Savings Account**
  - Allows deposits up to $5000 per day into the savings account. This can be accomplished in one or more transactions.

- **Option 3: Withdraw from Checking Account**
  - Permits withdrawals up to $500 per day directly from the checking account, in one or more transactions.

- **Option 4: Transfer from Checking to Savings**
  - Enables unlimited transfers from the checking to the savings account, provided there are sufficient funds.

- **Option 5: Transfer from Savings to Checking**
  - Allows for transfers up to a maximum of $100 per day from the savings to the checking account.

- **Option 6: Check Balances**
  - Provides a real-time update on the balances of both the checking and savings accounts.

- **Option 7: Check Daily Transaction Limits**
  - Displays whether the daily transaction limits for deposits, withdrawals, and transfers have been reached.

- **Option 8: Check Your Bank Account Numbers**
  - Displays the account numbers for both the checking and savings accounts.

- **Option 9: Move to Next Day**
  - Advances the system to the next day, resetting all daily transaction limits and preserving the current balances.

- **Option 10: Exit**
  - Exits the application, saving all current data including balances and the date to ensure continuity upon next login.

### System Requirements

- Java 8 or newer.
- JSON-simple library for handling JSON data used for persistence.

### Setup

1. Ensure Java and JSON-simple are correctly installed. Initial JSON file 'banking.json' should contain value currentDay: 1, dailyDeposited: 0.0, dailyWithdrawn: 0.0, dailyTransferred: 0.0. If not in the correct format, feel free to change.
2. Clone the repository or download the source code.
3. Compile and run `BankSystemMain.java` using your Java IDE or from the command line.

## Utility System

To run the application, execute the 'UtilitySystemMain.java' file. This will initiate the system and present you with the following options in your first iteration:

Welcome to Utilities Services System!
Today is Day: 1

Please select an option:
1. Create an Account
2. Login to your Account
3. Exit

### Initial Setup

At the start of the first iteration, the day is set to 1. Inside of the utilities.json file there will be an empty username, password, and acctNum field. There will also be a bill field that holds all of the bills that will be used for us to test the system.

### Menu Options

- **Option 1: Create an Account**
  - Allows deposits up to $5000 per day into the checking account. This can be accomplished in one or more transactions.

- **Option 2: Login to your Account**
  - Brings you to another page where you can choose to login using your username or your account number.
  - **Option 1: Login using username**
    -Provide username and password. You get 3 attempts before the program shuts down. Upon successful sign in you will be brought to the main access page of the utility system.
  - **Option 2: Login using account number**
    -Provide acctNum and password. You get 3 attempts before the program shuts down. Upon successful sign in you will be brought to the main access page of the utility system.

- **Option 10: Exit**
  - Exits the application, saving all current data until the next login.

### System Requirements

- Java 8 or newer.
- JSON-simple library for handling JSON data used for persistence.

### Setup

1. Ensure Java and JSON-simple are correctly installed. Initial JSON file 'banking.json' should contain value currentDay: 1, dailyDeposited: 0.0, dailyWithdrawn: 0.0, dailyTransferred: 0.0. If not in the correct format, feel free to change.
2. Clone the repository or download the source code.
3. Compile and run `BankSystemMain.java` using your Java IDE or from the command line.
