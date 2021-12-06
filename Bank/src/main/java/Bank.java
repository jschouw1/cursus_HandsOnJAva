public class Bank {
    int[][] BankAccounts;
    static final int MAXACCOUNTS = 10;

    public int addBankAccount(int balance) {
        BankAccount bankAccount;
        int accountNumber = 1 + BankAccounts.length;
        if (balance != 0) {
            bankAccount = new BankAccount(accountNumber);
        } else {
            bankAccount = new BankAccount(accountNumber, balance);
        }
        return bankAccount.accountNumber;
    }

    public static void main(String[] args) {

    }
}
