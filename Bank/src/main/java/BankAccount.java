public class BankAccount {

    int    accountNumber;
    int    balance = 0;
    double interestPos = 0.1;
    double interestNeg = 9.2;

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

    public double getInterestPos() {
        return interestPos;
    }

    public void setInterestPos(double interestPos) {
        this.interestPos = interestPos;
    }

    public double getInterestNeg() {
        return interestNeg;
    }

    public void setInterestNeg(double interestNeg) {
        this.interestNeg = interestNeg;
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

    public int Interest(){
        double balance = getBalance();

        if (balance >= 0){
            balance = balance * interestPos;
        } else {
            balance = balance * interestNeg;
        }

        return (int)(balance * 100);
    }

    public static void main(String[] args) {

    }

}
