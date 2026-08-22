public class TestAccount {
    public static void main(String[] args) {

        System.out.println("=".repeat(50));
        System.out.println("GLOBAL DIGITAL BANK - ACCOUNT TEST");
        System.out.println("=".repeat(50));

        System.out.println(">>> 1. Creating Account");

        Account account1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");

        System.out.println("Account created!");
        System.out.println("Account #" + account1.getAccountNumber() + " | "
                + account1.getName() + " (" + account1.getAge() + " yrs) | "
                + account1.getAccountType() + " | Rs." + account1.getBalance()
                + " | " + account1.getStatus());

        System.out.println(">>> 2. Deposit Money");

        double deposit1 = 500.0;
        boolean result = account1.deposit(deposit1);

        System.out.println("Depositing Rs." + deposit1 + ": "
                + (result ? "SUCCESS" : "FAILED (Invalid amount)"));
        System.out.println("New balance: Rs." + account1.getBalance());

        double deposit2 = -100.0;
        result = account1.deposit(deposit2);

        System.out.println("Depositing Rs." + deposit2 + ": "
                + (result ? "SUCCESS" : "FAILED (Invalid amount)"));

        System.out.println(">>> 3. Withdraw Money");

        double withdraw1 = 200.0;
        result = account1.withdraw(withdraw1);

        System.out.println("Withdrawing Rs." + withdraw1 + ": "
                + (result ? "SUCCESS" : "FAILED (Insufficient balance)"));
        System.out.println("New balance: Rs." + account1.getBalance());

        double withdraw2 = 2000.0;
        result = account1.withdraw(withdraw2);

        System.out.println("Withdrawing Rs." + withdraw2 + ": "
                + (result ? "SUCCESS" : "FAILED (Insufficient balance)"));
        System.out.println("Current balance: Rs." + account1.getBalance());

        System.out.println(">>> 4. Creating Another Account");

        Account account2 = new Account(1002, "Jane Smith", 30, 2000.0, "Current");

        System.out.println("Account #" + account2.getAccountNumber() + " | "
                + account2.getName() + " (" + account2.getAge() + " yrs) | "
                + account2.getAccountType() + " | Rs." + account2.getBalance()
                + " | " + account2.getStatus());

        System.out.println(">>> 5. All Accounts");

        System.out.println("Account #" + account1.getAccountNumber() + " | "
                + account1.getName() + " (" + account1.getAge() + " yrs) | "
                + account1.getAccountType() + " | Rs." + account1.getBalance()
                + " | " + account1.getStatus());

        System.out.println("Account #" + account2.getAccountNumber() + " | "
                + account2.getName() + " (" + account2.getAge() + " yrs) | "
                + account2.getAccountType() + " | Rs." + account2.getBalance()
                + " | " + account2.getStatus());

        System.out.println("=".repeat(50));
        System.out.println("TEST COMPLETED!");
        System.out.println("=".repeat(50));
    }
}