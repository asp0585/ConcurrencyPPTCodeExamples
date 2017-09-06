package demo;

public class NoVisibility {

    private static int number;
    private static boolean ready;

    private static Runnable readerThread = () -> {
        while (!ready) {
            Thread.yield();
            System.out.println("Waiting....");
        }
        System.out.println(number);
    };

    public static void main(String[] args) {
        new Thread(readerThread).start();
        number = 42;
        ready = true;
    }
}
