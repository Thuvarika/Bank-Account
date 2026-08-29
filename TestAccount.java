public class TestAccount {

    public static void main(String[] args) {

        System.out.println("===== ACTIVITY 5: EXCEPTION TESTING =====\n");

        // Test 1: Create valid account
        try {
            Account acc = new Account(
                    1001,
                    "Alice Brown",
                    30,
                    5000.0,
                    "Savings"
            );

            System.out.println("Test 1: Account Creation");
            System.out.println("SUCCESS: " + acc.getName()
                    + " | Balance: " + acc.getBalance());
            System.out.println();

        } catch (IllegalArgumentException e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 2: Invalid age
        try {
            Account acc = new Account(
                    1002,
                    "Bob",
                    16,
                    5000.0,
                    "Savings"
            );

            System.out.println("Test 2: FAILED - Invalid age accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("Test 2: Invalid Age");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        }


        // Test 3: Invalid deposit
        try {
            Account acc = new Account(
                    1003,
                    "Charlie",
                    25,
                    5000.0,
                    "Savings"
            );

            acc.deposit(-500);

            System.out.println("Test 3: FAILED - Invalid deposit accepted");

        } catch (InvalidAmountException e) {
            System.out.println("Test 3: Invalid Deposit");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        } catch (InactiveAccountException e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 4: Withdrawal without PIN
        try {
            Account acc = new Account(
                    1004,
                    "David",
                    28,
                    5000.0,
                    "Savings"
            );

            acc.withdraw(500, 1234);

            System.out.println("Test 4: FAILED - Withdrawal allowed without PIN");

        } catch (InvalidPinException e) {
            System.out.println("Test 4: Withdrawal Without PIN");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 5: Successful withdrawal
        try {
            Account acc = new Account(
                    1005,
                    "Alice Brown",
                    30,
                    2000.0,
                    "Current"
            );

            acc.setPin(1234);
            acc.withdraw(200, 1234);

            System.out.println("Test 5: Successful Withdrawal");
            System.out.println("SUCCESS");
            System.out.println("New Balance: " + acc.getBalance());
            System.out.println();

        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 6: Wrong PIN
        try {
            Account acc = new Account(
                    1006,
                    "Emma",
                    25,
                    5000.0,
                    "Savings"
            );

            acc.setPin(1234);
            acc.withdraw(500, 9999);

            System.out.println("Test 6: FAILED - Wrong PIN accepted");

        } catch (InvalidPinException e) {
            System.out.println("Test 6: Wrong PIN");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 7: Insufficient balance
        try {
            Account acc = new Account(
                    1007,
                    "Frank",
                    30,
                    1000.0,
                    "Current"
            );

            acc.setPin(1234);
            acc.withdraw(1500, 1234);

            System.out.println("Test 7: FAILED - Insufficient balance accepted");

        } catch (InsufficientBalanceException e) {
            System.out.println("Test 7: Insufficient Balance");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 8: Minimum balance violation
        try {
            Account acc = new Account(
                    1008,
                    "George",
                    30,
                    1000.0,
                    "Current"
            );

            acc.setPin(1234);
            acc.withdraw(100, 1234);

            System.out.println("Test 8: FAILED - Minimum balance violated");

        } catch (MinimumBalanceViolationException e) {
            System.out.println("Test 8: Minimum Balance Violation");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 9: Inactive account
        try {
            Account acc = new Account(
                    1009,
                    "Henry",
                    30,
                    5000.0,
                    "Savings"
            );

            acc.closeAccount();
            acc.deposit(500);

            System.out.println("Test 9: FAILED - Deposit allowed on inactive account");

        } catch (InactiveAccountException e) {
            System.out.println("Test 9: Inactive Account");
            System.out.println("SUCCESS: " + e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        // Test 10: Reopen account
        try {
            Account acc = new Account(
                    1010,
                    "Isha",
                    25,
                    5000.0,
                    "Savings"
            );

            acc.closeAccount();
            acc.reopenAccount();

            System.out.println("Test 10: Reopen Account");
            System.out.println("SUCCESS: Account is now "
                    + acc.getStatus());
            System.out.println();

        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }


        System.out.println("===== TESTING COMPLETED =====");
    }
}