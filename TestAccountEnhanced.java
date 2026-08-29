public class TestAccountEnhanced {

    public static void main(String[] args) {

        System.out.println("============================================================");
        System.out.println("ENHANCED ACCOUNT TEST (BOOLEAN RETURNS)");
        System.out.println("============================================================");

        // Test 1: Valid Account Creation
        System.out.println("\n>>> Test 1: Valid Account Creation");

        AccountEnhanced account1 =
                new AccountEnhanced(1001, "John Doe", 25, 1000.0, "Savings");

        System.out.println(formatAccount(account1));


        // Test 2: Invalid Age
        System.out.println(">>> Test 2: Invalid Age (under 18)");
        System.out.println("Creating account with age 16");

        AccountEnhanced account2 =
                new AccountEnhanced(1002, "Young Kid", 16, 500.0, "Savings");

        System.out.println("Age auto-corrected to: " + account2.getAge());
        System.out.println(formatAccount(account2));


        // Test 3: Invalid Account Type
        System.out.println(">>> Test 3: Invalid Account Type");
        System.out.println("Creating account with type \"Invalid\"");

        AccountEnhanced account3 =
                new AccountEnhanced(1003, "Test User", 25, 500.0, "Invalid");

        System.out.println("Account type defaulted to: "
                + account3.getAccountType());

        System.out.println(formatAccount(account3));


        // Test 4: Minimum Balance Enforcement
        System.out.println(">>> Test 4: Minimum Balance Enforcement on Creation");
        System.out.println("Creating Savings account with ₹300 (below minimum)");

        AccountEnhanced account4 =
                new AccountEnhanced(1004, "Bob Wilson", 25, 300.0, "Savings");

        System.out.println("Balance auto-corrected to minimum: ₹"
                + account4.getBalance());

        System.out.println(formatAccount(account4));


        // Test 5: Withdrawal with Minimum Balance
        System.out.println(">>> Test 5: Withdrawal with Minimum Balance");

        AccountEnhanced account5 =
                new AccountEnhanced(1005, "Alice Brown", 30, 1000.0, "Current");

        account5.setPin(1234);

        System.out.println("Initial: " + formatAccount(account5));

        boolean result = account5.withdraw(200.0, 1234);

        System.out.println("Withdrawing ₹200.0: "
                + (result ? "SUCCESS" : "FAILED"));

        System.out.println("New balance: ₹" + account5.getBalance());

        System.out.println("After withdrawal: " + formatAccount(account5));

        result = account5.withdraw(900.0, 1234);

        System.out.println("Withdrawing ₹900.0 (would leave ₹-100): "
                + (result ? "SUCCESS" : "FAILED (Minimum balance violation)"));

        System.out.println("Current balance: ₹" + account5.getBalance());


        // Test 6: Account Status Management
        System.out.println(">>> Test 6: Account Status Management");

        AccountEnhanced account6 =
                new AccountEnhanced(1006, "Charlie Green", 35, 2000.0, "Savings");

        System.out.println("Initial: " + formatAccount(account6));

        result = account6.closeAccount();

        System.out.println("Closing account: "
                + (result ? "SUCCESS" : "FAILED"));

        System.out.println("After close: " + formatAccount(account6));

        result = account6.deposit(500.0);

        System.out.println("\nDepositing ₹500.0 to closed account: "
                + (result ? "SUCCESS" : "FAILED (Account inactive)"));

        result = account6.reopenAccount();

        System.out.println("Reopening account: "
                + (result ? "SUCCESS" : "FAILED"));

        System.out.println("After reopen: " + formatAccount(account6));


        // Test 7: PIN Protection
        System.out.println(">>> Test 7: PIN Protection");

        AccountEnhanced account7 =
                new AccountEnhanced(1007, "Diana Prince", 28, 1500.0, "Savings");

        result = account7.setPin(1234);

        System.out.println("Setting PIN 1234: "
                + (result ? "SUCCESS" : "FAILED"));

        result = account7.verifyPin(1234);

        System.out.println("Correct PIN (1234): "
                + (result ? "SUCCESS" : "FAILED"));

        result = account7.verifyPin(9999);

        System.out.println("Incorrect PIN (9999): "
                + (result ? "SUCCESS" : "FAILED (Incorrect PIN)"));

        result = account7.withdraw(200.0, 1234);

        System.out.println("Withdrawing ₹200.0 with correct PIN (1234): "
                + (result ? "SUCCESS" : "FAILED"));

        System.out.println("New balance: ₹" + account7.getBalance());

        result = account7.withdraw(100.0, 9999);

        System.out.println("Withdrawing ₹100.0 with incorrect PIN (9999): "
                + (result ? "SUCCESS" : "FAILED (Incorrect PIN)"));

        AccountEnhanced account8 =
                new AccountEnhanced(1008, "No PIN User", 25, 1000.0, "Savings");

        result = account8.withdraw(100.0, 1234);

        System.out.println("Withdrawing ₹100.0 with PIN not set: "
                + (result ? "SUCCESS" : "FAILED (PIN not set)"));


        // Test 8: All Accounts Summary
        System.out.println(">>> Test 8: All Accounts Summary");

        System.out.println(formatAccount(account1));
        System.out.println(formatAccount(account2));
        System.out.println(formatAccount(account3));
        System.out.println(formatAccount(account4));
        System.out.println(formatAccount(account5));
        System.out.println(formatAccount(account6));
        System.out.println(formatAccount(account7));


        System.out.println("============================================================");
        System.out.println("ENHANCED TEST COMPLETED!");
        System.out.println("============================================================");
    }


    // Method to format account details
    static String formatAccount(AccountEnhanced account) {

        String pinStatus;

        if (account.hasPin()) {
            pinStatus = "Yes";
        } else {
            pinStatus = "No";
        }

        return "Account #" + account.getAccountNumber()
                + " | " + account.getName()
                + " (" + account.getAge() + " yrs)"
                + " | " + account.getAccountType()
                + " | ₹" + account.getBalance()
                + " | " + account.getStatus()
                + " | PIN: " + pinStatus;
    }
}