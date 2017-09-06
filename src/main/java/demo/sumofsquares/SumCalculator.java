package demo.sumofsquares;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class SumCalculator implements Callable<Long> {

    private final List<Integer> list;

    SumCalculator(List<Integer> list) {
        this.list = Collections.unmodifiableList(list);
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        String name = Thread.currentThread().getName();
        System.out.println("Calculating sum for " + name);
        for (int a : list) {
            sum += a * a;
        }
        System.out.println("Sum returned by " + name + " = " + sum);
        return sum;
    }
}
