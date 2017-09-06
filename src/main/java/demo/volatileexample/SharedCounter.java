package demo.volatileexample;

public class SharedCounter {

    private volatile int c;

    public SharedCounter(int seed) {
        this.c = seed;
    }

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    private int getCounter() {
        return c;
    }
}
