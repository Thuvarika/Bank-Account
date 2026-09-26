package com.gdb.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountRulesEngine {

    // Savings minimum balance lookup table
    private static final Map<String, Double> MIN_BALANCE_RULES = new HashMap<>();

    // Savings interest rate lookup table
    private static final Map<String, Double> INTEREST_RATE_RULES = new HashMap<>();

    // Fill the lookup tables
    static {
        MIN_BALANCE_RULES.put("NEW", 10000.0);
        MIN_BALANCE_RULES.put("STANDARD", 7500.0);
        MIN_BALANCE_RULES.put("PREMIUM", 5000.0);
        MIN_BALANCE_RULES.put("PRIVILEGE", 2500.0);

        INTEREST_RATE_RULES.put("NEW", 2.70);
        INTEREST_RATE_RULES.put("STANDARD", 3.00);
        INTEREST_RATE_RULES.put("PREMIUM", 3.50);
        INTEREST_RATE_RULES.put("PRIVILEGE", 4.00);
    }

    public static String getSavingsBucket(int tenureYears) {
        if (tenureYears >= 5) {
            return "PRIVILEGE";
        } else if (tenureYears >= 3) {
            return "PREMIUM";
        } else if (tenureYears >= 1) {
            return "STANDARD";
        } else {
            return "NEW";
        }
    }

    public static double getSavingsMinBalance(int tenureYears) {
        String bucket = getSavingsBucket(tenureYears);
        return MIN_BALANCE_RULES.getOrDefault(bucket, 10000.0);
    }

    public static double getSavingsInterestRate(int tenureYears) {
        String bucket = getSavingsBucket(tenureYears);
        return INTEREST_RATE_RULES.getOrDefault(bucket, 2.70);
    }

    public static double getCurrentOverdraftLimit(double monthlyTurnover) {
        return Math.max(monthlyTurnover * 2.5, 25000.0);
    }

    public static double getFDInterestRate(int months) {
        if (months >= 36) {
            return 7.50;
        } else if (months >= 12) {
            return 6.50;
        } else {
            return 5.00;
        }
    }
}