package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestInterfaceFactory {
    public static void main(String[] args) {
        System.out.println("=== Activity 12: Factory-Driven System Suite ===");

        // Step 1 - Create accounts exclusively through AccountFactory
        IAccount savings = AccountFactory.createAccount(
                "SAVINGS",
                "SAV1001",
                "Rajesh Sharma",
                28,
                5000.0,
                "ACTIVE",
                "1234"
        );

        IAccount current = AccountFactory.createAccount(
                "CURRENT",
                "CUR1001",
                "Priya Patel",
                34,
                10000.0,
                "ACTIVE",
                "5678"
        );

        IAccount fixedDeposit = AccountFactory.createAccount(
                "FIXED_DEPOSIT",
                "FD1001",
                "Amit Kumar",
                45,
                50000.0,
                "ACTIVE",
                "1111"
        );

        System.out.println("[PASS] Accounts created through AccountFactory");

        // Step 2 - Deposits and withdrawals through IAccount
        System.out.println("\n=== Deposits and Withdrawals ===");

        try {
            savings.deposit(1000);
            System.out.println(
                    "[PASS] Savings deposit | Balance: Rs "
                    + savings.getBalance()
            );

            savings.withdraw(500, "1234");
            System.out.println(
                    "[PASS] Savings withdrawal | Balance: Rs "
                    + savings.getBalance()
            );

            current.deposit(2000);
            System.out.println(
                    "[PASS] Current deposit | Balance: Rs "
                    + current.getBalance()
            );

            current.withdraw(1000, "5678");
            System.out.println(
                    "[PASS] Current withdrawal | Balance: Rs "
                    + current.getBalance()
            );

        } catch (AccountException e) {
            System.out.println("[FAIL] Transaction failed: " + e.getMessage());
        }

        // Step 3 - Savings minimum balance rule
        System.out.println("\n=== Savings Minimum Balance Test ===");

        try {
            savings.withdraw(5000, "1234");
            System.out.println("[FAIL] Savings minimum balance rule not enforced");

        } catch (MinimumBalanceViolationException e) {
            System.out.println(
                    "[PASS] Savings minimum balance rule enforced"
            );

        } catch (AccountException e) {
            System.out.println(
                    "[PASS] Savings withdrawal rejected: "
                    + e.getMessage()
            );
        }

        // Step 4 - Current overdraft limit
        System.out.println("\n=== Current Overdraft Test ===");

        try {
            current.withdraw(40000, "5678");
            System.out.println("[FAIL] Current overdraft limit not enforced");

        } catch (InsufficientBalanceException e) {
            System.out.println(
                    "[PASS] Current overdraft limit enforced"
            );

        } catch (AccountException e) {
            System.out.println(
                    "[PASS] Current withdrawal rejected: "
                    + e.getMessage()
            );
        }

        // Step 5 - Fixed Deposit premature withdrawal
        System.out.println("\n=== Fixed Deposit Premature Withdrawal Test ===");

        try {
            fixedDeposit.withdraw(10000, "1111");
            System.out.println(
                    "[FAIL] Fixed Deposit allowed premature withdrawal"
            );

        } catch (AccountException e) {
            System.out.println(
                    "[PASS] Fixed Deposit premature withdrawal rejected"
            );
        }

        // Step 6 - Invalid account type
        System.out.println("\n=== Invalid Account Type Test ===");

        try {
            AccountFactory.createAccount(
                    "UNKNOWN",
                    "XXX1001",
                    "Test User",
                    30,
                    5000.0,
                    "ACTIVE",
                    "9999"
            );

            System.out.println(
                    "[FAIL] Invalid account type was accepted"
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "[PASS] Invalid account type rejected: "
                    + e.getMessage()
            );
        }

        System.out.println(
                "\n=== Complete Activity 12 test suite and run ==="
        );
    }
}
