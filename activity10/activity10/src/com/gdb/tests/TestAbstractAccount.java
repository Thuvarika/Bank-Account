package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // Step 1 - Create an array/portfolio of AbstractAccount objects
        AbstractAccount savings = new SavingsAccount(
                "S001",
                "Student",
                20,
                10000,
                "ACTIVE",
                "1234",
                5000,
                4.0
        );

        AbstractAccount current = new CurrentAccount(
                "C001",
                "Student",
                20,
                5000,
                "ACTIVE",
                "1234",
                25000
        );

        AbstractAccount salary = new SalaryAccount(
                "SAL001",
                "Student",
                20,
                15000,
                "ACTIVE",
                "1234",
                "ABC Company"
        );

        AbstractAccount[] accounts = {
                savings,
                current,
                salary
        };

        System.out.println("[PASS] Account portfolio created");
        System.out.println("Total accounts: " + accounts.length);

        // Step 2 - Secure fund transfer from Savings to Current
        System.out.println("\n=== Fund Transfer: Savings -> Current ===");

        double transferAmount = 2000;

        try {
            savings.withdraw(transferAmount, "1234");
            current.deposit(transferAmount);

            System.out.println("[PASS] Transfer successful");
            System.out.println("Savings Balance: Rs " + savings.getBalance());
            System.out.println("Current Balance: Rs " + current.getBalance());

        } catch (AccountException e) {
            System.out.println("[FAIL] Transfer failed: " + e.getMessage());
        }

        // Step 3 - Failed transfer with wrong PIN
        System.out.println("\n=== Failed Transfer: Wrong PIN ===");

        double savingsBefore = savings.getBalance();
        double currentBefore = current.getBalance();

        try {
            savings.withdraw(1000, "9999");
            current.deposit(1000);

            System.out.println("[FAIL] Wrong PIN was accepted");

        } catch (InvalidPinException e) {
            System.out.println("[PASS] Wrong PIN transfer rejected");

            System.out.println("Savings Balance unchanged: "
                    + (savings.getBalance() == savingsBefore));

            System.out.println("Current Balance unchanged: "
                    + (current.getBalance() == currentBefore));

        } catch (AccountException e) {
            System.out.println("[PASS] Transfer rejected: " + e.getMessage());
        }

        // Step 4 - Process monthly cycle
        System.out.println("\n=== Monthly Cycle ===");

        for (AbstractAccount account : accounts) {

            if (account instanceof SavingsAccount) {
                SavingsAccount savingsAccount =
                        (SavingsAccount) account;

                savingsAccount.applyInterest();

                System.out.println(
                        "[PASS] Savings interest applied | Balance: Rs "
                                + savingsAccount.getBalance()
                );
            }

            if (account instanceof SalaryAccount) {
                SalaryAccount salaryAccount =
                        (SalaryAccount) account;

                salaryAccount.incrementInactiveMonths();

                System.out.println(
                        "[PASS] Salary inactive months checked | Months: "
                                + salaryAccount.getInactiveMonths()
                );
            }
        }

        System.out.println(
                "\n=== Complete the test suite and verify all banking operations ==="
        );
    }
}