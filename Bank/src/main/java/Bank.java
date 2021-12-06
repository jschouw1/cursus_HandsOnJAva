import java.util.Random;

public class Bank {

    static final int MAXACCOUNTS = 10;
    BankAccount[] bankAccounts = new BankAccount[MAXACCOUNTS];

    public BankAccount[] getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccount[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public int addBankAccount(int balance) {
        BankAccount bankAccount;
        Random random = new Random();
        int accountNumber = random.nextInt();
        if (balance != 0) {
            bankAccount = new BankAccount(accountNumber);
        } else {
            bankAccount = new BankAccount(accountNumber, balance);
        }

        return bankAccount.getAccountNumber();
    }

    public int totalBalance() {
        int totalBalance = 0;
        for (BankAccount bankAccount : bankAccounts) {
            totalBalance += bankAccount.getBalance();
        }
        return totalBalance;
    }

    public void Interest() {
        for (BankAccount bankAccount : bankAccounts) {
            bankAccount.Interest();
        }
    }

    public BankAccount FindAccount(int accountNumber) {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getAccountNumber() == accountNumber) {
                return bankAccount;
            }
        }
        return null;
    }

    public void Transfer(int accountNumberFrom, int accountNumberTo, double amount) {
        BankAccount accountFrom = FindAccount(accountNumberFrom);
        BankAccount accountTo   = FindAccount(accountNumberTo);

        if (accountFrom != null && accountTo != null) {
            accountFrom.setBalance(accountFrom.getBalance() - (int) (amount * 100));
            accountTo.setBalance(accountFrom.getBalance() + (int) (amount * 100));
        }
    }

    public static void main(String[] args) {

    }
}
