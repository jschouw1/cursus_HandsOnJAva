package Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingsAccountTest {
    BankAccount sut;

    @BeforeEach
    public void beforeEach() throws SavingsAccountException {
        sut = new SavingsAccount(0, 100);
    }

    @Test
    public void withdraw_withdrawFromSavingsAccountWithSufficientBalance_remainingBalance() throws BankAccountException {
        sut.withdraw(50);
        assertThat(sut.getBalance(), equalTo(50.0));
    }

    @Test
    public void withdraw_withdrawFromInsufficientBalance_Error() {
        Throwable exception = assertThrows(SavingsAccountException.class, () -> {
                    sut.withdraw(200);
                }
        );
        assertEquals("Balance of a SavingsAccount cannot drop below zero.", exception.getMessage());
    }

    @Test
    public void interest_positiveBalance_interestPosApplied() throws BankAccountException {
        sut.interest();
        assertThat(sut.getBalance(), equalTo(105.0));
    }
}
