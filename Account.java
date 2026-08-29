public class Account {

    // ===== Constants =====
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    // ===== Fields =====
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    // ===== Constructor =====
    public Account(int accountNumber, String name, int age,
                   double initialBalance, String accountType)
            throws IllegalArgumentException {

        // Validate age
        if (age < MIN_AGE) {
            throw new IllegalArgumentException(
                    "Age must be at least 18 years.");
        }

        // Validate account type
        if (!accountType.equalsIgnoreCase("Savings")
                && !accountType.equalsIgnoreCase("Current")) {
            throw new IllegalArgumentException(
                    "Account type must be Savings or Current.");
        }

        // Store account type in standard format
        if (accountType.equalsIgnoreCase("Savings")) {
            this.accountType = "Savings";
        } else {
            this.accountType = "Current";
        }

        // Validate minimum balance
        double minimumBalance = getMinimumBalance();

        if (initialBalance < minimumBalance) {
            throw new IllegalArgumentException(
                    "Initial balance must be at least "
                    + minimumBalance + " for a "
                    + this.accountType + " account.");
        }

        // Initialize fields
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.status = "Active";
        this.pin = null;
    }

    // ===== Business Methods =====

    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {

        // Check if account is active
        validateActive();

        // Check if amount is positive
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }

        // Add amount
        balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException {

        // Check if account is active
        validateActive();

        // Check if PIN is set
        if (!hasPin()) {
            throw new InvalidPinException(
                    "PIN is not set for this account.");
        }

        // Verify PIN
        if (!verifyPin(pin)) {
            throw new InvalidPinException(
                    "Incorrect PIN.");
        }

        // Check if amount is positive
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }

        // Check sufficient balance
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance.");
        }

        // Check minimum balance
        double newBalance = balance - amount;

        if (newBalance < getMinimumBalance()) {
            throw new MinimumBalanceViolationException(
                    "Withdrawal would violate the minimum balance requirement of "
                    + getMinimumBalance() + ".");
        }

        // Deduct amount
        balance = newBalance;
    }

    // ===== Account Status Management =====

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

    // ===== PIN Management =====

    public void setPin(int pin) throws IllegalArgumentException {

        // Validate 4-digit PIN
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

    // ===== Helper Methods =====

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

    // ===== Getters =====

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