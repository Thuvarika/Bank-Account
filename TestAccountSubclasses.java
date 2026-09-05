public class TestAccountSubclasses {

    public static void main(String[] args) {

        System.out.println("=== Activity 7: Account Subclasses Test ===");

        SavingsAccount savings =
                new SavingsAccount(1001, "Alice", 25, 10000);

        System.out.println(
                "Savings Account Created: Balance Rs "
                + savings.getBalance()
                + " | Min Balance: Rs "
                + savings.getMinBalance());

        CurrentAccount current =
                new CurrentAccount(1002, "Bob", 30, 10000);

        System.out.println(
                "Current Account Created: Overdraft Limit Rs "
                + current.getOverdraftLimit());

        FixedDepositAccount fd =
                new FixedDepositAccount(1003, "Charlie", 35, 10000);

        System.out.println(
                "Fixed Deposit Created: Tenure "
                + fd.getTenureMonths()
                + " months | Interest: "
                + fd.getInterestRate() + "%");

        SalaryAccount salary =
                new SalaryAccount(
                        1004, "David", 28, 10000, "Infosys");

        System.out.println(
                "Salary Account Created: Employer "
                + salary.getEmployerName());

        System.out.println(
                "All subclasses instantiated successfully!");
    }
}