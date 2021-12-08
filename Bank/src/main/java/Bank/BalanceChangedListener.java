package Bank;

import java.util.EventListener;

@FunctionalInterface
public interface BalanceChangedListener extends EventListener {

    void balanceChanged(BalanceChangedEvent event);

}
