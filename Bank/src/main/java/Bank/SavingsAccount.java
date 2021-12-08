package Bank;

public class SavingsAccount extends BankAccount{

    private static final String TYPEACCOUNT = "SavingsAccount";
    private double interestPos = 0.05;

    SavingsAccount(int accountNumber) {
        super(accountNumber, TYPEACCOUNT);
    }

    SavingsAccount(int accountNumber, double balance) throws SavingsAccountException {
        super(accountNumber, balance, TYPEACCOUNT);
        checkBalanceForNegative(balance);
    }

    void setInterestPos(double interestPos) {
        this.interestPos = interestPos;
    }

    @Override
    void withdraw(double debit) throws SavingsAccountException {
        checkBalanceForNegative(this.getBalance()-debit);
        super.withdraw(debit);
    }

    @Override
    void interest(){
        this.changeBalance(balance -> balance + (int)((double)this.balance * interestPos));
    }

    private void checkBalanceForNegative(double balance) throws SavingsAccountException {
        if (balance < 0) {
            throw new SavingsAccountException("Balance of a SavingsAccount cannot drop below zero.");
        }
    }
}
