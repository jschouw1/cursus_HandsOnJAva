package Bank;

import java.util.LinkedList;
import java.util.List;

public class Bank {

    private static int accountNumberGenerator = 100;
    private List<BankAccount> bankAccounts = new LinkedList<>();


    public int addCheckingAccount(double balance) {
        CheckingAccount checkingAccount;
        int accountNumber = accountNumberGenerator++;
        if (balance == 0.00) {
            checkingAccount = new CheckingAccount(accountNumber);
        } else {
            checkingAccount = new CheckingAccount(accountNumber, balance);
        }
        bankAccounts.add(checkingAccount);

        return checkingAccount.getAccountNumber();
    }

    public int addSavingsAccount(double balance) throws SavingsAccountException {
        SavingsAccount savingsAccount;
        int accountNumber = accountNumberGenerator++;
        if (balance == 0.00) {
            savingsAccount = new SavingsAccount(accountNumber);
        } else {
            savingsAccount = new SavingsAccount(accountNumber, balance);
        }
        bankAccounts.add(savingsAccount);

        return savingsAccount.getAccountNumber();
    }

    public double totalBalance() {
        int totalBalance = 0;
        for (BankAccount bankAccount : bankAccounts) {
            totalBalance += (bankAccount.getBalance() * 100);
        }
        return (double) totalBalance / 100;
    }

    public void interest() throws BankAccountException {
        for (BankAccount bankAccount : bankAccounts) {
            bankAccount.interest();
        }
    }

    public BankAccount findAccount(int accountNumber) throws BankException {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getAccountNumber() == accountNumber) {
                return bankAccount;
            }
        }
        throw new BankException(String.format("No account found for account number %s.", String.valueOf(accountNumber)));
    }

    public void transfer(int accountNumberFrom, int accountNumberTo, double amount) throws BankException, SavingsAccountException {
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
            throw new BankException(String.format("Cannot remove account with non-zero balance. Balance of account %s is %s.", String.valueOf(removeAccount.getAccountNumber()), String.valueOf(removeAccount.getBalance())));
        }

        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getAccountNumber() == removeAccount.getAccountNumber()) {
                bankAccounts.remove(i);
                break;
            }
        }
    }

    public void removeAccount(int accountNumberRemove, int accountNumberTo) throws BankException, SavingsAccountException {
        BankAccount removeAccount = findAccount(accountNumberRemove);
        BankAccount toAccount = findAccount(accountNumberTo);

        transfer(removeAccount.getAccountNumber(), toAccount.getAccountNumber(), removeAccount.getBalance());

        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getAccountNumber() == removeAccount.getAccountNumber()) {
                bankAccounts.remove(i);
                break;
            }
        }
    }

    public int[] findAllNegativeAccounts() {
        int count = 0;

        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getBalance() < 0) {
                count++;
            }
        }
        int[] negativeBalanceAccountNumbers = new int[count];
        int index = 0;

        for (int i = 0; i < bankAccounts.size(); i++) {
            var bankAccount = bankAccounts.get(i);
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
        double averageBalance = totalBalance() / (bankAccounts.size());
        int[] mostAverageAccountNumbers = new int[bankAccounts.size()];
        int index = 0;

        for (int i = 0; i < bankAccounts.size(); i++) {
            differenceFromAverage = Math.abs(bankAccounts.get(i).getBalance() - averageBalance);

            if (differenceFromAverage == currentBest) {
                mostAverageAccountNumbers[index] = bankAccounts.get(i).getAccountNumber();
                index++;
            } else if (currentBest > differenceFromAverage) {
                for (int j = 0; j < index; j++) {
                    mostAverageAccountNumbers[j] = 0;
                }
                index = 0;
                currentBest = differenceFromAverage;
                mostAverageAccountNumbers[index] = bankAccounts.get(i).getAccountNumber();
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
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Bank app");
//        BankAccount bankAccount = new BankAccount(1);
//        bankAccount.deposit(20.01);
//        System.out.println(bankAccount.getAccountNumber());
//        System.out.println(bankAccount.getBalance());
//        bankAccount.withdraw(11.99);
//        System.out.println(bankAccount.getAccountNumber());
//        System.out.println(bankAccount.getBalance());
//        Bank bank = new Bank();
//        int accountNumber = bank.addCheckingAccount(1000.05);
//        System.out.println("-----Account 1-----");
//        System.out.println(accountNumber);
//        BankAccount foundAccount = bank.findAccount(accountNumber);
//        System.out.println(foundAccount.getBalance());
//        int accountNumber2 = bank.addCheckingAccount(-100.05);
//        System.out.println("-----Account 2-----");
//        System.out.println(accountNumber2);
//        BankAccount foundAccount2 = bank.findAccount(accountNumber2);
//        System.out.println(foundAccount2.getBalance());
//        bank.interest();
//        System.out.println("-----RENTE-----");
//        System.out.println("-----Account 1-----");
//        System.out.println(accountNumber);
//        System.out.println(foundAccount.getBalance());
//        System.out.println("-----Account 2-----");
//        System.out.println(accountNumber2);
//        System.out.println(foundAccount2.getBalance());
//        System.out.println("-----Totaal van accounts-----");
//        System.out.println(bank.totalBalance());
//        System.out.println("-----150.76 van account 1 naar 2-----");
//        bank.transfer(accountNumber, accountNumber2, 150.76);
//        System.out.println("-----Account 1-----");
//        System.out.println(accountNumber);
//        System.out.println(foundAccount.getBalance());
//        System.out.println("-----Account 2-----");
//        System.out.println(accountNumber2);
//        System.out.println(foundAccount2.getBalance());
//        System.out.println("-----Totaal van accounts-----");
//        System.out.println(bank.totalBalance());
//        System.out.println("-----Verwijderen Account 2, geld naar account 1-----");
//        bank.removeAccount(accountNumber2, accountNumber);
//        if (bank.findAccount(accountNumber2) == null) {
//            System.out.println("Account 2 succesvol verwijderd.");
//        } else {
//            System.out.println("Verwijderen account 2 niet succesvol.");
//        }
//        System.out.println("-----Account 1-----");
//        System.out.println(accountNumber);
//        System.out.println(foundAccount.getBalance());
//        System.out.println("-----Totaal van accounts-----");
//        System.out.println(bank.totalBalance());
//        System.out.println("-----Verwijderen Account 1, zonder transfer-----");
//        bank.removeAccount(accountNumber);
//        if (bank.findAccount(accountNumber) == null) {
//            System.out.println("Account 1 succesvol verwijderd.");
//        } else {
//            System.out.println("Verwijderen account 1 niet succesvol.");
//        }




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


}
