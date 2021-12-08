package Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckingAccountTest {
    BankAccount sut;

    @BeforeEach
    public void beforeEach() {
        sut = new CheckingAccount(0);
    }

    @Test
    public void deposit_100_total100() {
        sut.deposit(100.00);
        assertThat(sut.getBalance(), equalTo(100.00));
    }

    @Test
    public void deposit_tenthCent_shouldBeRoundedToTheNearestWholeCent() {
        sut.deposit(100.001);
        assertThat(sut.getBalance(), equalTo(100.00));
    }

    @Test
    public void deposit_singleEventLoggerSingleDeposit_SingleLog() {
        AtomicReference<Double> newBalance = new AtomicReference<>((double) 0);
        AtomicReference<Double> oldBalance = new AtomicReference<>((double) 0);
        BalanceChangedLogger balanceChangedLogger = new BalanceChangedLogger();
        sut.addBalanceChangedListener(event -> {
            newBalance.set(event.getNewBalance());
            oldBalance.set(event.getOldBalance());
        });
        sut.deposit(100);
        assertThat(newBalance.get(), equalTo(100.0));
        assertThat(oldBalance.get(), equalTo(0.0));
    }

    @Test
    public void interest_positiveBalance_interestPosApplied() throws BankAccountException {
        sut.deposit(100);
        sut.interest();
        assertThat(sut.getBalance(), equalTo(101.0));
    }
}
