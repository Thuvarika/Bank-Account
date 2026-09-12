package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 8: Polymorphism Test ===");

        // Step 1
        try {
            Account savings = new SavingsAccount(
                "S001", "Student", 20, 10000.0, "ACTIVE", "1234",
                1000.0, 4.0
            );

            savings.withdraw(9500.0, "1234");
            System.out.println("[FAIL] SavingsAccount minimum balance test");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("[PASS] SavingsAccount minimum balance breach");
        } catch (Exception e) {
            System.out.println("[FAIL] Unexpected exception");
        }

        // Step 2
        try {
            Account current = new CurrentAccount(
                "C001", "Student", 20, 5000.0, "ACTIVE", "1234",
                25000.0
            );

            current.withdraw(10000.0, "1234");

            if (current.getBalance() == -5000.0) {
                System.out.println("[PASS] CurrentAccount overdraft withdrawal");
            } else {
                System.out.println("[FAIL] Wrong balance");
            }

            // Step 3
            try {
                current.withdraw(30000.0, "1234");
                System.out.println("[FAIL] Overdraft limit test");
            } catch (InsufficientBalanceException e) {
                System.out.println("[PASS] CurrentAccount overdraft limit");
            }

        } catch (Exception e) {
            System.out.println("[FAIL] CurrentAccount test");
        }

        // Step 4
        try {
            Account fixed = new FixedDepositAccount(
                "F001", "Student", 20, 10000.0, "ACTIVE", "1234",
                12, 6.5
            );

            fixed.withdraw(1000.0, "1234");
            System.out.println("[FAIL] FixedDepositAccount withdrawal");
        } catch (AccountException e) {
            System.out.println("[PASS] FixedDepositAccount premature withdrawal");
        }

        System.out.println("=== Complete Activity 8 polymorphism tests and verify output ===");
    }
}