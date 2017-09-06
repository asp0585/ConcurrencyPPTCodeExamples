package demo;


public class ThreadRunnableDemo {

    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread foo = new Thread(r);
        Thread bar = new Thread(r);
        foo.start();
        bar.start();
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                System.out.println("The counter value is " + i);
            }
        }
    }
}
