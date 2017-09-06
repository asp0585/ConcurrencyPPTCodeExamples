package demo.volatileexample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PrimeGeneratorExample {

  /*static class PrimeGenerator implements Runnable {

    private final BlockingQueue<BigInteger> queue;

    public PrimeGenerator() {
      this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
      BigInteger p = BigInteger.ONE;
      while (!Thread.currentThread().isInterrupted()) {
        try {
          queue.put(p.nextProbablePrime());
        } catch (InterruptedException e) {
          // do nothing
        }
      }
    }

    public void cancel() {
      Thread.currentThread().interrupt();
    }

    public List<BigInteger> get() {
      return (List<BigInteger>) queue;
//    return queue.toArray(new BigInteger[0]);
    }
  }*/

    public static void main(String[] args) throws InterruptedException {
        BrokenPrimeGenerator brokenPrimeGenerator = new BrokenPrimeGenerator();
        Thread t = new Thread(brokenPrimeGenerator);
        t.start();
        try {
            SECONDS.sleep(1);
        } finally {
            brokenPrimeGenerator.cancel();
        }
        System.out.println(brokenPrimeGenerator.get());
    }

    static class BrokenPrimeGenerator implements Runnable {

        private final List<BigInteger> primes = Collections.synchronizedList(new ArrayList<>());
        // to fix it, just declare cancelled variable as volatile
        private boolean cancelled = false;

        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                p = p.nextProbablePrime();
                synchronized (this) {
                    primes.add(p);
                }
            }
        }

        public void cancel() {
            cancelled = true;
        }

        public List<BigInteger> get() {
            return primes;
        }
    }
}



