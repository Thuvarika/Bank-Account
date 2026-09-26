package com.gdb.domain;

public class AccountRulesEngine {

    private static final AccountRulesPropertiesLoader savingsRules =
            new AccountRulesPropertiesLoader(
                    "src/main/resources/config/rules/savings.properties");

    private static final AccountRulesPropertiesLoader currentRules =
            new AccountRulesPropertiesLoader(
                    "src/main/resources/config/rules/current.properties");

    private static final AccountRulesPropertiesLoader fixedDepositRules =
            new AccountRulesPropertiesLoader(
                    "src/main/resources/config/rules/fixeddeposit.properties");

    private static final AccountRulesPropertiesLoader salaryRules =
            new AccountRulesPropertiesLoader(
                    "src/main/resources/config/rules/salary.properties");

    public static double getSavingsMinBalance(int tenureYears) {

        if (tenureYears >= 6) {
            return savingsRules.getDouble(
                    "minBalance.privilege", 2500.0);
        }

        if (tenureYears >= 4) {
            return savingsRules.getDouble(
                    "minBalance.premium", 5000.0);
        }

        if (tenureYears >= 2) {
            return savingsRules.getDouble(
                    "minBalance.standard", 7500.0);
        }

        return savingsRules.getDouble(
                "minBalance.new", 10000.0);
    }

    public static double getSavingsInterestRate(int tenureYears) {

        if (tenureYears >= 6) {
            return savingsRules.getDouble(
                    "interestRate.privilege", 4.0);
        }

        if (tenureYears >= 4) {
            return savingsRules.getDouble(
                    "interestRate.premium", 3.5);
        }

        if (tenureYears >= 2) {
            return savingsRules.getDouble(
                    "interestRate.standard", 3.0);
        }

        return savingsRules.getDouble(
                "interestRate.new", 2.7);
    }

    public static double getCurrentOverdraftLimit() {
        return currentRules.getDouble(
                "overdraftLimit", 25000.0);
    }

    public static int getFixedDepositTenureMonths() {
        return (int) fixedDepositRules.getDouble(
                "tenureMonths", 12);
    }

    public static double getFixedDepositInterestRate() {
        return fixedDepositRules.getDouble(
                "interestRate", 6.5);
    }

    public static String getSalaryEmployerName() {
        return salaryRules.getProperty(
                "employerName", "TechCorp");
    }

    public static int getSalaryInactiveMonths() {
        return (int) salaryRules.getDouble(
                "inactiveMonths", 3);
    }
}