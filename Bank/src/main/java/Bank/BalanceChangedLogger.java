package Bank;

public class BalanceChangedLogger implements BalanceChangedListener{
    String message;

    public void balanceChanged(BalanceChangedEvent event) {
        message = String.format("Event: Balance Old (%s); Balance New (%s).", event.getOldBalance(), event.getNewBalance());
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }

}
