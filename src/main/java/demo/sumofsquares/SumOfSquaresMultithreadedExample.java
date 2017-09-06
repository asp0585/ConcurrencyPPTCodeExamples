package demo.sumofsquares;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumOfSquaresMultithreadedExample {

    private static List<Integer> getRandomNumbersList(int size) {
        Random random = new Random();
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(1000));
        }
        return list;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // create list with random numbers
        int size = 100;
        List<Integer> numbersList = getRandomNumbersList(size);

        // partition list
        List<List<Integer>> allLists = Lists.partition(numbersList, 5);

        List<Future<Long>> futureList = new ArrayList<>();
        for (List<Integer> x : allLists) {
            SumCalculator sumCalculator = new SumCalculator(x);
            futureList.add(executorService.submit(sumCalculator));
        }
        long total = 0;
        for (Future<Long> f : futureList) {
            try {
                total += f.get();
            } catch (ExecutionException | InterruptedException e) {
                f.cancel(true);
            }
        }
        System.out.println("Total sum of squares = " + total);

        executorService.shutdown();
    }
}

