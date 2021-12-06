import java.util.Random;
import java.util.Scanner;

public class Bank {

    static final int MAXACCOUNTS = 10;
    int baIndex = 0;
    BankAccount[] bankAccounts = new BankAccount[MAXACCOUNTS];

    public int AddBankAccount(double balance) {
        BankAccount bankAccount;
        Random random = new Random();
        int accountNumber = random.nextInt(MAXACCOUNTS);
        if (balance == 0.00) {
            bankAccount = new BankAccount(accountNumber);
        } else {
            bankAccount = new BankAccount(accountNumber, balance);
        }
        bankAccounts[baIndex] = bankAccount;
        baIndex++;

        return bankAccount.getAccountNumber();
    }

    public double TotalBalance() {
        int totalBalance = 0;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null) {
            totalBalance += (bankAccount.getBalance()*100);
            }
        }
        return (double)totalBalance/100;
    }

    public void Interest() {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null) {
                bankAccount.Interest();
            }
        }
    }

    public BankAccount FindAccount(int accountNumber) {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null && bankAccount.getAccountNumber() == accountNumber) {
                return bankAccount;
            }
        }
        return null;
    }

    public void Transfer(int accountNumberFrom, int accountNumberTo, double amount) {
        BankAccount accountFrom = FindAccount(accountNumberFrom);
        BankAccount accountTo   = FindAccount(accountNumberTo);

        if (accountFrom != null && accountTo != null) {
            accountFrom.Withdraw(amount);
            accountTo.Deposit(amount);
        }
    }

    public void RemoveAccount(int accountNumber) {
        BankAccount removeAccount = FindAccount(accountNumber);

        if (removeAccount != null) {
            for (int i=0; i<bankAccounts.length; i++){
                if (bankAccounts[i].getAccountNumber() == removeAccount.getAccountNumber()) {
                    bankAccounts[i] = null;
                    break;
                }
            }
        }
    }

    public void RemoveAccount(int accountNumberRemove, int accountNumberTo) {
        BankAccount removeAccount = FindAccount(accountNumberRemove);
        BankAccount toAccount     = FindAccount(accountNumberTo);

        if (removeAccount != null && toAccount != null && removeAccount.getAccountNumber() != toAccount.getAccountNumber()) {

            Transfer(removeAccount.getAccountNumber(), toAccount.getAccountNumber(), removeAccount.getBalance());

            for (int i=0; i<bankAccounts.length; i++){
                if (bankAccounts[i].getAccountNumber() == removeAccount.getAccountNumber()) {
                    bankAccounts[i] = null;
                    break;
                }
            }
        }
    }

    public int[] FindAllNegativeAccounts() {
        int index = 0;
        int[] negativeBalanceAccountNumbers = new int[MAXACCOUNTS];

        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getBalance() < 0) {
                negativeBalanceAccountNumbers[index] = bankAccount.getAccountNumber();
                index++;
            }
        }

        return  negativeBalanceAccountNumbers;
    }

//    public int[] FindMostAverageAccounts() {
//        double differenceFromAverage;
//        double averageBalance = TotalBalance();
//        int[]  mostAverageAccountNumbers = new int[MAXACCOUNTS];
//
//        for (BankAccount bankAccount : bankAccounts) {
//            differenceFromAverage = Math.abs(bankAccount.getBalance() - averageBalance);
//
//            if (differenceFromAverage)
//        }
//
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bank app");
        BankAccount bankAccount = new BankAccount(1);
        bankAccount.Deposit(20.01);
        System.out.println(bankAccount.getAccountNumber());
        System.out.println(bankAccount.getBalance());
        bankAccount.Withdraw(11.99);
        System.out.println(bankAccount.getAccountNumber());
        System.out.println(bankAccount.getBalance());
        Bank bank = new Bank();
        int accountNumber = bank.AddBankAccount(1000.05);
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        BankAccount foundAccount = bank.FindAccount(accountNumber);
        System.out.println(foundAccount.getBalance());
        int accountNumber2 = bank.AddBankAccount(-100.05);
        System.out.println("-----Account 2-----");
        System.out.println(accountNumber2);
        BankAccount foundAccount2 = bank.FindAccount(accountNumber2);
        System.out.println(foundAccount2.getBalance());
        bank.Interest();
        System.out.println("-----RENTE-----");
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        System.out.println(foundAccount.getBalance());
        System.out.println("-----Account 2-----");
        System.out.println(accountNumber2);
        System.out.println(foundAccount2.getBalance());
        System.out.println("-----Totaal van accounts-----");
        System.out.println(bank.TotalBalance());
        System.out.println("-----150.76 van account 1 naar 2-----");
        bank.Transfer(accountNumber, accountNumber2, 150.76);
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        System.out.println(foundAccount.getBalance());
        System.out.println("-----Account 2-----");
        System.out.println(accountNumber2);
        System.out.println(foundAccount2.getBalance());
        System.out.println("-----Totaal van accounts-----");
        System.out.println(bank.TotalBalance());
        System.out.println("-----Verwijderen Account 2, geld naar account 1-----");
        bank.RemoveAccount(accountNumber2, accountNumber);
        if (bank.FindAccount(accountNumber2) == null) {
            System.out.println("Account 2 succesvol verwijderd.");
        } else {
            System.out.println("Verwijderen account 2 niet succesvol.");
        }
        System.out.println("-----Account 1-----");
        System.out.println(accountNumber);
        System.out.println(foundAccount.getBalance());
        System.out.println("-----Totaal van accounts-----");
        System.out.println(bank.TotalBalance());
        System.out.println("-----Verwijderen Account 1, zonder transfer-----");
        bank.RemoveAccount(accountNumber);
        if (bank.FindAccount(accountNumber) == null) {
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
}
