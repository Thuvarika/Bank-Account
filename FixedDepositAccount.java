public class FixedDepositAccount extends Account {

    private int tenureMonths;
    private double interestRate;

    public FixedDepositAccount(int accountNumber, String name, int age,
                               double initialBalance) {

        super(accountNumber, name, age, initialBalance,
              "Fixed_Deposit");

        this.tenureMonths = 12;
        this.interestRate = 6.5;
    }

    public double calculateMaturityAmount() {

        double years = tenureMonths / 12.0;

        return getBalance() *
               Math.pow(1 + interestRate / 100, years);
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }
}