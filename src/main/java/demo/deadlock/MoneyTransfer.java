package demo.deadlock;

public class MoneyTransfer {

    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct, final Account toAcct, double amount) {

        class Helper {
            private void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException("Insufficient funds to perform transfer");
                } else {
                    fromAcct.debitAmount(amount);
                    toAcct.creditAmount(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (toHash < fromHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}