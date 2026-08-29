public class AccountEnhanced {

    // ===== Constants =====
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

    public AccountEnhanced(int accountNumber, String name, int age,
                           double initialBalance, String accountType) {

        this.accountNumber = accountNumber;
        this.name = name;

        // Minimum age
        this.age = age < MIN_AGE ? MIN_AGE : age;

        // Account type validation
        if (accountType.equals("Savings") || accountType.equals("Current")) {
            this.accountType = accountType;
        } else {
            this.accountType = "Savings";
        }

        // Minimum balance
        double minimum;

        if (this.accountType.equals("Savings")) {
            minimum = MIN_BALANCE_SAVINGS;
        } else {
            minimum = MIN_BALANCE_CURRENT;
        }

        this.balance = initialBalance < minimum ? minimum : initialBalance;

        this.status = "Active";
        this.pin = null;
    }

    public boolean deposit(double amount) {

        if (!status.equals("Active") || amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    public boolean withdraw(double amount, int pin) {
    if (!status.equals("Active")) {
        return false;
    }

    if (!verifyPin(pin) || amount <= 0) {
        return false;
    }

    double minimum = 0;

    if (accountType.equals("Savings")) {
        minimum = MIN_BALANCE_SAVINGS;
    }

    if (balance - amount < minimum) {
        return false;
    }

    balance -= amount;
    return true;
}

    public boolean closeAccount() {

        if (status.equals("Inactive")) {
            return false;
        }

        status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {

        if (status.equals("Active")) {
            return false;
        }

        status = "Active";
        return true;
    }

    public boolean setPin(int pin) {

        if (pin >= MIN_PIN && pin <= MAX_PIN) {
            this.pin = pin;
            return true;
        }

        return false;
    }

    public boolean verifyPin(int pin) {
        return this.pin != null && this.pin == pin;
    }

    public boolean hasPin() {
        return pin != null;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age < MIN_AGE ? MIN_AGE : age;
    }
}