public class Account {
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public Account(int accountNumber, String name, int age,
                   double initialBalance, String accountType)
            throws IllegalArgumentException {

        if (age < MIN_AGE) {
            throw new IllegalArgumentException(
                    "Age must be at least 18 years.");
        }

        if (!accountType.equalsIgnoreCase("Savings")
                && !accountType.equalsIgnoreCase("Current")) {
            throw new IllegalArgumentException(
                    "Account type must be Savings or Current.");
        }

        if (accountType.equalsIgnoreCase("Savings")) {
            this.accountType = "Savings";
        } else {
            this.accountType = "Current";
        }

        double minimumBalance = getMinimumBalance();

        if (initialBalance < minimumBalance) {
            throw new IllegalArgumentException(
                    "Initial balance must be at least "
                    + minimumBalance + " for a "
                    + this.accountType + " account.");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.status = "Active";
        this.pin = null;
    }


    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {

        validateActive();

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }

        balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException {

        validateActive();

        if (!hasPin()) {
            throw new InvalidPinException(
                    "PIN is not set for this account.");
        }

        if (!verifyPin(pin)) {
            throw new InvalidPinException(
                    "Incorrect PIN.");
        }

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance.");
        }

        double newBalance = balance - amount;

        if (newBalance < getMinimumBalance()) {
            throw new MinimumBalanceViolationException(
                    "Withdrawal would violate the minimum balance requirement of "
                    + getMinimumBalance() + ".");
        }

        balance = newBalance;
    }


    public void closeAccount() throws IllegalStateException {

        if (status.equals("Inactive")) {
            throw new IllegalStateException(
                    "Account is already closed.");
        }

        status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {

        if (status.equals("Active")) {
            throw new IllegalStateException(
                    "Account is already active.");
        }

        status = "Active";
    }


    public void setPin(int pin) throws IllegalArgumentException {

        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException(
                    "PIN must be a 4-digit number.");
        }

        this.pin = pin;
    }

    public boolean verifyPin(int pin) {

        return this.pin != null && this.pin == pin;
    }

    public boolean hasPin() {

        return pin != null;
    }


    private double getMinimumBalance() {

        if (accountType.equalsIgnoreCase("Savings")) {
            return MIN_BALANCE_SAVINGS;
        }

        return MIN_BALANCE_CURRENT;
    }

    private void validateActive()
            throws InactiveAccountException {

        if (status.equals("Inactive")) {
            throw new InactiveAccountException(
                    "Account is inactive.");
        }
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getStatus() {
        return status;
    }

    public Integer getPin() {
        return pin;
    }
}