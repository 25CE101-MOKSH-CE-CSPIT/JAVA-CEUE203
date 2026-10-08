# MiniBank Project

MiniBank is a console banking application developed as part of the Object-Oriented Programming (CEUE203) course.

## Features
- **Account Types**: SavingsAccount, CurrentAccount, FixedDepositAccount
- **Encapsulation & Validation**: Regex validator, reflection-based annotations (@Id, @Positive, @MaxLength)
- **Robust Exception Handling**: Custom checked exceptions (InsufficientFundsException, InvalidAmountException, AccountNotFoundException)
- **Thread Safety**: Multithreaded execution, synchronized deposit/withdraw, deadlock-free transfers
- **Concurrency & Batch Processing**: Thread pool using ExecutorService (4 threads), producer-consumer buffer
- **Persistence & Logging**: Object serialization for accounts, append-only transaction logs, NIO report generator
- **Collections**: Generic repository with reflection-based @Id lookup, sorted views
- **Layered Architecture**: BankService wiring repository, validation, and domain logic

## Compilation
```bash
javac --release 17 model/annotation/*.java exception/*.java *.java
```

## Running the Application
Interactive console menu:
```bash
java -jar minibank.jar
```
Or directly via main class:
```bash
java MiniBank
```

## Running Automated Tests
```bash
java -jar minibank.jar test
```
