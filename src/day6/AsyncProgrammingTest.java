package day6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncProgrammingTest {
    public static void main(String[] args) {
        Executor executors = Executors.newFixedThreadPool(5);

        CompletableFuture<Exchange> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(8000);
            } catch (Exception ex) {
                System.out.printf("exception : "+ex);
            }
            return new Exchange("abc.com", "1 Dollar is 87.092683 Rupees");
        }, executors);
        CompletableFuture<Exchange> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(7000);
            } catch (Exception ex) {
                System.out.printf("exception : "+ex);
            }
            return new Exchange("cde.com", "1 Euro is 89.831892 Rupee");
        }, executors);
        CompletableFuture<Exchange> task3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
                System.out.printf("exception : "+ex);
            }
            return new Exchange("jkl.com", "1 British Pound is 108.151305 Rupee");
        }, executors);
        CompletableFuture<Exchange> task4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (Exception ex) {
                System.out.printf("exception : "+ex);
            }
            return new Exchange("mno.com", "1 Swiss Franc is 95.556131 Rupee");
        }, executors);
        CompletableFuture<Exchange> task5 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                System.out.printf("exception : "+ex);
            }
            return new Exchange("pqr.com", "1 Australian Dollar is 54.042046 Rupee");
        }, executors);

        System.out.println("waiting for tasks to complete");

        // fastest task
        CompletableFuture<Object> fastestTask = CompletableFuture.anyOf(task1, task2, task3, task4, task5);

        fastestTask.thenAccept((val) -> System.out.println("fastest task : "+val));

        // all task
        CompletableFuture<Void> allTask = CompletableFuture.allOf(task1, task2, task3, task4, task5);
        allTask.thenRun( () -> {
            List<Exchange> exchangeList = new ArrayList<>();
            exchangeList.add(task1.join());
            exchangeList.add(task2.join());
            exchangeList.add(task3.join());
            exchangeList.add(task4.join());
            exchangeList.add(task5.join());
            System.out.println(exchangeList);
        });

    }
}

class Exchange {
    String key;
    String exchangeRate;

    Exchange(String key, String exchangeRate) {
        this.key = key;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "key='" + key + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                '}';
    }
}
