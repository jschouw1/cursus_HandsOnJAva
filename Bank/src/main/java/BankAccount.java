public class BankAccount {

    int accountNumber;
    int balance = 0;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = (int)(balance*100);
    }

    public void Deposit(double credit) {
        balance = getBalance() + (int)(credit*100);
        setBalance(balance);
    }

    public void Withdraw(double debit) {
        balance = getBalance() + (int)(debit*100);
        setBalance(balance);
    }

    public static void main(String[] args) {

    }

}
