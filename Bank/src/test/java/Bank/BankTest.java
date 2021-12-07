package Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class BankTest {
    Bank sut;

    @BeforeEach
    public void beforeEach() {
        sut = new Bank();
    }

    @Test
    public void findAccount_nonExistingAccountNumber_Exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
            sut.findAccount(100);
            }
        );

        assertEquals("No account found for account number 100.", exception.getMessage());
    }

    @Test
    public void transfer_nonExistingAccount_Exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
                    sut.addBankAccount(200);
                    sut.transfer(100, 101, 50);
                }
        );

        assertEquals("No account found for account number 100.", exception.getMessage());
    }

    @Test
    public void transfer_sameAccountNumbers_Exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
            int accountNumber = sut.addBankAccount(200);
            sut.transfer(accountNumber, accountNumber, 50);
            }
        );

        assertEquals("Cannot transfer from and to the same account.", exception.getMessage());
    }

    @Test
    public void removeAccount_accountNumberStillHoldingCredit_Exception() {
        AtomicInteger accountNumber = new AtomicInteger();
        Throwable exception = assertThrows(Exception.class, () -> {
            accountNumber.set(sut.addBankAccount(200));
            sut.removeAccount(accountNumber.get());
            }
        );

        assertEquals(String.format("Cannot remove account with non-zero balance. Balance of account %s is 200.0.", accountNumber.get()), exception.getMessage());
    }

    @Test
    public void removeAccount_nonExistingAccount_Exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
            sut.addBankAccount(200);
            sut.removeAccount(101);
            }
        );

        assertEquals("No account found for account number 101.", exception.getMessage());
    }

    @Test
    public void removeAccount_nonExistingAccountToTransferTo_Exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
            int accountNumber = sut.addBankAccount(200);
            sut.removeAccount(accountNumber, 101);
            }
        );

        assertEquals("No account found for account number 101.", exception.getMessage());
    }

    @Test
    public void removeAccount_transferToSameAccount_Exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
            int accountNumber1 = sut.addBankAccount(200);
            sut.addBankAccount(200);
            sut.removeAccount(accountNumber1, accountNumber1);
            }
        );

        assertEquals("Cannot transfer from and to the same account.", exception.getMessage());
    }

    @Test
    public void findAllNegativeAccountNumbers_with1negativeBalance_returnsOneAccount() {
        int accountNumber = (sut.addBankAccount(-1));
        sut.addBankAccount(1);
        assertThat(sut.findAllNegativeAccounts(), equalTo(new int[] {accountNumber}));
    }

    @Test
    public void findAllNegativeAccountNumbers_with3negativeBalance_returnsThoseAccounts() {
        int[] accountNumbers = new int[3];
        accountNumbers[0] = (sut.addBankAccount(-1));
        sut.addBankAccount(1);
        accountNumbers[1] = (sut.addBankAccount(-1));
        accountNumbers[2] = (sut.addBankAccount(-1));
        sut.addBankAccount(1);
        sut.addBankAccount(1);
        assertThat(sut.findAllNegativeAccounts(), equalTo(accountNumbers));
    }

    @Test
    public void findAllNegativeAccountNumbers_with0Balance_returnsEmpty() {
        sut.addBankAccount(0);
        assertThat(sut.findAllNegativeAccounts().length, equalTo(0));
    }

    @Test
    public void findMostAverageAccountNumbers_withOneExactAverage_returnsThatAccount() {
        sut.addBankAccount(2);
        sut.addBankAccount(-2);
        int accountNumber = sut.addBankAccount(0);
        assertThat(sut.findMostAverageAccounts(), equalTo(new int[] {accountNumber}));
    }

    @Test
    public void findMostAverageAccountNumbers_withTwoExactAverage_returnsBothAccounts() {
        int[] accountNumbers = new int[2];
        sut.addBankAccount(2);
        sut.addBankAccount(-2);
        accountNumbers[0] = sut.addBankAccount(0);
        accountNumbers[1] = sut.addBankAccount(0);
        assertThat(sut.findMostAverageAccounts(), equalTo(accountNumbers));
    }

    @Test
    public void findMostAverageAccountNumbers_withAllAccountsSameBalance_returnsAllAccounts() {
        int[] accountNumbers = new int[4];
        accountNumbers[0] = sut.addBankAccount(0);
        accountNumbers[1] = sut.addBankAccount(0);
        accountNumbers[2] = sut.addBankAccount(0);
        accountNumbers[3] = sut.addBankAccount(0);
        assertThat(sut.findMostAverageAccounts(), equalTo(accountNumbers));
    }
}
