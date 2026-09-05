public class TestAccountExceptions {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================");
        Account acc1 = null;
        System.out.println("\n>>> Test 1: Valid Account Creation");
        try {
            acc1 = new Account(1001, "John Doe", 25, 1000, "Savings");
            System.out.println("SUCCESS: " + display(acc1));
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 2: Invalid Age (under 18)");
        try {
            Account acc2 = new Account(1002, "Tom Smith", 16, 1000, "Savings");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 3: Invalid Account Type");
        try {
            Account acc3 = new Account(1003, "Sam Brown", 25, 1000, "Invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 4: Minimum Balance on Creation");
        System.out.println("\nCreating Savings account with ₹300");
        try {
            Account acc4 = new Account(1004, "David Lee", 25, 300, "Savings");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 5: Valid Deposit and Withdrawal");
        try {
            Account acc5 =
                new Account(1005, "Alice Brown", 30, 1000, "Current");

            System.out.println("Account: " + display(acc5));

            System.out.print("Setting PIN 1234: ");
            acc5.setPin(1234);
            System.out.println("SUCCESS");

            System.out.print("Depositing ₹500.0: ");
            acc5.deposit(500);
            System.out.println("SUCCESS");

            System.out.println("Balance after deposit: ₹"
                    + acc5.getBalance());

            System.out.print("Withdrawing ₹200.0: ");
            acc5.withdraw(200, 1234);
            System.out.println("SUCCESS");

            System.out.println("Balance after withdrawal: ₹"
                    + acc5.getBalance());

            System.out.println(display(acc5));

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 6: Invalid Deposit (Negative Amount)");

        try {
            Account acc6 =
                new Account(1006, "Test User", 25, 1000, "Current");

            System.out.println("Attempting to deposit ₹-100.0");
            acc6.deposit(-100);

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 7: Insufficient Balance");

        try {
            Account acc7 =
                new Account(1007, "Charlie Green", 35, 500, "Savings");

            acc7.setPin(1234);

            System.out.println("Account: " + display(acc7));
            System.out.println("Attempting to withdraw ₹1000.0");

            acc7.withdraw(1000, 1234);

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 8: Minimum Balance Violation");

        try {
            Account acc8 =
                new Account(1008, "Diana Prince", 28, 1000, "Savings");

            acc8.setPin(1234);

            System.out.println("Account: " + display(acc8));
            System.out.println("Attempting to withdraw ₹600.0");

            acc8.withdraw(600, 1234);

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 9: Inactive Account Operations");

        try {
            Account acc9 =
                new Account(1009, "Eve Wilson", 32, 2000, "Current");

            System.out.println("Account: " + display(acc9));

            System.out.print("Closing account: ");
            acc9.closeAccount();
            System.out.println("SUCCESS");

            System.out.println(
                "Attempting to deposit ₹100.0 on closed account");

            try {
                acc9.deposit(100);
            } catch (InactiveAccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Reopening account: ");
            acc9.reopenAccount();
            System.out.println("SUCCESS");

            System.out.print("Depositing ₹100.0 after reopen: ");
            acc9.deposit(100);
            System.out.println("SUCCESS");

            System.out.println("Balance after deposit: ₹"
                    + acc9.getBalance());

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 10: PIN Verification");

        try {
            Account acc10 =
                new Account(1010, "Frank Miller", 40, 1500, "Savings");

            System.out.println("Account: " + display(acc10));

            System.out.print("Setting PIN 1234: ");
            acc10.setPin(1234);
            System.out.println("SUCCESS");

            System.out.print(
                "Withdrawing ₹200.0 with correct PIN: ");

            acc10.withdraw(200, 1234);
            System.out.println("SUCCESS");

            System.out.println("\nBalance: ₹"
                    + acc10.getBalance());

            System.out.println(
                "Attempting to withdraw ₹100.0 with incorrect PIN (9999)");

            try {
                acc10.withdraw(100, 9999);
            } catch (InvalidPinException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.println(
                "Attempting to withdraw ₹100.0 without PIN set");

            Account accNoPin =
                new Account(1011, "No Pin User", 30, 1000, "Savings");

            try {
                accNoPin.withdraw(100, 1234);
            } catch (InvalidPinException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        System.out.println("\n>>> Test 11: All Accounts Summary");

        if (acc1 != null) {
            System.out.println(display(acc1));
        }

        try {
            Account acc5 =
                new Account(1005, "Alice Brown", 30, 1000, "Current");
            acc5.setPin(1234);
            acc5.deposit(500);
            acc5.withdraw(200, 1234);
            System.out.println(display(acc5));

            Account acc6 =
                new Account(1006, "Charlie Green", 35, 500, "Savings");
            acc6.setPin(1234);
            System.out.println(display(acc6));

            Account acc7 =
                new Account(1007, "Diana Prince", 28, 1000, "Savings");
            acc7.setPin(1234);
            System.out.println(display(acc7));

            Account acc8 =
                new Account(1008, "Eve Wilson", 32, 2000, "Current");
            acc8.closeAccount();
            acc8.reopenAccount();
            acc8.deposit(100);
            System.out.println(display(acc8));

            Account acc9 =
                new Account(1009, "Frank Miller", 40, 1500, "Savings");
            acc9.setPin(1234);
            acc9.withdraw(200, 1234);
            System.out.println(display(acc9));

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
    public static String display(Account account) {

        String pinStatus = account.hasPin() ? "Yes" : "No";

        return "Account #" + account.getAccountNumber()
                + " | " + account.getName()
                + " (" + account.getAge() + " yrs)"
                + " | " + account.getAccountType()
                + " | ₹" + account.getBalance()
                + " | " + account.getStatus()
                + " | PIN: " + pinStatus;
    }
}
