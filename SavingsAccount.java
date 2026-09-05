public class SavingsAccount extends Account {

    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String name, int age,
                           double initialBalance) {

        super(accountNumber, name, age, initialBalance, "Savings");

        this.minBalance = 1000.0;
        this.interestRate = 4.0;
    }

    public void applyInterest()
            throws InvalidAmountException, InactiveAccountException {

        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }
}