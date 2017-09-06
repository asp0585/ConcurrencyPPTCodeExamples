package demo.bankaccount;

public interface IAccount {

    int getBalance();

    void withdrawAmount(int amount, String threadName);
}
