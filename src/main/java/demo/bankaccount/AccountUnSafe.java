package demo.bankaccount;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
class AccountUnSafe implements IAccount {

    private int balance = 20;

    public int getBalance() {
        return balance;
    }

    public void withdrawAmount(int amount, String threadName) {
        System.out.println(threadName + " " + balance);
        if (balance >= amount) {
            System.out.println(threadName + "is going to make withdrawal");
            try {
                System.out.println(threadName + "going to sleep");
                Thread.sleep(1000);
                System.out.println(threadName + "woke up");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(threadName + "made amount to " + balance + " after withdrawal.");
            System.out.println(threadName + "completes the withdrawal. Account Balance now is " + balance);
        } else {
            System.out.println(
                    threadName + " Not enough amount for " + threadName + " to withdraw account balance " + balance);
        }
    }
}
