package Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BankAccountTest {
    BankAccount sut;

    @BeforeEach
    public void beforeEach() {
        sut = new BankAccount(0);
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
}
