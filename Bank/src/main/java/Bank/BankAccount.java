package Bank;

import java.util.ArrayList;
import java.util.List;

abstract class BankAccount {

    private String typeAccount;
    private final int accountNumber;
    protected int balance;

    private final List<BalanceChangedListener> balanceChangedListeners = new ArrayList<>();

    BankAccount(int accountNumber, String typeAccount) {
        this.typeAccount = typeAccount;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    BankAccount(int accountNumber, double balance, String typeAccount) {
        this.typeAccount = typeAccount;
        this.accountNumber = accountNumber;
        this.balance = (int)(balance*100);
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    int getAccountNumber() {
        return accountNumber;
    }

    abstract void interest() throws BankAccountException;

    double getBalance() {
        return (double)balance/100;
    }

    void changeBalance(ChangeBalanceAction action) {
        double oldBalance = balance/100;
        balance = action.change(balance);
        double newBalance = balance/100;
        BalanceChangedEvent balanceChangedEvent = new BalanceChangedEvent(this, oldBalance, newBalance);

        for (BalanceChangedListener balanceChangedListener : balanceChangedListeners) {
            balanceChangedListener.balanceChanged(balanceChangedEvent);
        }
    }

    void deposit(double credit) {
        this.changeBalance(balance -> balance + (int)(credit*100));
    }

    void withdraw(double debit) throws SavingsAccountException {
        this.changeBalance(balance -> balance - (int)(debit*100));
    }

    void addBalanceChangedListener(BalanceChangedListener listener) {
        balanceChangedListeners.add(listener);
    }

    void removeBalanceChangedListener(BalanceChangedListener listener) {
        balanceChangedListeners.remove(listener);
    }

    public static void main(String[] args) {

    }

    @FunctionalInterface
    interface ChangeBalanceAction {
        int change(int oldBalance);
    }
}
