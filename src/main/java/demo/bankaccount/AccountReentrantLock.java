package demo.bankaccount;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
class AccountReentrantLock implements IAccount {

    private final Lock lock = new ReentrantLock();
    @GuardedBy("lock")
    private int balance = 20;

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void withdrawAmount(int amount, String threadName) {
        System.out.println(threadName + " " + balance);
        lock.lock();
        try {
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
        } finally {
            lock.unlock();
        }
    }
}
