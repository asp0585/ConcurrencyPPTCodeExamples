package demo.deadlock;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String s) {
        super(s);
    }
}
