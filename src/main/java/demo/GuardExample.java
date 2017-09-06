package demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GuardExample {

    private final Object lock = new Object();
    private final Lock reentrantLock = new ReentrantLock();
    private int x;

    GuardExample(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        GuardExample guardExample = new GuardExample(10);
    }

    public synchronized int getX1() {
        return x;
    }

    public synchronized void setX1(int x) {
        this.x = x;
    }

    public int getX2() {
        synchronized (lock) {
            return x;
        }
    }

    public void setX2(int x) {
        synchronized (lock) {
            this.x = x;
        }
    }

    public int getX3() {
        synchronized (this) {
            return x;
        }
    }

    public void setX3(int x) {
        synchronized (this) {
            this.x = x;
        }
    }

    public int getX4() {
        reentrantLock.lock();
        try {
            return x;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void setX4(int x) {
        reentrantLock.lock();
        try {
            this.x = x;
        } finally {
            reentrantLock.unlock();
        }
    }
}
