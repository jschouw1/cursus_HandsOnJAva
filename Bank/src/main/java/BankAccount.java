public class BankAccount {

    int accountNumber;
    double balance = 0;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void Deposit(double credit) {
        balance = getBalance() + credit;
        setBalance(balance);
    }

    public void Withdraw(double debit) {
        balance = getBalance() + debit;
        setBalance(balance);
    }

    public static void main(String[] args) {

    }

}
