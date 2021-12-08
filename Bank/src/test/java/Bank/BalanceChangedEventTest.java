package Bank;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BalanceChangedEventTest {

    @Test
    public void equals_sameValue_isTrue() {
        BankAccount bankAccount = new CheckingAccount(0);
        BalanceChangedEvent balanceChangedEvent = new BalanceChangedEvent(bankAccount, 0, 1);
        BalanceChangedEvent balanceChangedEvent2 = new BalanceChangedEvent(bankAccount, 0, 1);
        assertThat(balanceChangedEvent.equals(balanceChangedEvent2), equalTo(true));

    }
}
