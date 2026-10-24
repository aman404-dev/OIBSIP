# ATM Interface – Java

## Project Description
The ATM Interface is a simple console-based application developed using Java. It simulates basic ATM operations and demonstrates object-oriented programming concepts.

## Features
- Login using User ID and PIN
- Maximum of 3 incorrect login attempts
- Check account balance
- Withdraw money
- Deposit money
- Transfer money between demo accounts
- View transaction history
- Store transactions using an ArrayList
- Input validation and insufficient-balance checks

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- ArrayList
- HashMap
- Java Scanner

## Project Structure
```text
Java-Task3-ATMInterface/
├── Main.java
├── ATM.java
├── Account.java
├── Transaction.java
├── Bank.java
└── README.md
```

## Demo Login Details

**Account 1**
- User ID: `user1`
- PIN: `1234`
- Opening Balance: Rs. 5000

**Account 2**
- User ID: `user2`
- PIN: `5678`
- Opening Balance: Rs. 3000

These are sample accounts for testing. This project does not connect to a real bank.

## How to Run

1. Install the Java Development Kit (JDK).
2. Open a terminal or Command Prompt inside the project folder.
3. Compile all Java files:

```bash
javac Main.java ATM.java Account.java Transaction.java Bank.java
```

4. Run the program:

```bash
java Main
```

5. Enter one of the demo User IDs and PINs to log in.

## Concepts Demonstrated
- Classes and Objects
- Encapsulation
- Constructors
- Conditional Statements
- Loops
- Methods
- ArrayList
- HashMap
- Exception Handling
- Date and Time Formatting

## Internship
**Organization:** OASIS INFOBYTE  
**Track:** Java Development  
**Task:** Task 3 – ATM Interface

## Disclaimer
This is an educational project. It is not connected to a real bank or payment system.
