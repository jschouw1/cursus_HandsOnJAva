package Bank;

class CheckingAccount extends BankAccount {
    private static final String TYPEACCOUNT = "CheckingAccount";
    private double interestPos = 0.01;
    private double interestNeg = 0.092;

    CheckingAccount(int accountNumber) {
        super(accountNumber, TYPEACCOUNT);
    }

    CheckingAccount(int accountNumber, double balance) {
        super(accountNumber, balance, TYPEACCOUNT);
    }

    void setInterestPos(double interestPos) {
        this.interestPos = interestPos;
    }

    void setInterestNeg(double interestNeg) {
        this.interestNeg = interestNeg;
    }

    @Override
    void interest(){
        if (this.balance >= 0){
            this.changeBalance(balance -> balance + (int)((double)this.balance * interestPos));
        } else {
            this.changeBalance(balance -> balance + (int)((double)this.balance * interestNeg));
        }
    }
}
