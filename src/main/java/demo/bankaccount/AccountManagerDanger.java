package demo.bankaccount;

public class AccountManagerDanger implements Runnable {

    private demo.bankaccount.IAccount account = new demo.bankaccount.AccountUnSafe();

    public static void main(String[] args) {
        AccountManagerDanger accountManagerDanger = new AccountManagerDanger();

        Thread t1 = new Thread(accountManagerDanger);
        Thread t2 = new Thread(accountManagerDanger);
        t1.setName("Lucy");
        t2.setName("Bob");

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            String threadName = Thread.currentThread().getName() + "-" + (i + 1) + " ";
            int amountToWithdraw = 10;
            account.withdrawAmount(amountToWithdraw, threadName);
            int balanceAmount = account.getBalance();
            if (balanceAmount < 0) {
                System.out.println("Balance is overdrawn : " + balanceAmount);
            }
        }
    }
}

