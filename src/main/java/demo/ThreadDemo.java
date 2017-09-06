package demo;


public class ThreadDemo {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
//        myThread.start();
        myThread.run();
    }

    static class MyThread extends Thread {

        public void run() {
            System.out.println("Inside my thread");
        }
    }
}
