package com.gdb.domain;

import com.gdb.exceptions.MinimumBalanceViolationException;

public class SavingsAccount extends AbstractAccount {

    private double minBalance;
    private double interestRate;
    private int tenureYears;

    public SavingsAccount(String accountNumber, String name, int age,
                          double balance, String status, String pin,
                          int tenureYears) {
        super(accountNumber, name, age, balance, "SAVINGS", status, pin);

        this.tenureYears = tenureYears;

        this.minBalance =
                AccountRulesEngine.getSavingsMinBalance(tenureYears);

        this.interestRate =
                AccountRulesEngine.getSavingsInterestRate(tenureYears);
    }

    @Override
    public void processDebit(double amount)
            throws MinimumBalanceViolationException {

        if (balance - amount < minBalance) {
            throw new MinimumBalanceViolationException(
                    "Cannot breach minimum balance of Rs " + minBalance);
        }

        balance -= amount;
    }

    public void applyInterest() {
        balance += balance * interestRate / 100;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTenureYears() {
        return tenureYears;
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: Rs " + balance);
        System.out.println("Minimum Balance: Rs " + minBalance);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Tenure: " + tenureYears + " years");
        System.out.println("Status: " + status);
    }
}