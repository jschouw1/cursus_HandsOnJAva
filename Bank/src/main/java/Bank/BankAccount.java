package Bank;

class BankAccount {

    private int    accountNumber;
    private int    balance;
    private double interestPos = 0.01;
    private double interestNeg = 0.092;

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = (int)(balance*100);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return (double)balance/100;
    }

    public void setInterestPos(double interestPos) {
        this.interestPos = interestPos;
    }

    public void setInterestNeg(double interestNeg) {
        this.interestNeg = interestNeg;
    }

    public void deposit(double credit) {
        balance += (int)(credit*100);
    }

    public void withdraw(double debit) {
        balance -= (int)(debit*100);
    }

    public void interest(){
        if (this.balance >= 0){
            this.balance += (int)((double)this.balance * interestPos);
        } else {
            this.balance += (int)((double)this.balance * interestNeg);
        }
    }

    public static void main(String[] args) {

    }

}
