package Bank;

import java.util.Scanner;

public class Bank {

    static final int MAXACCOUNTS = 10;
    private static int accountNumberGenerator = 100;
    private int baIndex = 0;
    private BankAccount[] bankAccounts = new BankAccount[MAXACCOUNTS];

    public int addBankAccount(double balance) {
        BankAccount bankAccount;
        int accountNumber = accountNumberGenerator++;
        if (balance == 0.00) {
            bankAccount = new BankAccount(accountNumber);
        } else {
            bankAccount = new BankAccount(accountNumber, balance);
        }
        bankAccounts[baIndex] = bankAccount;
        baIndex++;

        return bankAccount.getAccountNumber();
    }

    public double totalBalance() {
        int totalBalance = 0;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null) {
                totalBalance += (bankAccount.getBalance() * 100);
            }
        }
        return (double) totalBalance / 100;
    }

    public void interest() {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null) {
                bankAccount.interest();
            }
        }
    }

    public BankAccount findAccount(int accountNumber) throws BankException {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null && bankAccount.getAccountNumber() == accountNumber) {
                return bankAccount;
            }
        }
        throw new BankException("No account found for account number ".concat(String.valueOf(accountNumber)).concat("."));
    }

    public void transfer(int accountNumberFrom, int accountNumberTo, double amount) throws BankException {
        BankAccount accountFrom = findAccount(accountNumberFrom);
        BankAccount accountTo = findAccount(accountNumberTo);

        if (accountFrom.getAccountNumber() != accountTo.getAccountNumber()) {
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
        } else {
            throw new BankException("Cannot transfer from and to the same account.");
        }
    }

    public void removeAccount(int accountNumber) throws BankException {
        BankAccount removeAccount = findAccount(accountNumber);

        if (removeAccount.getBalance() != 0) {
            throw new BankException("Cannot remove account with non-zero balance. Balance of account ".concat(String.valueOf(removeAccount.getAccountNumber()).concat(" is ").concat(String.valueOf(removeAccount.getBalance()).concat("."))));
        }

        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getAccountNumber() == removeAccount.getAccountNumber()) {
                bankAccounts[i] = null;
                baIndex--;
                break;
            }
        }
    }

    public void removeAccount(int accountNumberRemove, int accountNumberTo) throws BankException {
        BankAccount removeAccount = findAccount(accountNumberRemove);
        BankAccount toAccount = findAccount(accountNumberTo);

        transfer(removeAccount.getAccountNumber(), toAccount.getAccountNumber(), removeAccount.getBalance());

        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getAccountNumber() == removeAccount.getAccountNumber()) {
                bankAccounts[i] = null;
                baIndex--;
                break;
            }
        }
    }

    public int[] findAllNegativeAccounts() {
        int count = 0;

        for (int i = 0; i < baIndex; i++) {
            if (bankAccounts[i].getBalance() < 0) {
                count++;
            }
        }
        int[] negativeBalanceAccountNumbers = new int[count];
        int index = 0;

        for (int i = 0; i < baIndex; i++) {
            var bankAccount = bankAccounts[i];
            if (bankAccount.getBalance() < 0) {
                negativeBalanceAccountNumbers[index] = bankAccount.getAccountNumber();
                index++;
            }
        }

        return negativeBalanceAccountNumbers;
    }

    public int[] findMostAverageAccounts() {
        double currentBest = Double.MAX_VALUE;
        double differenceFromAverage;
        double averageBalance = totalBalance() / (baIndex);
        int[] mostAverageAccountNumbers = new int[baIndex];
        int index = 0;

        for (int i = 0; i < baIndex; i++) {
            differenceFromAverage = Math.abs(bankAccounts[i].getBalance() - averageBalance);

            if (differenceFromAverage == currentBest) {
                mostAverageAccountNumbers[index] = bankAccounts[i].getAccountNumber();
                index++;
            } else if (currentBest > differenceFromAverage) {
                for (int j = 0; j < index; j++) {
                    mostAverageAccountNumbers[j] = 0;
                }
                index = 0;
                currentBest = differenceFromAverage;
                mostAverageAccountNumbers[index] = bankAccounts[i].getAccountNumber();
                index++;
            }

        }

        int[] resultMostAverageAccountNumbers = new int[index];
        index = 0;

        for (int accountNumber : mostAverageAccountNumbers) {
            if (accountNumber != 0) {
                resultMostAverageAccountNumbers[index] = accountNumber;
                index++;
            }

        }

        return resultMostAverageAccountNumbers;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bank app");
        BankAccount bankAccount = new BankAccount(1);
        bankAccount.deposit(20.01);
        System.out.println(bankAccount.getAccountNumber());
        System.out.println(bankAccount.getBalance());
        bankAccount.withdraw(11.99);
        System.out.println(bankAccount.getAccountNumber());
        System.out.println(bankAccount.getBalance());
        Bank bank = new Bank();
        int accountNumber = bank.addBankAccount(1000.05);
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        BankAccount foundAccount = bank.findAccount(accountNumber);
        System.out.println(foundAccount.getBalance());
        int accountNumber2 = bank.addBankAccount(-100.05);
        System.out.println("-----Account 2-----");
        System.out.println(accountNumber2);
        BankAccount foundAccount2 = bank.findAccount(accountNumber2);
        System.out.println(foundAccount2.getBalance());
        bank.interest();
        System.out.println("-----RENTE-----");
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        System.out.println(foundAccount.getBalance());
        System.out.println("-----Account 2-----");
        System.out.println(accountNumber2);
        System.out.println(foundAccount2.getBalance());
        System.out.println("-----Totaal van accounts-----");
        System.out.println(bank.totalBalance());
        System.out.println("-----150.76 van account 1 naar 2-----");
        bank.transfer(accountNumber, accountNumber2, 150.76);
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        System.out.println(foundAccount.getBalance());
        System.out.println("-----Account 2-----");
        System.out.println(accountNumber2);
        System.out.println(foundAccount2.getBalance());
        System.out.println("-----Totaal van accounts-----");
        System.out.println(bank.totalBalance());
        System.out.println("-----Verwijderen Account 2, geld naar account 1-----");
        bank.removeAccount(accountNumber2, accountNumber);
        if (bank.findAccount(accountNumber2) == null) {
            System.out.println("Account 2 succesvol verwijderd.");
        } else {
            System.out.println("Verwijderen account 2 niet succesvol.");
        }
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        System.out.println(foundAccount.getBalance());
        System.out.println("-----Totaal van accounts-----");
        System.out.println(bank.totalBalance());
        System.out.println("-----Verwijderen Account 1, zonder transfer-----");
        bank.removeAccount(accountNumber);
        if (bank.findAccount(accountNumber) == null) {
            System.out.println("Account 1 succesvol verwijderd.");
        } else {
            System.out.println("Verwijderen account 1 niet succesvol.");
        }




//        while (true) {
//
////            switch (scanner.nextInt()) {
////                case 1:
////                    System.out.println("Define balance new BankAccount: ");
////                    double newBalance = scanner.nextDouble();
////                    int accountNumber;
////
////                    accountNumber = addBankAccount((int) newBalance * 100);
////
////                    System.out.println("New account with number ".concat(String.valueOf(accountNumber)).concat(" created."));
////                    break;
////                case 2:
////                    double totalBalance = TotalBalance()/100;
////                    System.out.println("The total balance of accounts is: ".concat(String.valueOf(totalBalance)));
////                    break;
////                case 3:
////                    int searchAccountNumber = scanner.nextInt();
////                    BankAccount foundAccount = Find
////                    break;
////                case 4:
////                    System.exit(0);
////                default:
////                    System.out.println("Invalid input, try again:");
////            }
//        }
    }

    public class BankException extends Exception {

        public BankException(String message) {
            super(message);
        }
    }
}
