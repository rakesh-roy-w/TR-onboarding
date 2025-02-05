package day6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyTest {
    public static void main(String[] args) {
        // Thread created by Runnable interface
        Thread thread1 = new Thread(new IncrementThread());

        DecrementThread thread2 = new DecrementThread();
        thread1.start();
        thread2.start();

        // executor service
        // Type of threadPool
        // 1. Fixed Thread Pool
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        es.submit(new IncrementThread());
        es.submit(new DecrementThread());
        es.submit(() -> display());

        /* diff b/w ExecutorService submit and execute method
        1. The submit() can accept both Runnable and Callable task but execute() can only accept the Runnable task.
        2. The submit() method is declared in ExecutorService interface while execute() method is declared in the Executor interface.
        3. The return type of submit() method is a Future object but return type of execute() method is void.
        */

        // 2. Single Thread Pool
        ExecutorService es1 = Executors.newSingleThreadExecutor();

        // 3. Dynamic/cached Thread Pool
        /*
        Short-Lived Tasks: For tasks that execute quickly and don’t need to be executed continuously,
        a cached thread pool works well as it minimizes the overhead of thread creation.
        Unpredictable Number of Tasks: If the number of tasks to execute is unknown and can vary,
        newCachedThreadPool() will adapt by creating new threads as needed.
        */
        ExecutorService es2 = Executors.newCachedThreadPool();

        // 4. Scheduled Thread Pool
//        ExecutorService es3 = Executors.newScheduledThreadPool();

        // 5. Custom Thread Pool
    }

    public static void display() {
        System.out.println("running executor service");
    }
}
class Sum implements Callable<Integer> {
    int val;
    public Sum(int v) {
        val = v;
    }
    public Integer call(){
        int result = 0;
        for(int i = 1; i <= val; i++){
            result += i;
        }
        return result;
    }
}
class IncrementThread implements Runnable {

    @Override
    public void run() {
        // increment count;
        System.out.println("increment thread : count : "+ Resource.count.incrementAndGet());
    }
}

class DecrementThread extends Thread {
    @Override
    public void run() {
        System.out.println("decrement thread: count: " + Resource.count.decrementAndGet());
    }
}

class Resource {
    public static AtomicInteger count = new AtomicInteger(0);
}


