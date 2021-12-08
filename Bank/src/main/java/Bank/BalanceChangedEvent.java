package Bank;

import java.util.EventObject;
import java.util.Objects;

public class BalanceChangedEvent extends EventObject {
    double oldBalance;
    double newBalance;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BalanceChangedEvent(Object source, double oldBalance, double newBalance) {
        super(source);
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    double getOldBalance() {
        return oldBalance;
    }

    double getNewBalance() {
        return newBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceChangedEvent that = (BalanceChangedEvent) o;
        return Double.compare(that.oldBalance, oldBalance) == 0 && Double.compare(that.newBalance, newBalance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldBalance, newBalance);
    }
}
